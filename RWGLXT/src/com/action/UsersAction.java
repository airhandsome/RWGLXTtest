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
import com.domain.Users;
import com.domain.extr.UserInfor;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AtrributeService;
import com.service.UsersService;

public class UsersAction extends ActionSupport{
	private Integer usersId;
	private String username; 
	private String password; 
	private String department; 
	private String role; 
	private String post; 
	private Integer positionId;
	private String strname;
	private String type;	
	private JSONArray responseJson;
	
	private UsersService usersservice = new UsersService();

	public String getStrname() {
		return strname;
	}

	public void setStrname(String strname) {
		this.strname = strname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public JSONArray getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	
	public String insertUser() throws IOException{ 
		Integer id = usersservice.insert(username, password, department, role, post, positionId);
		ServletActionContext.getResponse().getWriter().print(id);
		
		return null;	
	}
	
	public String deleteUser(){
		//List<Map> result = new ArrayList<Map>();
		usersservice.delete(usersId);
		return "Success";
	}
	
	public String getUser(){
		JSONArray jsonarray = JSONArray.fromObject(usersservice.getUser());
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String updateUser(){
		//List<Map> result = new ArrayList<Map>();
		usersservice.update(usersId, username, password, department, role, post, positionId);
		return "Success";
	}
	public String getUserbySearch(){
		JSONArray jsonarray = JSONArray.fromObject(usersservice.getUserbySearch(strname));
		this.setResponseJson(jsonarray);
		return "Success";
	}
	public String getUserbyOrder(){
		JSONArray jsonarray = JSONArray.fromObject(usersservice.getUserbyOrder(type));
		this.setResponseJson(jsonarray);
		return "Success";
	}
}
