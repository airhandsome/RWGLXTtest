package com.service.execute;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.dao.AtrributeDAO;
import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.EventTriggerDAO;
import com.dao.IspDAO;
import com.dao.TriggerAtrDAO;
import com.domain.Atrribute;
import com.domain.Ca;
import com.domain.Da;
import com.domain.EventTrigger;
import com.domain.Isp;
import com.domain.TriggerAtr;
import com.domain.execute.AttributeDaoResult;

public class AtrChangeService {
	public void atrExecute(String tag, Float value) {
		// update attribute value
		AtrributeDAO atrdao = new AtrributeDAO();
		AttributeDaoResult adr = new AttributeDaoResult();
		Integer atrId = atrdao.getAtrByTag(tag).get(0).getAtrId(); //Atr_id
		Atrribute tempatr = atrdao.getAtrByTag(tag).get(0);           
		Float change = value- tempatr.getValue();                                //���¼�����Atr_id����,value�ĸı�
		adr = atrdao.updateById(atrId, value, change);
		Float atrValue = value;
		// check if that action affects any ca or da,in a new xiancheng. change
		// the state
		TriggerAtrDAO tadao = new TriggerAtrDAO();
		// add ca and isp
		DaDAO dadao = new DaDAO();
		List<TriggerAtr> talist = tadao.getTriggerByAtrId(adr.getAtrId());

		EventTriggerDAO etdao = new EventTriggerDAO();
		try {
			for (int i = 0; i < talist.size(); i++) {     //�鿴atrid��
				TriggerAtr ta = talist.get(i);
				Integer eventid = ta.getId().getEventTrigger().getTriggerId();   //eventidҲ����triggerid
				List<TriggerAtr> sameTrIdList = tadao.getTriggerAtr(eventid);//import java.util.ArrayList; 
				Map ta_map = new HashMap();
				
				for (int j = 0; j < sameTrIdList.size(); j++) {
					TriggerAtr ta_tmp = sameTrIdList.get(j);
					Integer ta_tmp_atrid = ta_tmp.getId().getAtrribute().getAtrId();
					System.out.println("tianjianzhi="+ta_tmp.getValue().floatValue());
//					Float true_value  = ta_tmp.getId().getAtrribute().getValue();//�洢����ݿ��е�ֵ ��Ĺ���
					Float true_value = atrdao.findById(ta_tmp_atrid).getValue();
					
					System.out.println("true_value"+true_value);
					switch (ta_tmp.getSymbol()) {
						case 1:
							if (ta_tmp.getValue().floatValue() == true_value.floatValue()) { 
								ta_map.put(ta_tmp_atrid, "1");
								break;
							}						
						case 2:
							if (ta_tmp.getValue().floatValue() > true_value.floatValue()) { 
								ta_map.put(ta_tmp_atrid, "1");
								break;
							}				
						case 3:
							if (ta_tmp.getValue().floatValue() < true_value.floatValue()) { 
								ta_map.put(ta_tmp_atrid, "1");
								break;
							}					
						case 4:
							if (ta_tmp.getValue().floatValue()>= true_value.floatValue()) { 
								ta_map.put(ta_tmp_atrid, "1");
								break;
							}
							case 5:
							if (ta_tmp.getValue().floatValue() <= true_value.floatValue()) { 
								ta_map.put(ta_tmp_atrid, "1");
								break;
							}
							default:
								ta_map.put(ta_tmp_atrid, "0");
					}
				}
				EventTrigger et_tmp = etdao.findById(eventid);//���findById��ͨ��trigger_id����eventTrigger�Ļ�
				if(et_tmp.getLogic()==null)
						continue;
				String[] logic_array = et_tmp.getLogic().split("#");      //logic��ʽΪ or=1 and=2 end=0     1#2#0����or#and#0 
				String[] atrid_array = et_tmp.getCode().split("#");		//code��ʽΪ atrid1#atrid2#atrid3 
				List<String> end = new ArrayList<String>();
				for(int k = 0; k < atrid_array.length; k++) {
					end.add(ta_map.get(Integer.parseInt(atrid_array[k])).toString());
				}
				boolean flag,flag_tmp;
				if(end.get(0) == "0")
					flag = false;
				else 
					flag = true;
				for(int h = 0; h < end.size()-1; h++ ){
					 String end_par = end.get(h+1);
					 if(end_par == "0")
						 flag_tmp = false;
						else 
							flag_tmp = true;
					 String logic_par = logic_array[h];
					 if( logic_par =="2")//or
						 flag =  flag|flag_tmp;
					 else if( logic_par =="1")//and
						 flag =  flag&flag_tmp;
					 }
				if(flag){
					updateAutoDa(eventid);
					dadao.updateNotAutoDaByEvent(eventid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateAutoDa(Integer eventid) {
		DaDAO dadao = new DaDAO();
		List<Da> da_stoped_list = dadao.findStopedDaByEventAndState(eventid);
		dadao.updateStartAutoDaByEvent(da_stoped_list);
		List<Da> da_started_list = dadao.findStartedDaByEventAndState(eventid);
		dadao.updateStopAutoDaByEvent(da_started_list);
		// new function

		// ExecuteFollowDaService service = new ExecuteFollowDaService();
		// service.execute(dalist);
	}
	private void updateISP(Integer eventid) {
		IspDAO ispdao = new IspDAO();
		List<Isp> isplist = ispdao.getEventISP(eventid);
		ispdao.updateEventISP(isplist);
		if (isplist != null && isplist.size() > 0) {
			CaDAO cadao = new CaDAO();
			for (Isp isp : isplist) {
				cadao.changeStateByISP(isp.getIspId(), "TriggerPending");
			}
		}
	}

	private void updateAutoCa(Integer eventid) {
		CaDAO cadao = new CaDAO();
		List<Ca> calist = cadao.getCabyEvent(eventid);
		if (calist != null && calist.size() > 0) {
			DaDAO dadao = new DaDAO();
			for (Ca ca : calist) {
				ca.setExcuteStarttime(new Timestamp(System.currentTimeMillis()));
				cadao.changeState(ca, "Executing");
				dadao.changeStateByCA(ca.getCaId(), "TriggerPending");
			}
		}
	}

	// private void updateNotAutoCa(Integer eventid) {
	// CaDAO cadao = new CaDAO();
	// cadao.updateCaByEvent(eventid);
	// }
}
