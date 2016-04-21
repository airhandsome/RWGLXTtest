package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.domain.Da;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SequenceService;

public class SequenceAction extends ActionSupport{
	
	private Integer daId;
	private Integer sequenceId;
	private String preType;
	private Integer pre;
	private String postType;
	private Integer post;
	private SequenceService sequenceservice = new SequenceService();
	private JSONArray responseJson;
	
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	public SequenceService getSequenceService() {
		return sequenceservice;
	}
	public void setSequenceService(SequenceService sequenceservice) {
		this.sequenceservice = sequenceservice;
	}
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getPreType() {
		return preType;
	}
	public void setPreType(String preType) {
		this.preType = preType;
	}
	public Integer getPre() {
		return pre;
	}
	public void setPre(Integer pre) {
		this.pre = pre;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public Integer getPost() {
		return post;
	}
	public void setPost(Integer post) {
		this.post = post;
	}
	public Integer getDaId() {
		return daId;
	}
	public void setDaId(Integer daId) {
		this.daId = daId;
	}
	
	public String getDAPost(){ //tested
		List<Map> result = new ArrayList<Map>();
		result = sequenceservice.getDAPost(pre);
		//if (result!=null){
			JSONArray jsonarray = JSONArray.fromObject(result);
			this.setResponseJson(jsonarray);
			return "Success";
		//}
	}
	
	public String getCAPost(){
		List<Map> result = new ArrayList<Map>();
		result = sequenceservice.getDAPost(pre);
		//if (result!=null){
			JSONArray jsonarray = JSONArray.fromObject(result);
			this.setResponseJson(jsonarray);
			return "Success";
		//}
	}
	
	public String getDAPre() throws IOException{
		Integer id = sequenceservice.getDAPre(daId);
		ServletActionContext.getResponse().getWriter().print(id);
		return null;
	}
	
}
