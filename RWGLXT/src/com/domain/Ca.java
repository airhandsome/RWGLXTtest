package com.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Ca entity. @author MyEclipse Persistence Tools
 */

public class Ca implements java.io.Serializable {

	// Fields

	private Integer caId;
	private EventTrigger eventTriggerByEvent;
	private Isp isp;
	private Users users;
	private EventTrigger eventTriggerByEndevent;
	private Type type;
	private String name;
	private String description;
	private String triggertype;
	private Integer priority;
	private Integer defbelong;
	private Integer ifAuto;
	private Integer ifdef;
	private String state;
	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private Timestamp excuteStarttime;
	private Timestamp excuteEndtime;
	private String terminate;
	private Set das = new HashSet(0);
	private Set dss = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ca() {
	}

	/** minimal constructor */
	public Ca(Type type, String name, String description, String triggertype,
			Integer priority, Integer defbelong, Integer ifAuto, Integer ifdef,
			String state, Timestamp excuteStarttime, Timestamp excuteEndtime) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.triggertype = triggertype;
		this.priority = priority;
		this.defbelong = defbelong;
		this.ifAuto = ifAuto;
		this.ifdef = ifdef;
		this.state = state;
		this.excuteStarttime = excuteStarttime;
		this.excuteEndtime = excuteEndtime;
	}

	/** full constructor */
	public Ca(EventTrigger eventTriggerByEvent, Isp isp, Users users,
			EventTrigger eventTriggerByEndevent, Type type, String name,
			String description, String triggertype, Integer priority,
			Integer defbelong, Integer ifAuto, Integer ifdef,
			String state, Timestamp planStarttime, Timestamp planEndtime,
			Timestamp excuteStarttime, Timestamp excuteEndtime,
			String terminate, Set das, Set dss) {
		this.eventTriggerByEvent = eventTriggerByEvent;
		this.isp = isp;
		this.users = users;
		this.eventTriggerByEndevent = eventTriggerByEndevent;
		this.type = type;
		this.name = name;
		this.description = description;
		this.triggertype = triggertype;
		this.priority = priority;
		this.defbelong = defbelong;
		this.ifAuto = ifAuto;
		this.ifdef = ifdef;
		this.state = state;
		this.planStarttime = planStarttime;
		this.planEndtime = planEndtime;
		this.excuteStarttime = excuteStarttime;
		this.excuteEndtime = excuteEndtime;
		this.terminate = terminate;
		this.das = das;
		this.dss = dss;
	}

	// Property accessors

	public Integer getCaId() {
		return this.caId;
	}

	public void setCaId(Integer caId) {
		this.caId = caId;
	}

	public EventTrigger getEventTriggerByEvent() {
		return this.eventTriggerByEvent;
	}

	public void setEventTriggerByEvent(EventTrigger eventTriggerByEvent) {
		this.eventTriggerByEvent = eventTriggerByEvent;
	}

	public Isp getIsp() {
		return this.isp;
	}

	public void setIsp(Isp isp) {
		this.isp = isp;
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

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTriggertype() {
		return this.triggertype;
	}

	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getDefbelong() {
		return this.defbelong;
	}

	public void setDefbelong(Integer defbelong) {
		this.defbelong = defbelong;
	}

	public Integer getIfAuto() {
		return this.ifAuto;
	}

	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}

	public Integer getIfdef() {
		return this.ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getTerminate() {
		return this.terminate;
	}

	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}

	public Set getDas() {
		return this.das;
	}

	public void setDas(Set das) {
		this.das = das;
	}
	
	public Set getDss() {
		return this.dss;
	}

	public void setDss(Set dss) {
		this.dss = dss;
	}

}