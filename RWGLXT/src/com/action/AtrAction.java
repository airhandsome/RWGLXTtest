package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import com.domain.Systems;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AtrributeService;



public class AtrAction extends ActionSupport{
	private Integer atrId;
	private Integer systemId;
	private String name;
	private String tag;
	private String strname;
	private JSONArray responseJson;
	private AtrributeService atrservice = new AtrributeService();
	
	public String getStrname() {
		return strname;
	}
	public void setStrname(String strname) {
		this.strname = strname;
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
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
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
	public AtrributeService getAtrservice() {
		return atrservice;
	}
	public void setAtrservice(AtrributeService atrservice) {
		this.atrservice = atrservice;
	}
	
	public String getAllAtr(){
		List<Map> result = new ArrayList<Map>();
		result = atrservice.getAtr();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getAtrBySystem(){
		List<Map> result = new ArrayList<Map>();
		result = atrservice.getAtrBySystem(systemId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getAtrbyId(){
		List<Map> result = new ArrayList<Map>();
		result = atrservice.getAtrbyId(atrId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	public String getAtrbySearch(){
		List<Map> result = new ArrayList<Map>();
		result = atrservice.getAtrBySearch(strname);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "success";
	}
	public String insertAtr() throws IOException{ 
		Integer id = atrservice.insert(systemId, name, tag);
		ServletActionContext.getResponse().getWriter().print(id);
		
		return null;	
	}
	
	public String deleteAtr(){
		List<Map> result = new ArrayList<Map>();
		atrservice.delete(atrId);
		return "Success";
	}
	
	public String updateAtr(){
		System.out.println(tag);
		atrservice.update(atrId, name, tag, systemId);
		return "Success";
	}
   
  public String uploadExc(){
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/WEB-INF/upload");
		String result = "";
		System.out.println(path);
		try{
			MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
			String filename = wrapper.getFileNames("attrfile")[0];
			System.out.println(filename);
			File file = wrapper.getFiles("attrfile")[0];
			String fileExt = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
			System.out.println(fileExt);
			if(!fileExt.equals("xls")){
				System.out.println("1");
				result = "fail";
				ServletActionContext.getResponse().getWriter().print(result);
			}else{
				System.out.println(2);
				InputStream in = new FileInputStream(file);
				File uploadFile = new File(path+"\\"+filename);
				OutputStream out = new FileOutputStream(uploadFile);
				byte[] buffer = new byte[1024];
				int length;
				while((length=in.read(buffer))>0){
					out.write(buffer);
				}
				in.close();
				out.close();
				atrservice.uploadExc(path, filename);
				result = "success";
				ServletActionContext.getResponse().getWriter().print(result);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
