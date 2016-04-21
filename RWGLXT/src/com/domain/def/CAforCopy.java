package com.domain.def;

import java.sql.Timestamp;
import java.util.List;

public class CAforCopy implements Cloneable{

	private Integer CA_id;
	private Integer type;
	private String name;
	private String description;
	private String triggertype;
	private Integer priority;
	private Integer defbelong;
	private Integer ISPbelong;
	private Integer ifAuto;
	private Integer ifdef;
	private String state;
	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private String terminate;
	private Timestamp excuteStarttime;
	private Timestamp excuteEndtime;
	private Integer uploader;
	private Integer event;
	private Integer endevent;
	
	private Integer change_ca_id;

	private List<DAforCopy> dalist;
	
	public Integer getCA_id() {
		return CA_id;
	}


	public void setCA_id(Integer cA_id) {
		CA_id = cA_id;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
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


	public Integer getDefbelong() {
		return defbelong;
	}


	public void setDefbelong(Integer defbelong) {
		this.defbelong = defbelong;
	}


	public Integer getISPbelong() {
		return ISPbelong;
	}


	public void setISPbelong(Integer iSPbelong) {
		ISPbelong = iSPbelong;
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


	public String getTerminate() {
		return terminate;
	}


	public void setTerminate(String terminate) {
		this.terminate = terminate;
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


	public Integer getUploader() {
		return uploader;
	}


	public void setUploader(Integer uploader) {
		this.uploader = uploader;
	}


	public Integer getEvent() {
		return event;
	}


	public void setEvent(Integer event) {
		this.event = event;
	}


	public Integer getEndevent() {
		return endevent;
	}


	public void setEndevent(Integer endevent) {
		this.endevent = endevent;
	}


	public Integer getChange_ca_id() {
		return change_ca_id;
	}


	public void setChange_ca_id(Integer change_ca_id) {
		this.change_ca_id = change_ca_id;
	}


	public List<DAforCopy> getDalist() {
		return dalist;
	}


	public void setDalist(List<DAforCopy> dalist) {
		this.dalist = dalist;
	}


	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
