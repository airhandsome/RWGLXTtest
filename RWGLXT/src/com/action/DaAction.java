package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import com.domain.Da;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.DAService;
import com.service.TriggerAtrService;
import com.service.execute.ExecuteDaService;

public class DaAction extends ActionSupport  {
	
	private Integer daId;
	private Integer eventTriggerByEventId;
	private Integer fightPostionId;
	private Integer usersId;
	private Integer caId;
	private Integer eventTriggerByEndEventId;
	private Integer typeId;
	private Integer dsId;
	private Integer defbelong;
	private String name;
	private String description;
	private String triggertype;
	private Integer priority;
	private String state;
	private Integer ifAuto;
	private Integer ifdef;
	private Timestamp planStart;
	private Timestamp planDuration;
	private Timestamp planEnd;
	private String terminate;
	private Timestamp excuteStart;
	private Timestamp excuteEnd;
	private String code;
	private String nodename;
	private Integer ispId;
	
	public Integer getIspId() {
		return ispId;
	}
	public void setIspId(Integer ispId) {
		this.ispId = ispId;
	}

	private Integer[] attribute;
	private String eventlogic;
	private String eventcode;
	private String[] eventatr;
	private String endeventlogic;
	private String endeventcode;
	private String[] endeventatr;
	private Integer[] preDA;
	private Integer preCA;
	
	

