package com.domain.def;

public class EventTriggerforCopy implements Cloneable{
	private Integer trigger_id;
	private String logic;
	private String code;
	
	private Integer change_trigger_id;

	public Integer getTrigger_id() {
		return trigger_id;
	}

	public void setTrigger_id(Integer trigger_id) {
		this.trigger_id = trigger_id;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getChange_trigger_id() {
		return change_trigger_id;
	}

	public void setChange_trigger_id(Integer change_trigger_id) {
		this.change_trigger_id = change_trigger_id;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	} 
}
