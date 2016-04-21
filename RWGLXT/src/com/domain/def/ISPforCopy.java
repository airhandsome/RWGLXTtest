package com.domain.def;

import java.sql.Timestamp;
import java.util.List;

public class ISPforCopy implements Cloneable{

	private Integer ISP_id;
	private String name;
	private String description;
	private Integer priority;
	private String triggertype;
	private Integer ifdef;
	private Integer ifAuto;
	private String state;
	private Timestamp ExcuteStarttime;
	private Timestamp ExcuteEndtime;
	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private Integer event;
	private Integer uploader;
	private Integer endevent;
	private String terminate;
	private String layout;
	
	private Integer change_isp_id;
	private List<CAforCopy> calist;
	
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getTriggertype() {
		return triggertype;
	}

	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}

	public Integer getISP_id() {
		return ISP_id;
	}

	public void setISP_id(Integer iSP_id) {
		ISP_id = iSP_id;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getIfdef() {
		return ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public Integer getIfAuto() {
		return ifAuto;
	}

	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getExcuteStarttime() {
		return ExcuteStarttime;
	}

	public void setExcuteStarttime(Timestamp excuteStarttime) {
		ExcuteStarttime = excuteStarttime;
	}

	public Timestamp getExcuteEndtime() {
		return ExcuteEndtime;
	}

	public void setExcuteEndtime(Timestamp excuteEndtime) {
		ExcuteEndtime = excuteEndtime;
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

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public Integer getUploader() {
		return uploader;
	}

	public void setUploader(Integer uploader) {
		this.uploader = uploader;
	}

	public Integer getEndevent() {
		return endevent;
	}

	public void setEndevent(Integer endevent) {
		this.endevent = endevent;
	}

	public String getTerminate() {
		return terminate;
	}

	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}

	public Integer getChange_isp_id() {
		return change_isp_id;
	}

	public void setChange_isp_id(Integer change_isp_id) {
		this.change_isp_id = change_isp_id;
	}

	public List<CAforCopy> getCalist() {
		return calist;
	}

	public void setCalist(List<CAforCopy> calist) {
		this.calist = calist;
	}

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