	private DAService daservice = new DAService();
	private ExecuteDaService executedaservice = new ExecuteDaService();
	private JSONArray responseJson;
	

	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public Integer[] getPreDA() {
		return preDA;
	}
	public void setPreDA(Integer[] preDA) {
		this.preDA = preDA;
	}
	public JSONArray getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}
	public DAService getDaservice() {
		return daservice;
	}
	public void setDaservice(DAService daservice) {
		this.daservice = daservice;
	}
	public Integer getDaId() {
		return daId;
	}
	public void setDaId(Integer daId) {
		this.daId = daId;
	}
	public Integer getEventTriggerByEventId() {
		return eventTriggerByEventId;
	}
	public void setEventTriggerByEventId(Integer eventTriggerByEventId) {
		this.eventTriggerByEventId = eventTriggerByEventId;
	}
	public Integer getFightPostionId() {
		return fightPostionId;
	}
	public void setFightPostionId(Integer fightPostionId) {
		this.fightPostionId = fightPostionId;
	}
	public Integer getUsersId() {
		return usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public Integer getCaId() {
		return caId;
	}
	public void setCaId(Integer caId) {
		this.caId = caId;
	}
	public Integer getEventTriggerByEndEventId() {
		return eventTriggerByEndEventId;
	}
	public void setEventTriggerByEndEventId(Integer eventTriggerByEndEventId) {
		this.eventTriggerByEndEventId = eventTriggerByEndEventId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getDsId() {
		return dsId;
	}
	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}
	public Integer getDefbelong() {
		return defbelong;
	}
	public void setDefbelong(Integer defbelong) {
		this.defbelong = defbelong;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public Timestamp getPlanStart() {
		return planStart;
	}
	public void setPlanStart(Timestamp planStart) {
		this.planStart = planStart;
	}
	public Timestamp getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(Timestamp planDuration) {
		this.planDuration = planDuration;
	}
	public Timestamp getPlanEnd() {
		return planEnd;
	}
	public void setPlanEnd(Timestamp planEnd) {
		this.planEnd = planEnd;
	}
	public String getTerminate() {
		return terminate;
	}
	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}
	public Timestamp getExcuteStart() {
		return excuteStart;
	}
	public void setExcuteStart(Timestamp excuteStart) {
		this.excuteStart = excuteStart;
	}
	public Timestamp getExcuteEnd() {
		return excuteEnd;
	}
	public void setExcuteEnd(Timestamp excuteEnd) {
		this.excuteEnd = excuteEnd;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer[] getAttribute() {
		return attribute;
	}
	public void setAttribute(Integer[] attribute) {
		this.attribute = attribute;
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
	public Integer getPreCA() {
		return preCA;
	}
	public void setPreCA(Integer preCA) {
		this.preCA = preCA;
	}
	
	public String getExe(){                              //tested
		List<Map> result = new ArrayList<Map>();
		result = daservice.getExeDA(fightPostionId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getDAbyType(){
		List<Map> result = new ArrayList<Map>();
		result = daservice.getDAbyType(typeId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getDAbyCA(){
		List<Map> result = new ArrayList<Map>();
		result = daservice.getDAbyCa(caId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getDAbyState(){
		List<Map> result = new ArrayList<Map>();
		result = daservice.getDAbyState(state);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getDAbyId() throws UnsupportedEncodingException{
		List<Map> result = new ArrayList<Map>();
		result = daservice.getDAbyId(daId);
		if(ActionContext.getContext().getSession().containsKey("exeDa"))
			ActionContext.getContext().getSession().remove("exeDa");
		ActionContext.getContext().getSession().put("exeDa", daId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	//////!@#
	private String attrid;
	private String symbs;
	private String vals;
	public ExecuteDaService getExecutedaservice() {
		return executedaservice;
	}
	public void setExecutedaservice(ExecuteDaService executedaservice) {
		this.executedaservice = executedaservice;
	}
	public String getAttrid() {
		return attrid;
	}
	public void setAttrid(String attrid) {
		this.attrid = attrid;
	}
	public String getSymbs() {
		return symbs;
	}
	public void setSymbs(String symbs) {
		this.symbs = symbs;
	}
	public String getVals() {
		return vals;
	}
	public void setVals(String vals) {
		this.vals = vals;
	}
	public String getLogics() {
		return logics;
	}
	public void setLogics(String logics) {
		this.logics = logics;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	private String logics;
	private Integer status;
	public String getTriggersbyId() throws UnsupportedEncodingException{
		List<Map> result = new ArrayList<Map>();
		System.out.println("status"+status+" daid"+daId);
		result = daservice.getTriggersbyId(daId,status);
		if(ActionContext.getContext().getSession().containsKey("exeDa"))
			ActionContext.getContext().getSession().remove("exeDa");
		ActionContext.getContext().getSession().put("exeDa", daId);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}
	public String setTriggersbyId() throws UnsupportedEncodingException{
		System.out.println("chentong111111111111111111111111");
		System.out.println("attrid"+attrid+" symbs"+symbs+" vals"+vals+" logics"+logics+" status"+status+" daid"+daId);
		daservice.setTriggersbyId(daId,attrid ,symbs ,vals ,logics,status);//0 daibiao kaishi
		return "Success";
	}
	//////
	public String insertDA() throws IOException{ 
		List<Integer> list = new ArrayList<Integer>();
		if(preDA!=null&&preDA.length>0){
			for(Integer i:preDA){
				list.add(i);
			}
		}
		Integer id = daservice.insert(fightPostionId, usersId, caId, typeId, dsId, defbelong, name, description, triggertype, priority, state, ifAuto, ifdef, planStart, planDuration, planEnd, terminate, excuteStart, excuteEnd, code, 
				attribute, eventlogic, eventcode, eventatr, endeventlogic, endeventcode, endeventatr, list, preCA,null);
		ServletActionContext.getResponse().getWriter().print(id);
		
		return null;	
	}
	
	public String updateDA(){      				//tested        
		List<Integer> list = new ArrayList<Integer>();
		if(preDA!=null&&preDA.length>0){
			for(Integer i:preDA){
				list.add(i);
			}
		}
		daservice.update(daId, fightPostionId, usersId, caId, typeId, dsId, defbelong, name, description, triggertype, priority, state, ifAuto, ifdef, planStart, planDuration, planEnd, terminate, excuteStart, excuteEnd, code, eventTriggerByEventId, eventlogic, eventcode, eventatr, eventTriggerByEndEventId, endeventlogic, endeventcode, endeventatr, list, preCA, attribute);
		//if(flag==1)
			return "Success";
		//else
			//return "err";
	}
	
	public String deleteDA() throws IOException{      				//tested                 
		int id = daservice.delete(daId, caId);
		ServletActionContext.getResponse().getWriter().print(id);
		return null;
	}
	
	public String updateDAState(){
		daservice.updateState(daId, state);
		return "Success";	
	}
	
	public String daStart(){
		executedaservice.daStart(daId);
		return "Success";
	}
	
	public String daFinish(){
		executedaservice.daFinish(daId);
		return "Success";
	}
	
	//get da for execute
	public String getEDA(){
		Integer positionId =(Integer) ActionContext.getContext().getSession().get("position");
		//session position
		JSONArray jsonarray = JSONArray.fromObject(executedaservice.getDaByPosition("Executing", positionId));
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String getPDA(){
		Integer positionId =(Integer) ActionContext.getContext().getSession().get("position");
		//session position
		JSONArray jsonarray = JSONArray.fromObject(executedaservice.getDaByPosition("AffirmPending", positionId));
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	//wait action, get state trigger da's attribute information
	public String getEventTriggerByDaId(){
		TriggerAtrService triggerAtrService = new TriggerAtrService();		
		JSONArray jsonarray = JSONArray.fromObject(triggerAtrService.getDaTriggerInfo(daId));
		this.setResponseJson(jsonarray);
		return "Success";
	}
	
	public String executeOPC(){
	//	executedaservice.sendOPC(daId);
		return "Success";
	}
	
	public String getPreDAbyId(){
		List<Map> list = daservice.getPreDAbyId(daId);
		JSONArray j = JSONArray.fromObject(list);
		this.setResponseJson(j);
		return "Success";
	}
	
	public String getPostDAbyId(){
		List<Map> list = daservice.getPostDAbyId(daId);
		JSONArray j = JSONArray.fromObject(list);
		this.setResponseJson(j);
		return "Success";
	}
	
	public String getCurrDa(){
	//	ServletActionContext.getResponse().setHeader("Access-Control-Allow-Origin", "*");
//		System.out.println("get current da");
		Da da = new Da();
		if(ActionContext.getContext().getSession().containsKey("currentDa"))
			 da = (Da)ActionContext.getContext().getSession().get("currentDa");
		List<Map> list = daservice.getCurrentDa(da);
		JSONArray j = JSONArray.fromObject(list);
		this.setResponseJson(j);
		return "Success";
	}
	
	public String setCurrentDa(){
		Da da = new Da();
	//	ServletActionContext.getResponse().setHeader("Access-Control-Allow-Origin", "*");
		da = daservice.setCurrentDa(daId);
		if(ActionContext.getContext().getSession().containsKey("currentDa"))
			ActionContext.getContext().getSession().remove("currentDa");
		ActionContext.getContext().getSession().put("currentDa", da);
		return "Success";
	}
	//!@#
//	public  Timestamp getDaExcuteStartById(){
	public  String getDaExcuteStartById(){
		Da da = new Da();
		da = daservice.setCurrentDa(daId);
		 Timestamp  excuteStart = da.getExcuteStart();
		 String node_name = da.getName();
			System.out.println(daId+node_name);
//		List<Timestamp> list = new ArrayList<Timestamp>();
//		list.add(excuteStart);
			List<String> list = new ArrayList<String>();
			list.add(excuteStart.toString());
		JSONArray j = JSONArray.fromObject(list);
		this.setResponseJson(j);
		return "Success";
	}
	public  String getDaExcuteEndById(){
		Da da = new Da();
		da = daservice.setCurrentDa(daId);
		 Timestamp  excuteEnd = da.getExcuteEnd();
			List<String> list = new ArrayList<String>();
		list.add(excuteEnd.toString());
		JSONArray j = JSONArray.fromObject(list);
		this.setResponseJson(j);
		return "Success";
	}
	
	public String getDaByIsp(){
		JSONArray j = JSONArray.fromObject(daservice.getDaByIsp(ispId));
		this.setResponseJson(j);
		return "Success";
	}
	
	public String getCASequencebyDA() throws IOException{
//		ServletActionContext.getResponse().setHeader("Access-Control-Allow-Origin", "*");
//		ActionContext.getContext().getSession().put("exeDa", 906);
		
		Integer daid = null;
		if(ActionContext.getContext().getSession().containsKey("exeDa"))
			daid = (Integer)ActionContext.getContext().getSession().get("exeDa");
		String title = " ";
		title = "{\"title\":\"" + title + "\"}";
		String layout = title;
		if(daid!=null){
			layout = daservice.getCASequencebyDA(daid);
			List<Map> list = new ArrayList<Map>();
			Map map = new HashMap<String,Object>();
			map.put("layout", layout);
			list.add(map);
			JSONArray jsonarray = JSONArray.fromObject(list);
			this.setResponseJson(jsonarray);
		}
		return "Success";
	}
}
