package com.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import com.domain.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.LoginService;

public class LoginAction extends ActionSupport {
	// private LoginService loginservice;
	private String username;
	private String password;
	private LoginService loginservice = new LoginService();

	private JSONArray responseJson;

	public JSONArray getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
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

	public void setSession(Users user) {
		ActionContext.getContext().getSession().put("userid", user.getUserid());
		ActionContext.getContext().getSession()
				.put("username", user.getUsername());
		ActionContext.getContext().getSession().put("post", user.getPost());
		ActionContext.getContext().getSession().put("role", user.getRole());
		if(user.getFightPostion()!=null)
		ActionContext.getContext().getSession()
				.put("position", user.getFightPostion().getPostionId());
	}

	public String login() {
		System.out.println(username+password);
		ActionContext.getContext().getSession().clear();
		Users user = loginservice.login(username, password);// 璋冪敤鏁版嵁搴撴煡璇㈡柟娉曪紝楠岃瘉鐢ㄦ埛鍚嶃�瀵嗙爜
		if (user != null) {
			setSession(user);
			System.out.println(user.getRole());
			if (user.getRole().equals("任务库管理员")) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("msg", "admin");
				JSONArray jsonarray = JSONArray.fromObject(map);
				this.setResponseJson(jsonarray);
				return "Success";
			} else if (user.getRole().equals("任务编辑人员")) {
				if (user.getUsername().equals("jianzhang")) {
					// jianzhang page
					Map<String, String> map = new HashMap<String, String>();
					map.put("msg", "jianzhang");
					JSONArray jsonarray = JSONArray.fromObject(map);
					this.setResponseJson(jsonarray);
					return "Success";
				} else if (user.getUsername().contains("fujianzhang")) {
					// fujianzhang page
					Map<String, String> map = new HashMap<String, String>();
					map.put("msg", "fujianzhang");
					JSONArray jsonarray = JSONArray.fromObject(map);
					this.setResponseJson(jsonarray);
					return "Success";
				} else {
					// bumenzhang page
					Map<String, String> map = new HashMap<String, String>();
					map.put("msg", "bumenzhang");
					JSONArray jsonarray = JSONArray.fromObject(map);
					this.setResponseJson(jsonarray);
					return "Success";
				}
			} else if (user.getRole().equals("任务执行员")) {
				// zhixing page
				Map<String, String> map = new HashMap<String, String>();
				map.put("msg", "executor");
				JSONArray jsonarray = JSONArray.fromObject(map);
				this.setResponseJson(jsonarray);
				return "Success";
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("msg", "fail");
				JSONArray jsonarray = JSONArray.fromObject(map);
				this.setResponseJson(jsonarray);
				return "Success";
			}
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", "fail");
			JSONArray jsonarray = JSONArray.fromObject(map);
			this.setResponseJson(jsonarray);
			return "Success";
		}
	}
	
	public String logout(){
		ActionContext.getContext().getSession().clear();
		System.out.println("logout");
		return "Success";
	}
}