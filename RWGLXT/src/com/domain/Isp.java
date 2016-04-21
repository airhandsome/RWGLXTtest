package com.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Isp entity. @author MyEclipse Persistence Tools
 */

public class Isp implements java.io.Serializable {

	// Fields

	private Integer ispId;
	private EventTrigger eventTriggerByEvent;
	private Users users;
	private EventTrigger eventTriggerByEndevent;
	private String name;
	private String desription;
	private Integer priority;
	private Integer ifdef;
	private Integer ifAuto;
	private String state;
	private Timestamp excuteStarttime;
	private Timestamp excuteEndtime;
	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private String triggertype;
	private String terminate;
	private String layout;
	

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	private Set cas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Isp() {
	}

	/** minimal constructor */
	public Isp(String name, String desription, Integer priority, Integer ifdef,
			Integer ifAuto, String state, Timestamp excuteStarttime,
			Timestamp excuteEndtime, String triggertype, String terminate,String layout) {
		this.name = name;
		this.desription = desription;
		this.priority = priority;
		this.ifdef = ifdef;
		this.ifAuto = ifAuto;
		this.state = state;
		this.excuteStarttime = excuteStarttime;
		this.excuteEndtime = excuteEndtime;
		this.triggertype = triggertype;
		this.terminate = terminate;
		this.layout = layout;
	}

	/** full constructor */
	public Isp(EventTrigger eventTriggerByEvent, Users users,
			EventTrigger eventTriggerByEndevent, String name,
			String desription, Integer priority, Integer ifdef, Integer ifAuto,
			String state, Timestamp excuteStarttime, Timestamp excuteEndtime,
			Timestamp planStarttime, Timestamp planEndtime, String triggertype,
			String terminate,String layout, Set cas) {
		this.eventTriggerByEvent = eventTriggerByEvent;
		this.users = users;
		this.eventTriggerByEndevent = eventTriggerByEndevent;
		this.name = name;
		this.desription = desription;
		this.priority = priority;
		this.ifdef = ifdef;
		this.ifAuto = ifAuto;
		this.state = state;
		this.excuteStarttime = excuteStarttime;
		this.excuteEndtime = excuteEndtime;
		this.planStarttime = planStarttime;
		this.planEndtime = planEndtime;
		this.layout = layout;
		this.triggertype = triggertype;
		this.cas = cas;
		this.terminate = terminate;
	}

	// Property accessors

	public Integer getIspId() {
		return this.ispId;
	}

	public void setIspId(Integer ispId) {
		this.ispId = ispId;
	}

	public EventTrigger getEventTriggerByEvent() {
		return this.eventTriggerByEvent;
	}

	public void setEventTriggerByEvent(EventTrigger eventTriggerByEvent) {
		this.eventTriggerByEvent = eventTriggerByEvent;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public EventTrigger getEventTriggerByEndevent() {
		return this.eventTriggerByEndevent;
	}

	public void setEventTriggerByEndevent(EventTrigger eventTriggerByEndevent) {
		this.eventTriggerByEndevent = eventTriggerByEndevent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesription() {
		return this.desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getIfdef() {
		return this.ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public Integer getIfAuto() {
		return this.ifAuto;
	}

	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getExcuteStarttime() {
		return this.excuteStarttime;
	}

	public void setExcuteStarttime(Timestamp excuteStarttime) {
		this.excuteStarttime = excuteStarttime;
	}

	public Timestamp getExcuteEndtime() {
		return this.excuteEndtime;
	}

	public void setExcuteEndtime(Timestamp excuteEndtime) {
		this.excuteEndtime = excuteEndtime;
	}

	public Timestamp getPlanStarttime() {
		return this.planStarttime;
	}

	public void setPlanStarttime(Timestamp planStarttime) {
		this.planStarttime = planStarttime;
	}

	public Timestamp getPlanEndtime() {
		return this.planEndtime;
	}

	public void setPlanEndtime(Timestamp planEndtime) {
		this.planEndtime = planEndtime;
	}

	public String getTriggertype() {
		return this.triggertype;
	}

	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}

	public String getTerminate() {
		return terminate;
	}

	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}
	
	public Set getCas() {
		return this.cas;
	}

	public void setCas(Set cas) {
		this.cas = cas;
	}

}