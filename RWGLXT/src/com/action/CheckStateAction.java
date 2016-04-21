package com.action;

import com.dao.AtrributeDAO;
import com.dao.DaDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AtrributeService;
import com.service.execute.AtrChangeService;
import com.service.execute.TimeExecute;

public class CheckStateAction extends ActionSupport{
	private Integer atrId;
	private String attrname;
	private String tag;
	private Float value;
	
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getAtrId() {
		return atrId;
	}

	public void setAtrId(Integer atrId) {
		this.atrId = atrId;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String execute(){
		return null;
	}
	
	//check time
	public String checkTimeState(){
		boolean result = false;
		System.out.println("time change");
		TimeExecute te = new TimeExecute();
		result = te.checkTime();
//		if(result)
//			return "SUCCESS";
//		else
//			return "FAIL";
		return "Success";
	}
	
	//change attribute's value and change the affected ca/da state
	public String changeAttributeValue(){
		AtrChangeService acs = new AtrChangeService();
		AtrributeService as = new AtrributeService();
		Integer changeid = as.getAtrByTag(tag);
		System.out.println("Attribute change");
		if(changeid!=0)
			acs.atrExecute(tag, value);
//		acs.atrExecute(10, (float) 1.0);
		return "Success";
	}
	
	public String test(){
		DaDAO dadao = new DaDAO();
		dadao.test();
		return "Success";
	}
}
