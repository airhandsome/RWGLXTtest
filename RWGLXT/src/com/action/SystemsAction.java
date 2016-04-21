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


public class SystemsAction extends ActionSupport{
	
	private String sysname;
	private Integer sysId;
	private Integer parent;
	private Integer level;
	private String type;
	private String strname;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStrname() {
		return strname;
	}
	public void setStrname(String strname) {
		this.strname = strname;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	private JSONArray responseJson;
	private SystemService sysservice = new SystemService();
	
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	public SystemService getSysservice() {
		return sysservice;
	}
	public void setSysservice(SystemService sysservice) {
		this.sysservice = sysservice;
	}
	
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public Integer getSysId() {
		return sysId;
	}
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	public String getSystem(){
		List<Map> result = new ArrayList<Map>();
		result = sysservice.getSystem();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getSystembyId(){
		//get by parent
		List<Map> result = new ArrayList<Map>();
		result = sysservice.getSystembyParent(sysId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	public String getSystembySearch(){
		List<Map> result = new ArrayList<Map>();
		result = sysservice.getSystembySearch(strname);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getSystembyOrder(){
		List<Map> result = new ArrayList<Map>();
		result = sysservice.getSystembyOrder(type);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	public String insertSys(){
		sysservice.insertSys(sysname,parent);
		return "Success";
	}
	
	public String updateSys(){
		sysservice.updateSys(sysId, sysname,parent);
		return "Success";
	}
}
