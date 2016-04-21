package com.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import com.domain.FightPostion;
import com.domain.Systems;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AtrributeService;
import com.service.FightPostionService;

public class FightPostionAction extends ActionSupport{
	
	
	private Integer fightpostionId;
	private String name;
	private String department;
	private String post;
	private JSONArray responseJson;
	private String strname;
	
	public String getStrname() {
		return strname;
	}

	public void setStrname(String strname) {
		this.strname = strname;
	}

	private FightPostionService fpservice = new FightPostionService();
	
	
	public Integer getFightpostionId() {
		return fightpostionId;
	}

	public void setFightpostionId(Integer fightpostionId) {
		this.fightpostionId = fightpostionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public JSONArray getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	
	public String getFP(){
		List<Map> result = new ArrayList<Map>();
		result = fpservice.getFP();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
		
	}
	public String getFPbyOrder(String type){
		List<Map> result = new ArrayList<Map>();
		result = fpservice.getFPbyOrder(type);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
		
	}
	
	public String getFPbySearch(){
		List<Map> result = new ArrayList<Map>();
		result = fpservice.getFPbySearch(strname);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
		
	}
	public String insertFP() throws IOException{ 
		Integer id = fpservice.insert(name, department, post);
		ServletActionContext.getResponse().getWriter().print(id);
		
		return null;	
	}
	
	public String deleteFP(){
		fpservice.delete(fightpostionId);
		return "Success";
	}
	
	public String updateFP(){
		System.out.println(fightpostionId+name+department+post);
		fpservice.update(fightpostionId, name, department, post);
		return "Success";
	}
}
