package com.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Da entity. @author MyEclipse Persistence Tools
 */

public class Da implements java.io.Serializable {

	// Fields

	private Integer daId;
	private EventTrigger eventTriggerByEvent;
	private FightPostion fightPostion;
	private Users users;
	private Ca ca;
	private EventTrigger eventTriggerByEndEvent;
	private Type type;
	private Ds ds;
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
	private Set dsesForTrueExit = new HashSet(0);
	private Set dsesForPreDa = new HashSet(0);
	private Set daatrs = new HashSet(0);
	private Set dsesForFalseExit = new HashSet(0);

	// Constructors

	/** default constructor */
	public Da() {
	}

	/** minimal constructor */
	public Da(FightPostion fightPostion, Type type, String name,
			String description, String triggertype, Integer priority,
			String state, Integer ifAuto, Integer ifdef) {
		this.fightPostion = fightPostion;
		this.type = type;
		this.name = name;
		this.description = description;
		this.triggertype = triggertype;
		this.priority = priority;
		this.state = state;
		this.ifAuto = ifAuto;
		this.ifdef = ifdef;
	}

	/** full constructor */
	public Da(EventTrigger eventTriggerByEvent, FightPostion fightPostion,
			Users users, Ca ca, EventTrigger eventTriggerByEndEvent, Type type,
			Ds ds, Integer defbelong, String name, String description,
			String triggertype, Integer priority, String state, Integer ifAuto,
			Integer ifdef, Timestamp planStart, Timestamp planDuration, Timestamp planEnd,
			String terminate, Timestamp excuteStart, Timestamp excuteEnd,String code,String nodename,
			Set dsesForTrueExit, Set dsesForPreDa, Set daatrs,
			Set dsesForFalseExit) {
		this.eventTriggerByEvent = eventTriggerByEvent;
		this.fightPostion = fightPostion;
		this.users = users;
		this.ca = ca;
		this.eventTriggerByEndEvent = eventTriggerByEndEvent;
		this.type = type;
		this.ds = ds;
		this.defbelong = defbelong;
		this.name = name;
		this.description = description;
		this.triggertype = triggertype;
		this.priority = priority;
		this.state = state;
		this.ifAuto = ifAuto;
		this.ifdef = ifdef;
		this.planStart = planStart;
		this.planDuration = planDuration;
		this.planEnd = planEnd;
		this.terminate = terminate;
		this.excuteStart = excuteStart;
		this.excuteEnd = excuteEnd;
		this.code = code;
		this.nodename = nodename;
		this.dsesForTrueExit = dsesForTrueExit;
		this.dsesForPreDa = dsesForPreDa;
		this.daatrs = daatrs;
		this.dsesForFalseExit = dsesForFalseExit;
	}

	// Property accessors

	public Integer getDaId() {
		return this.daId;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public void setDaId(Integer daId) {
		this.daId = daId;
	}

	public EventTrigger getEventTriggerByEvent() {
		return this.eventTriggerByEvent;
	}

	public void setEventTriggerByEvent(EventTrigger eventTriggerByEvent) {
		this.eventTriggerByEvent = eventTriggerByEvent;
	}

	public FightPostion getFightPostion() {
		return this.fightPostion;
	}

	public void setFightPostion(FightPostion fightPostion) {
		this.fightPostion = fightPostion;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Ca getCa() {
		return this.ca;
	}

	public void setCa(Ca ca) {
		this.ca = ca;
	}

	public EventTrigger getEventTriggerByEndEvent() {
		return this.eventTriggerByEndEvent;
	}

	public void setEventTriggerByEndEvent(EventTrigger eventTriggerByEndEvent) {
		this.eventTriggerByEndEvent = eventTriggerByEndEvent;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Ds getDs() {
		return this.ds;
	}

	public void setDs(Ds ds) {
		this.ds = ds;
	}

	public Integer getDefbelong() {
		return this.defbelong;
	}

	public void setDefbelong(Integer defbelong) {
		this.defbelong = defbelong;
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

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Timestamp getPlanStart() {
		return this.planStart;
	}

	public void setPlanStart(Timestamp planStart) {
		this.planStart = planStart;
	}

	public Timestamp getPlanDuration() {
		return this.planDuration;
	}

	public void setPlanDuration(Timestamp planDuration) {
		this.planDuration = planDuration;
	}

	public Timestamp getPlanEnd() {
		return this.planEnd;
	}

	public void setPlanEnd(Timestamp planEnd) {
		this.planEnd = planEnd;
	}

	public String getTerminate() {
		return this.terminate;
	}

	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}

	public Timestamp getExcuteStart() {
		return this.excuteStart;
	}

	public void setExcuteStart(Timestamp excuteStart) {
		this.excuteStart = excuteStart;
	}

	public Timestamp getExcuteEnd() {
		return this.excuteEnd;
	}

	public void setExcuteEnd(Timestamp excuteEnd) {
		this.excuteEnd = excuteEnd;
	}

	public Set getDsesForTrueExit() {
		return this.dsesForTrueExit;
	}

	public void setDsesForTrueExit(Set dsesForTrueExit) {
		this.dsesForTrueExit = dsesForTrueExit;
	}

	public Set getDsesForPreDa() {
		return this.dsesForPreDa;
	}

	public void setDsesForPreDa(Set dsesForPreDa) {
		this.dsesForPreDa = dsesForPreDa;
	}

	public Set getDaatrs() {
		return this.daatrs;
	}

	public void setDaatrs(Set daatrs) {
		this.daatrs = daatrs;
	}

	public Set getDsesForFalseExit() {
		return this.dsesForFalseExit;
	}

	public void setDsesForFalseExit(Set dsesForFalseExit) {
		this.dsesForFalseExit = dsesForFalseExit;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}