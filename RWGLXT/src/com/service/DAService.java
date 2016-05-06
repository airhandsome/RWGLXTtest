package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DaatrDAO;
import com.dao.DsDAO;
import com.dao.EventTriggerDAO;
import com.dao.SequenceDAO;
import com.dao.TriggerAtrDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Daatr;
import com.domain.Ds;
import com.domain.EventTrigger;
import com.domain.Isp;
import com.domain.Sequence;
import com.domain.TriggerAtr;
import com.service.def.GetCAforExecute;
import com.service.def.SequenceController;

public class DAService   {
	private DaDAO dadao = new DaDAO();
	
	public List<Map> convert(List<Da> list){
		
		List<Map> result = new ArrayList<Map> ();
		Iterator<Da> iter = list.iterator(); 
		DaAtrService daatrservice= new DaAtrService();
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Da da = new Da();
			da = iter.next();
			map.put("id", da.getDaId());
			if(da.getFightPostion()!=null){
				map.put("fightPostion", da.getFightPostion().getPostionId());
				map.put("fightPostionName", da.getFightPostion().getName());
			}else{
				map.put("fightPostion", null);
				map.put("fightPostionName", null);
			}
			map.put("name", da.getName());
			map.put("description", da.getDescription() );
			map.put("triggertype", da.getTriggertype());
			map.put("priority", da.getPriority());
			map.put("state", da.getState());
			map.put("ifAuto", da.getIfAuto());
			map.put("ifdef", da.getIfdef());
			if(da.getType()!=null){
				map.put("Type",da.getType().getTypeId());
			}else{
				map.put("Type", null);
			}
			map.put("attribute", daatrservice.getDAAtr(da.getDaId()));
			map.put("code", da.getCode());
			map.put("nodename", da.getNodename());
			if(da.getUsers()!=null){
				map.put("userId", da.getUsers().getUserid());
				map.put("userName", da.getUsers().getUserid());
			}
			else{
				map.put("userId", null);
				map.put("userName", null);
			}
				
			if(da.getEventTriggerByEvent()!=null)
				map.put("eventTriggerByEvent", da.getEventTriggerByEvent().getTriggerId());
			else
				map.put("eventTriggerByEvent", null);
			
			if(da.getCa()!=null)
				map.put("caId", da.getCa().getCaId());
			else
				map.put("caId", null);
			
			if(da.getEventTriggerByEndEvent()!=null)
				map.put("eventTriggerByEndEvent", da.getEventTriggerByEndEvent().getTriggerId());
			else
				map.put("eventTriggerByEndEvent", null);
			
			if(da.getDs()!=null)
				map.put("postDs", da.getDs().getDsId());
			else
				map.put("postDs", null);
			
			if(da.getDefbelong()!=null)
				map.put("defBelong", da.getDefbelong());
			else
				map.put("defBelong", null);
			
			if(da.getPlanStart()!=null)
				map.put("planStart", da.getPlanStart().toString());
			else
				map.put("planStart", "");
			
			if(da.getPlanEnd()!=null)
				map.put("planEnd", da.getPlanEnd().toString());
			else
				map.put("planEnd", "");
			
			if(da.getPlanDuration()!=null)
				map.put("planDuration", da.getPlanDuration().toString());
			else
				map.put("planDuration", "");
			
			if(da.getTerminate()!=null)
				map.put("terminate", da.getTerminate());
			else
				map.put("terminate", null);
			
			if(da.getExcuteStart()!=null)
				map.put("excuteStart", da.getExcuteStart().toString());
			else
				map.put("excuteStart", "");
			
			if(da.getExcuteEnd()!=null)
				map.put("excuteEnd", da.getExcuteEnd().toString());
			else
				map.put("excuteEnd", "");
			
			result.add(map);
		}
		return result;
	}

	public List<Map> getExeDA(Integer postionId){
		List <Da> u = dadao.getExeDA(postionId);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getDAbyType(Integer typeId){
		List <Da> u = dadao.getDAbyType(typeId);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getDAbyCa(Integer caId){
		List <Da> u = dadao.getDAbyCa(caId);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getDAbyState(String state){
		List <Da> u = dadao.findByState(state);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getDAbyId(Integer daId) {
		List <Da> u = new ArrayList<Da>();
		Da da = dadao.findById(daId);

		u.add(da);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	/////!@#
	public List<Map> getTriggersbyId(Integer daId,Integer status) {//0 start 1 end
		List <Map> u = new ArrayList<Map>();
		Da da = dadao.findById(daId);

		EventTrigger  EventT = new EventTrigger();
		if(status == 0)
			EventT= da.getEventTriggerByEvent();
		else 
			EventT= da.getEventTriggerByEndEvent();
		if(EventT == null)
			return null;
//		System.out.println( EventT.getLogic());
		Integer eventId = EventT.getTriggerId();
		TriggerAtrDAO tadao = new TriggerAtrDAO();
		List<TriggerAtr> sameTrIdList = tadao.getTriggerAtr(eventId);
		//!@#0419
		EventTriggerDAO etdao = new EventTriggerDAO();
		if(etdao.findById(eventId).getLogic()=="")
			return null;
		String[] logic_dandu = etdao.findById(eventId).getLogic().split("#");
		for (int i = 0; i < sameTrIdList.size(); i++) {
					Map map = new HashMap();
					Integer attrid_ = sameTrIdList.get(i).getId().getAtrribute().getAtrId();
					String name_ = sameTrIdList.get(i).getId().getAtrribute().getName();
					Integer symbs_ = sameTrIdList.get(i).getSymbol();
					Float vals_  =sameTrIdList.get(i).getValue();
					String logics_ = logic_dandu[i];
					map.put("attrid", attrid_);
					map.put("symbs", symbs_);
					map.put("vals", vals_ );
					map.put("logics",logics_);
					String logics_word = "";
					String symbs_word="";
					if(logics_=="1")
						logics_word = "and";
					else if(logics_=="2")
						logics_word = "or";
					else 
						logics_word = "end";
					
					if(symbs_ == 1)
						symbs_word = "=";
					else if(symbs_ == 2)
						symbs_word = ">";
					else if(symbs_ == 3)
						symbs_word = "<";
					else if(symbs_ == 4)
						symbs_word = ">=";
					else if(symbs_ == 5)
						symbs_word = "<=";
					
					map.put("st", name_+" "+symbs_word+" "+vals_+" "+logics_word);
					u.add(map);
		}
		return u;
	}
	public void setTriggersbyId(Integer daId,String attrid_string ,String symbs_string ,String vals_string ,String logics,Integer status) {
		Da da = dadao.findById(daId);
//		EventTrigger EventT= da.getEventTriggerByEvent();
		EventTrigger  EventT;
		if(status == 0)
			EventT= da.getEventTriggerByEvent();
		else 
			EventT= da.getEventTriggerByEndEvent();
		
		if(attrid_string==null||logics==null)
			return ;
		Integer eventId = EventT.getTriggerId();
		EventTriggerDAO  eventtdao= new EventTriggerDAO();
//		eventtdao.update(eventId, logics, attrid_string);
		EventT.setCode(attrid_string);
		EventT.setLogic(logics);
		

		//!@#0419
//		EventTriggerDAO eventdao = new EventTriggerDAO();
		eventtdao.insert(eventId,attrid_string, logics);

		
	
		String[] attrid_array = attrid_string.split("#"); 
		String[] symbs_array = symbs_string.split("#"); 
		String[] vals_array = vals_string.split("#"); 
	  
		TriggerAtrDAO tadao = new TriggerAtrDAO();
		List<TriggerAtr> sameTrIdList = tadao.getTriggerAtr(eventId);
		for (int i = 0; i < sameTrIdList.size(); i++) {
			tadao.delete(sameTrIdList.get(i));
		}
		for (int i = 0; i < attrid_array.length; i++) {
			Integer attrid = Integer.parseInt(attrid_array[i]);
			Float    vals     = Float.parseFloat(vals_array[i]);
			Integer symbs =  Integer.parseInt(symbs_array[i]);
//			System.out.println("33333333333333333333333status  "+status);
//			System.out.println("33333333333333333333333eventId  "+eventId);	
//			System.out.println("33333333333333333333333attrid "+attrid);
//			System.out.println("33333333333333333333333vals "+vals);
//			System.out.println("33333333333333333333333symbs "+symbs);
			try{
			tadao.insert(eventId, attrid,vals ,symbs);
			}
			catch(Exception e){
					System.out.println("illegal string");
			}
		}
		return ;
	}
	////
	
	
	/*public int update(Integer daId,Integer eventTriggerByEventId, Integer fightPostionId, Integer usersId, Integer caId, Integer eventTriggerByEndEventId, 
			Integer typeId, Integer dsId, Integer defbelong, String name, String description, String triggertype, Integer priority, String state, Integer ifAuto,
			Integer ifdef, Timestamp planStart, Timestamp planDuration, Timestamp planEnd, String terminate, Timestamp excuteStart, Timestamp excuteEnd, String code){
		
		int flag = dadao.update(daId, eventTriggerByEventId, fightPostionId, usersId, caId, eventTriggerByEndEventId, typeId, dsId, defbelong, name, description, triggertype, priority, state, ifAuto, ifdef, planStart, planDuration, planEnd, terminate, excuteStart, excuteEnd, code);
		return flag;
	}*/
	
	public Integer insert(Integer fightPostionId, Integer usersId, Integer caId, Integer typeId, Integer dsId, Integer defbelong, String name, String description, String triggertype, Integer priority, String state, Integer ifAuto,
			Integer ifdef, Timestamp planStart, Timestamp planDuration, Timestamp planEnd, String terminate, Timestamp excuteStart, Timestamp excuteEnd, String code, Integer[] attribute, String eventlogic, String eventcode, String[] eventatr,
			String endeventlogic, String endeventcode, String[] endeventatr, List<Integer> preDA, Integer preCA,String nodename){
		
		Integer eventtriggerId = null;
		Integer endeventtriggerId = null;
		Integer daId = null;

		if(eventatr!=null){   //triggertype.equals("鐘舵�"); 鎻掑叆DA涔嬪墠锛屽厛瑕佹彃鍏rigger浠ュ強鐩稿簲鍏宠仈灞炴�,骞惰繑鍥瀟rigger鐨刬d锛宒a鎵嶈兘澶熸彃鍏�
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			EventTrigger eventtrigger = etd.insert(eventlogic, eventcode);
			eventtriggerId = eventtrigger.getTriggerId();
			tas.insertAnalyse(eventatr, eventtriggerId);
		}
		
		if(endeventatr!=null){   //triggertype.equals("鐘舵�"); 鎻掑叆DA涔嬪墠锛屽厛瑕佹彃鍏rigger浠ュ強鐩稿簲鍏宠仈灞炴�,骞惰繑鍥瀟rigger鐨刬d锛宒a鎵嶈兘澶熸彃鍏�
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			EventTrigger endeventtrigger = etd.insert(endeventlogic, endeventcode);
			endeventtriggerId = endeventtrigger.getTriggerId();
			tas.insertAnalyse(endeventatr, endeventtriggerId);
		}
		if(code==null||code.equals("")){
			code="no data";
		}
		daId = dadao.insertTo(eventtriggerId, fightPostionId, usersId, caId, endeventtriggerId, typeId, dsId, defbelong, name, description, triggertype, priority, state, ifAuto, ifdef, planStart, planDuration, planEnd, terminate, excuteStart, excuteEnd, code,nodename).getDaId();
		
		if(preDA != null&&preDA.size()>0){
			SequenceDAO seqdao = new SequenceDAO();
			for(Integer daid:preDA){
				seqdao.insert("DA", daid, "DA", daId);
			}
		}
		
//		if(preCA != null){
//			SequenceDAO seqdao = new SequenceDAO();
//			seqdao.insert("CA", preDA, "DA", daId);
//		}
		
		if(attribute!=null){
			DaatrDAO daatrdao = new DaatrDAO();
			for(int i = 0; i<attribute.length; i++){
				System.out.println("sssssss"+attribute.length);
				daatrdao.insert(daId, attribute[i], null);
			}
		}
		return daId;
	}

	public void update(Integer daId, Integer fightPostionId, Integer usersId, Integer caId, Integer typeId, Integer dsId, Integer defbelong, String name, String description, String triggertype, Integer priority, String state, Integer ifAuto,
			Integer ifdef, Timestamp planStart, Timestamp planDuration, Timestamp planEnd, String terminate, Timestamp excuteStart, Timestamp excuteEnd, String code, 
			Integer eventTriggerByEventId, String eventlogic, String eventcode, String[] eventatr,
			Integer eventTriggerByEndEventId, String endeventlogic, String endeventcode, String[] endeventatr, List<Integer> preDA, Integer preCA, Integer[] attribute){
		
		if(eventatr!=null){  
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			etd.update(eventTriggerByEventId, eventlogic, eventcode);
			tas.updateAnalyse(eventatr, eventTriggerByEventId);
		}
		
		if(endeventatr!=null){  
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			etd.update(eventTriggerByEndEventId, endeventlogic, endeventcode);
			tas.updateAnalyse(endeventatr, eventTriggerByEndEventId);
		}
		if(code==null||code.equals("")){
			code="no data";
		}
		dadao.update(daId, eventTriggerByEventId, fightPostionId, usersId, caId, eventTriggerByEndEventId, typeId, dsId, defbelong, name, description, triggertype, priority, state, ifAuto, ifdef, planStart, planDuration, planEnd, terminate, excuteStart, excuteEnd, code);
		
		if((preDA != null)&&preDA.size()>0){
			SequenceDAO seqdao = new SequenceDAO();
			for(Integer daid : preDA){
				List<Sequence> list = seqdao.queryByPost("DA", daId);
				for(Sequence seq : list){
					seqdao.delete(seq.getSequenceId());
				}
				seqdao.insert("DA", daid, "DA", daId);
			}
		}
		DaatrDAO daatrdao = new DaatrDAO();
		List<Daatr> daatrlist = daatrdao.getDAAtr(daId);
		for(Daatr temp : daatrlist){
			daatrdao.delete(temp);
		}
		daatrdao.deleteByDa(daId);
		if(attribute!=null){
			for(int i = 0; i<attribute.length; i++){
				daatrdao.insert(daId, attribute[i], null);
			}
		}
//		if(preCA != null){
//			SequenceDAO seqdao = new SequenceDAO();
//			Sequence seq = seqdao.queryByPost("DA", daId);
//			if(seq!=null)
//				seqdao.update(seq.getSequenceId(), "CA", preCA, "DA", daId);
//		}
		
	}
	
	public int delete (Integer daId, Integer caId){
		Da da = new Da();
		da = dadao.findById(daId);
		if(da!=null){
			if(da.getEventTriggerByEvent()!=null){
				EventTriggerDAO etd = new EventTriggerDAO();
				Integer eventid = da.getEventTriggerByEvent().getTriggerId();
				etd.delete(eventid);
			}
			if(da.getEventTriggerByEndEvent()!=null){
				EventTriggerDAO etd = new EventTriggerDAO();
				Integer endeventid = da.getEventTriggerByEndEvent().getTriggerId();
				etd.delete(endeventid);
			}
		}
		
		SequenceDAO seqdao = new SequenceDAO();
		List<Sequence> postseq=null;
		Integer preseq =null;
		if(seqdao.queryByPost("DA", daId)!=null){
			postseq = seqdao.queryByPost("DA", daId);
		}
		if(seqdao.queryByPre("DA", daId)!=null){
			preseq = seqdao.queryByPre("DA", daId).get(0).getSequenceId();//鑻ュ叾鑻ヤ负鍏朵粬鐨勫墠椹憋紝涓嶈兘琚垹闄�
		}
		
		if(preseq!=null){
			return 0;
		}
		else{
/*			DsDAO dsdao = new DsDAO();
			List<Ds> list = dsdao.getDSbyCa(caId);
			Iterator<Ds> it = list.iterator();
			while(it.hasNext()){
				Ds ds = it.next();
				if(ds.getDaByTrueExit().getDaId().equals(daId)||ds.getDaByFalseExit().getDaId().equals(daId)){
					//dsdao.delete(ds.getDsId());
					Integer postseqtemp = seqdao.queryByPost("DS", ds.getDsId()).getSequenceId();
					if(postseqtemp!=null){
						seqdao.delete(postseqtemp);
					}
					break;
				}
			}
			*/
			dadao.delete(daId);
			if(postseq!=null){
				for(Sequence s : postseq){
					seqdao.delete(s.getSequenceId());
				}
			}
			return 1;
		}

	}
	
	public void updateState(Integer daId, String state){
		Da da = dadao.findById(daId);
		dadao.updateState(da, state);
		return;
	}
	
	public List<Map> getPreDAbyId(Integer daId){
		List<Da> result = new ArrayList<Da>();
		SequenceDAO seqdao = new SequenceDAO();
		Ca ca = dadao.findById(daId).getCa();
		List<Sequence> list = seqdao.queryByPost("DA", daId);
		for(Sequence s:list){
			Da da = dadao.findById(s.getPre());
			if(da.getCa().getCaId().intValue()==ca.getCaId().intValue()){
				result.add(da);
			}
		}
		
		List<Ds> dslist = new DsDAO().getDSbyCa(ca.getCaId());
		for(Ds ds : dslist){
			if(ds.getDaByTrueExit().getDaId().intValue()==daId.intValue()||ds.getDaByFalseExit().getDaId().intValue()==daId){
				Sequence s = seqdao.queryByPost("DS", ds.getDsId()).get(0);
				Da da = dadao.findById(s.getPre());
				if(da.getCa().getCaId().intValue() == ca.getCaId().intValue()){
					result.add(da);
				}
			}
		}
		return convert(result);
	}
	
	public List<Map> getPostDAbyId(Integer daId){
		List<Da> result = new ArrayList<Da>();
		SequenceDAO seqdao = new SequenceDAO();
		List<Sequence> list = seqdao.queryByPre("DA", daId);
		Ca ca = dadao.findById(daId).getCa();
		for(Sequence s:list){
		if(s.getPostType().equals("DA")){
			Da da = dadao.findById(s.getPost());
			if(da.getCa().getCaId().intValue()==ca.getCaId().intValue()){
				result.add(da);
			}
		}else if(s.getPostType().equals("DS")){
			Ds ds = new DsDAO().findById(s.getPost());
			if(ds.getDaByFalseExit()!=null&&ds.getDaByFalseExit().getCa().getCaId().intValue()==ca.getCaId().intValue())
				result.add(ds.getDaByFalseExit());
			if(ds.getDaByTrueExit()!=null&&ds.getDaByTrueExit().getCa().getCaId().intValue()==ca.getCaId().intValue())
				result.add(ds.getDaByTrueExit());
		}
		}
		return convert(result);
	}
	
	public Da setCurrentDa(Integer daId){
		Da da = dadao.findById(daId);
		return da;
	}
	
	public List<Map> getCurrentDa(Da da){
		List<Da> list = new ArrayList<Da>();
		list.add(da);
		return convert(list);
	}
	
	public List<Map> getDaByIsp(Integer ispId){
		List<Ca> calist = new CaDAO().getCAbyISP(ispId);
		List<Da> result = new ArrayList<Da>();
		for(Ca ca:calist){
			List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
			for(Da da:dalist){
				result.add(da);
			}
		}
		return convert(result);
	}
	
	public String getCASequencebyDA(Integer daId){
		GetCAforExecute gc = new GetCAforExecute();
		String layout = gc.getCaSequence(daId);
		return layout;
	}
}
