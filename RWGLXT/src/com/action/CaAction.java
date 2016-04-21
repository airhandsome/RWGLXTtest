package com.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.domain.Ca;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CAService;

public class CaAction extends ActionSupport{
	
	private Integer caId;
	private Integer eventTriggerByEventId;
	private Integer eventTriggerByEndEventId;
	private Integer ispId; 
	private Integer usersId; 
	private Integer typeId; 
	private String name; 
	private String description; 
	private String triggertype; 
	private Integer priority; 
	private Timestamp time;
	private Integer defbelong; 
	private Integer ifAuto;
	private Integer ifdef;
	private String state;
	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private Timestamp excuteStarttime;
	private Timestamp excuteEndtime; 
	private String terminate;
	private String eventlogic;
	private String eventcode;
	private String[] eventatr;
	private String endeventlogic;
	private String endeventcode;
	private String[] endeventatr;
	private Integer preDA;
	private Integer preCA;
	
	private CAService caservice = new CAService();
	
	private JSONArray responseJson;
	
	public Integer getCaId() {
		return caId;
	}
	public void setCaId(Integer caId) {
		this.caId = caId;
	}
	public Integer getEventTriggerByEventId() {
		return eventTriggerByEventId;
	}
	public void setEventTriggerByEventId(Integer eventTriggerByEventId) {
		this.eventTriggerByEventId = eventTriggerByEventId;
	}
	public Integer getEventTriggerByEndEventId() {
		return eventTriggerByEndEventId;
	}
	public void setEventTriggerByEndEventId(Integer eventTriggerByEndEventId) {
		this.eventTriggerByEndEventId = eventTriggerByEndEventId;
	}
	public Integer getIspId() {
		return ispId;
	}
	public void setIspId(Integer ispId) {
		this.ispId = ispId;
	}
	public Integer getUsersId() {
		return usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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
	public String getTriggertype() {
		return triggertype;
	}
	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getDefbelong() {
		return defbelong;
	}
	public void setDefbelong(Integer defbelong) {
		this.defbelong = defbelong;
	}
	public Integer getIfAuto() {
		return ifAuto;
	}
	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}
	public Integer getIfdef() {
		return ifdef;
	}
	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getPlanStarttime() {
		return planStarttime;
	}
	public void setPlanStarttime(Timestamp planStarttime) {
		this.planStarttime = planStarttime;
	}
	public Timestamp getPlanEndtime() {
		return planEndtime;
	}
	public void setPlanEndtime(Timestamp planEndtime) {
		this.planEndtime = planEndtime;
	}
	public Timestamp getExcuteStarttime() {
		return excuteStarttime;
	}
	public void setExcuteStarttime(Timestamp excuteStarttime) {
		this.excuteStarttime = excuteStarttime;
	}
	public Timestamp getExcuteEndtime() {
		return excuteEndtime;
	}
	public void setExcuteEndtime(Timestamp excuteEndtime) {
		this.excuteEndtime = excuteEndtime;
	}
	public String getTerminate() {
		return terminate;
	}
	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}
	public String getEventlogic() {
		return eventlogic;
	}
	public void setEventlogic(String eventlogic) {
		this.eventlogic = eventlogic;
	}
	public String getEventcode() {
		return eventcode;
	}
	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}
	public String[] getEventatr() {
		return eventatr;
	}
	public void setEventatr(String[] eventatr) {
		this.eventatr = eventatr;
	}
	public String getEndeventlogic() {
		return endeventlogic;
	}
	public void setEndeventlogic(String endeventlogic) {
		this.endeventlogic = endeventlogic;
	}
	public String getEndeventcode() {
		return endeventcode;
	}
	public void setEndeventcode(String endeventcode) {
		this.endeventcode = endeventcode;
	}
	public String[] getEndeventatr() {
		return endeventatr;
	}
	public void setEndeventatr(String[] endeventatr) {
		this.endeventatr = endeventatr;
	}
	public Integer getPreDA() {
		return preDA;
	}
	public void setPreDA(Integer preDA) {
		this.preDA = preDA;
	}
	public Integer getPreCA() {
		return preCA;
	}
	public void setPreCA(Integer preCA) {
		this.preCA = preCA;
	}
	
	public CAService getCaservice() {
		return caservice;
	}
	public void setCaservice(CAService caservice) {
		this.caservice = caservice;
	}
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	
	
	public String insertCA() throws IOException{                    //tested
		System.out.println("DSE:"+description);
		Integer id = caservice.insert(ispId, usersId, typeId, name, description, triggertype, priority, defbelong, ifAuto, ifdef, state, planStarttime, planEndtime, excuteStarttime, excuteEndtime, terminate, eventlogic, eventcode, eventatr, endeventlogic, endeventcode, endeventatr, preDA, preCA);
		ServletActionContext.getResponse().getWriter().print(id);
		return null;
	}
	
	public String updateCA(){
		caservice.update(caId, ispId, usersId, typeId, name, description, triggertype, priority, defbelong, ifAuto, ifdef, state, planStarttime, planEndtime, excuteStarttime, excuteEndtime, terminate, eventTriggerByEventId, eventlogic, eventcode, eventatr, eventTriggerByEndEventId, endeventlogic, endeventcode, endeventatr, preDA, preCA);
		return "Success";
	}
	
	public String deleteCA(){
		caservice.delete(caId);
		return "Success";
	}
	
	public String getCAbyType(){
		List<Map> result = new ArrayList<Map>();
		result = caservice.getCAbyType(typeId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getCAbyState(){
		List<Map> result = new ArrayList<Map>();
		result = caservice.getCAbyState(state);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getCAbyId(){
		List<Map> result = new ArrayList<Map>();
		result = caservice.getCAbyId(caId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getCAbyISP(){
		List<Map> result = new ArrayList<Map>();
		result = caservice.getCAbyISP(ispId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String DAlocDS(){
		List<Integer[]> result = new ArrayList<Integer[]>();
		result = caservice.DAlocDS(caId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String updateCAState(){
		caservice.updateState(caId, state);
		return "Success";
	}
	//create a check pending ca with model id
	public String submitCA() throws IOException{
		CAService caservice = new CAService();
		Integer result = caservice.createCheckPendingCa(caId);
		ServletActionContext.getResponse().getWriter().print(result);
		return "Success";
	}
	
	public String getPendingCA(){
		CAService caservice = new CAService();
		List<Map> result = caservice.getPendingCA();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

}
