package com.dao.def;


import com.dao.EventTriggerDAO;
import com.domain.EventTrigger;
import com.domain.def.EventTriggerforCopy;

public class GetEventTrigger {
	public EventTriggerforCopy getTrigger(Integer trigger_id){
		EventTriggerDAO etdao = new EventTriggerDAO();
		EventTrigger etO = new EventTrigger();
		etO = etdao.findById(trigger_id);
		EventTriggerforCopy et = new EventTriggerforCopy();

		et.setTrigger_id(trigger_id);
		et.setLogic((String)etO.getLogic());
		et.setCode((String)etO.getCode());
		
		return et;
	}
}
