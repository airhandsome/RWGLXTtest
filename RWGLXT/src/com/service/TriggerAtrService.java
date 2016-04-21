package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.DaDAO;
import com.dao.TriggerAtrDAO;
import com.domain.Da;
import com.domain.TriggerAtr;
import com.domain.extr.DaTriggerInfor;

public class TriggerAtrService {
	TriggerAtrDAO triggeratrdao = new TriggerAtrDAO();
	
	public List<Map> convert(DaTriggerInfor dti){
		List<Map> list = new ArrayList<Map>();
		if(dti!=null){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("daId", dti.getDaId());
			map.put("StartTriggerId", dti.getStartTriggerId());
			map.put("StartattributeId", dti.getStartattributeId());
			map.put("StartattributeName", dti.getStartattributeName());
			map.put("Stratsymbol", dti.getStartsymbol());
			map.put("Startvalue", dti.getStartvalue());
			map.put("EndTriggerId", dti.getEndTriggerId());
			map.put("EndattributeId", dti.getEndattributeId());
			map.put("EndattributeName", dti.getEndattributeName());
			map.put("Endsymbol", dti.getEndsymbol());
			map.put("Endvalue", dti.getEndvalue());
			list.add(map);
		}
		return list;
	}
	
	public void insertAnalyse(String[] eventatr, Integer triggerId){
		System.out.println(eventatr.length);
		for (int i =0; i<eventatr.length; i++){
			String[] a = eventatr[i].split(",");
			triggeratrdao.insert(triggerId, Integer.parseInt(a[0]), Float.parseFloat(a[1]), Integer.parseInt(a[2]));
		}
	}
	
	public void updateAnalyse(String[] eventatr, Integer triggerId){
		for (int i =0; i<eventatr.length; i++){
			String[] a = eventatr[i].split(",");
			triggeratrdao.update(triggerId, Integer.parseInt(a[0]), Float.parseFloat(a[1]), Integer.parseInt(a[2]));
		}
	}
	
	public List<Map> getDaTriggerInfo(Integer daId){
		DaTriggerInfor dti = new DaTriggerInfor();
		DaDAO dadao = new DaDAO();
		Da da = dadao.findById(daId);
		dti.setDaId(daId);
		if(da.getEventTriggerByEvent()!=null){
			dti.setStartTriggerId(da.getEventTriggerByEvent().getTriggerId());
			List<TriggerAtr> triggeratr = triggeratrdao.getTriggerAtr(dti.getStartTriggerId());
			//when da has one trigger event!!!
			for(TriggerAtr ta: triggeratr){
				if(ta.getId()!=null&&ta.getId().getAtrribute()!=null){
					dti.setStartattributeId(ta.getId().getAtrribute().getAtrId());
					dti.setStartattributeName(ta.getId().getAtrribute().getName());
				}
				dti.setStartsymbol(ta.getSymbol());
				dti.setStartvalue(ta.getValue());
			}
		}
		if(da.getEventTriggerByEndEvent()!=null){
			dti.setEndTriggerId(da.getEventTriggerByEndEvent().getTriggerId());
		}
		return convert(dti);
	}
}
