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
import com.service.DSTypeService;



public class DSTypeAction extends ActionSupport{
	private Integer dstypeId;
	private String name;
	private JSONArray responseJson;
	private DSTypeService dstypeservice = new DSTypeService();
	public Integer getDsTypeId() {
		return dstypeId;
	}
	public void setDsTypeId(Integer dstypeId) {
		this.dstypeId = dstypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	public DSTypeService getDstypeservice() {
		return dstypeservice;
	}
	public void setDstypeservice(DSTypeService dstypeservice) {
		this.dstypeservice = dstypeservice;
	}
	
	
	public String getDSType(){
		List<Map> result = new ArrayList<Map>();
		result = dstypeservice.getDSType();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
}
