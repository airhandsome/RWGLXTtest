package com.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import com.domain.Systems;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AtrributeService;
import com.service.SystemService;
import com.service.TypeService;


public class TypeAction extends ActionSupport{
	
	private JSONArray responseJson;
	private TypeService typeservice = new TypeService();
	
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	public TypeService getTypeservice() {
		return typeservice;
	}
	public void setTypeservice(TypeService typeservice) {
		this.typeservice = typeservice;
	}
	
	public String getType(){
		List<Map> result = new ArrayList<Map>();
		result = typeservice.getType();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

}
