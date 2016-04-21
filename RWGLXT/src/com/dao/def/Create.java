package com.dao.def;


import java.sql.Timestamp;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DaatrDAO;
import com.dao.DsDAO;
import com.dao.DsatrDAO;
import com.dao.EventTriggerDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;
import com.dao.TriggerAtrDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.EventTrigger;
import com.domain.Isp;
import com.domain.def.CAforCopy;
import com.domain.def.DAAtrforCopy;
import com.domain.def.DAforCopy;
import com.domain.def.DSAtrforCopy;
import com.domain.def.DSforCopy;
import com.domain.def.EventTriggerforCopy;
import com.domain.def.ISPforCopy;
import com.domain.def.SequenceforCopy;
import com.domain.def.TriggerAtrforCopy;

public class Create {

	//create a new isp and return it's id
	public ISPforCopy createISP(ISPforCopy isp, String name, String desc, Timestamp time) throws CloneNotSupportedException{
		Timestamp planstart = isp.getPlanStarttime();
		Timestamp planend = isp.getPlanEndtime();
		String nam = isp.getName();
		String des = isp.getDescription();
		if(name != null){
			nam = name;
		}
		if(desc != null){
			des = desc;
		}
		if(time!=null){
			int year = time.getYear();
			int month = time.getMonth();
			int dat = time.getDate();
			if (planstart!=null){			
				planstart.setYear(year);
				planstart.setMonth(month);
				planstart.setDate(dat);
			}
			if (planend!=null) {
				planend.setYear(year);
			planend.setMonth(month);
			planend.setDate(dat);	
			}			
		}
		ISPforCopy newISP = new ISPforCopy();
		newISP = (ISPforCopy) isp.clone();
		Isp ispO = new Isp();
		IspDAO ispdao = new IspDAO();
		ispO = ispdao.insertTo(isp.getEvent(), isp.getUploader(), isp.getEndevent(), nam, des, isp.getTriggertype(), isp.getPriority(), isp.getIfAuto(), isp.getIfdef(), isp.getState(), planstart, planend, isp.getExcuteStarttime(), isp.getExcuteEndtime(), isp.getTerminate(), isp.getLayout());
		
		newISP.setISP_id(ispO.getIspId());
		return newISP;
	}
	
	//create a new ca and return it's id
	public Integer createCA(CAforCopy ca){
        CaDAO cadao = new CaDAO();
        Integer newcaid = null;
        Ca caO = new Ca();
		caO = cadao.insertTo(ca.getEvent(), ca.getISPbelong(), ca.getUploader(), ca.getEndevent(), ca.getType(), ca.getName(), ca.getDescription(), ca.getTriggertype(), ca.getPriority(), ca.getDefbelong(), ca.getIfAuto(), ca.getIfdef(), ca.getState(), ca.getPlanStarttime(), ca.getPlanEndtime(), ca.getExcuteStarttime(), ca.getExcuteEndtime(), ca.getTerminate());
        newcaid = caO.getCaId();
		return newcaid;
	}
	
	//create a new da in database and return it's id
	public Integer createDA(DAforCopy da){
		System.out.println(da);
		Integer newdaid = null;
		DaDAO dadao = new DaDAO();
		Da daO = new Da();
		try{
		daO = dadao.insertTo(da.getEvent(),
				da.getPostion(), 
				da.getOpeartor(), 
				da.getCAbelong(), 
				da.getEndEvent(), 
				da.getType(),
				null, 
				da.getDefbelong(),
				da.getName(), 
				da.getDescription(), 
				da.getTriggertype(), 
				da.getPriority(), 
				da.getState(), 
				da.getIfAuto(), 
				da.getIfdef(),
				da.getPlanStart(), 
				da.getPlanDuration(),
				da.getPlanEnd(), 
				da.getTerminate(), 
				da.getExcuteStart(), 
				da.getExcuteEnd(), 
				da.getCode(),
				da.getNodename());
		}catch(Exception e){
			e.printStackTrace();
		}
		newdaid = daO.getDaId();
		
		return newdaid;
	}
	
	//create a new ds in database and return it's id
	public Integer createDS(DSforCopy ds){
		Integer newdsid = null;
		DsDAO dsdao = new DsDAO();
		Ds dsO = new Ds();
		dsO = dsdao.insertTo(ds.getTrueExit(), ds.getFalseExit(), ds.getPreDA(), ds.getCAbelong(), ds.getDS_type(), ds.getName(), ds.getDescription(), ds.getCode(), ds.getIfdef(), ds.getDSbelong(), ds.getLogic());
		newdsid = dsO.getDsId();
		return newdsid;
	}
	
	//create a new sequence
	public void createSeq(SequenceforCopy seq){
		SequenceDAO seqdao = new SequenceDAO();
		seqdao.insert(seq.getPre_type(), seq.getPre(), seq.getPost_type(), seq.getPost());
	}
	
	//create a new daatr
	public void createDAAtr(DAAtrforCopy daatr){
		DaatrDAO daatrdao = new DaatrDAO();
		daatrdao.insert(daatr.getDA_id(), daatr.getAtr_id(), daatr.getValue());
	}
	
	//create a new dsatr
	public void createDSAtr(DSAtrforCopy dsatr){
		DsatrDAO dsatrdao = new DsatrDAO();
		dsatrdao.insert(dsatr.getDS_id(), dsatr.getAtr_id(), dsatr.getSymbol(), dsatr.getValue());
	}
	
	//create a new event trigger table
	public Integer createEventTrigger(EventTriggerforCopy et){
		EventTriggerDAO etdao = new EventTriggerDAO();
		EventTrigger etO = etdao.insert(et.getLogic(), et.getCode());
		Integer trigger_id = null;
		trigger_id = etO.getTriggerId();
		return trigger_id;
	}
	
	//create a new trigger_attribute table
	public void createTriggerAtr(TriggerAtrforCopy tac){
		TriggerAtrDAO tadao = new TriggerAtrDAO();
		tadao.insert(tac.getTrigger_id(), tac.getAtr_id(), tac.getValue(), tac.getSymbol());
	}
}
