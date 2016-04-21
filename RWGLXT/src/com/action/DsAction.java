package com.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.service.DSService;

public class DsAction extends ActionSupport{
	
	private Integer dsId;
	private Integer daByTrueExitId;
	private Integer daByFalseExitId;
	private Integer daByPreDaId;
	private Integer caId;
	private Integer dstypeId;
	private String name;
	private String description;
	private String code;
	private Integer ifdef;
	private Integer dsbelong;
	private String logic;
	private String[] attribute;
	
	private DSService dsservice = new DSService();
	private JSONArray responseJson;
	
	public Integer getDsId() {
		return dsId;
	}
	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}
	public Integer getDaByTrueExitId() {
		return daByTrueExitId;
	}
	public void setDaByTrueExitId(Integer daByTrueExitId) {
		this.daByTrueExitId = daByTrueExitId;
	}
	public Integer getDaByFalseExitId() {
		return daByFalseExitId;
	}
	public void setDaByFalseExitId(Integer daByFalseExitId) {
		this.daByFalseExitId = daByFalseExitId;
	}
	public Integer getDaByPreDaId() {
		return daByPreDaId;
	}
	public void setDaByPreDaId(Integer daByPreDaId) {
		this.daByPreDaId = daByPreDaId;
	}
	public Integer getCaId() {
		return caId;
	}
	public void setCaId(Integer caId) {
		this.caId = caId;
	}
	public Integer getDstypeId() {
		return dstypeId;
	}
	public void setDstypeId(Integer dstypeId) {
		this.dstypeId = dstypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getIfdef() {
		return ifdef;
	}
	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}
	public Integer getDsbelong() {
		return dsbelong;
	}
	public void setDsbelong(Integer dsbelong) {
		this.dsbelong = dsbelong;
	}
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String[] getAttribute() {
		return attribute;
	}
	public void setAttribute(String[] attribute) {
		this.attribute = attribute;
	}
	public DSService getDsservice() {
		return dsservice;
	}
	public void setDsservice(DSService dsservice) {
		this.dsservice = dsservice;
	}
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	
	public String insertDS() throws IOException{
		Integer id = dsservice.insert(daByTrueExitId, daByFalseExitId, daByPreDaId, caId, dstypeId, name, description, code, ifdef, dsbelong, logic, attribute);
		ServletActionContext.getResponse().getWriter().print(id);
		return null;
	}
	
	public String updateDS(){
		dsservice.update(dsId, daByTrueExitId, daByFalseExitId, daByPreDaId, caId, dstypeId, name, description, code, ifdef, dsbelong, logic, attribute);
		return "Success";
	}
	
	public String deleteDS(){
		dsservice.delete(dsId);
		return "Success";
	}
	
	public String getDSbyCA(){
		List<Map> result = new ArrayList<Map>();
		result = dsservice.getDSbyCA(caId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getDSbyType(){
		List<Map> result = new ArrayList<Map>();
		result = dsservice.getDSbyType(dstypeId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getDSbyId(){
		List<Map> result = new ArrayList<Map>();
		result = dsservice.getDSbyId(dsId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	
}
