package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * EventTrigger entity. @author MyEclipse Persistence Tools
 */

public class EventTrigger implements java.io.Serializable {

	// Fields

	private Integer triggerId;
	private String logic;
	private String code;
	private Set ispsForEvent = new HashSet(0);
	private Set ispsForEndevent = new HashSet(0);
	private Set casForEvent = new HashSet(0);
	private Set triggerAtrs = new HashSet(0);
	private Set dasForEvent = new HashSet(0);
	private Set dasForEndEvent = new HashSet(0);
	private Set casForEndevent = new HashSet(0);

	// Constructors

	/** default constructor */
	public EventTrigger() {
	}

	/** full constructor */
	public EventTrigger(String logic, String code, Set ispsForEvent,
			Set ispsForEndevent, Set casForEvent, Set triggerAtrs,
			Set dasForEvent, Set dasForEndEvent, Set casForEndevent) {
		this.logic = logic;
		this.code = code;
		this.ispsForEvent = ispsForEvent;
		this.ispsForEndevent = ispsForEndevent;
		this.casForEvent = casForEvent;
		this.triggerAtrs = triggerAtrs;
		this.dasForEvent = dasForEvent;
		this.dasForEndEvent = dasForEndEvent;
		this.casForEndevent = casForEndevent;
	}

	// Property accessors

	public Integer getTriggerId() {
		return this.triggerId;
	}

	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	public String getLogic() {
		return this.logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set getIspsForEvent() {
		return this.ispsForEvent;
	}

	public void setIspsForEvent(Set ispsForEvent) {
		this.ispsForEvent = ispsForEvent;
	}

	public Set getIspsForEndevent() {
		return this.ispsForEndevent;
	}

	public void setIspsForEndevent(Set ispsForEndevent) {
		this.ispsForEndevent = ispsForEndevent;
	}

	public Set getCasForEvent() {
		return this.casForEvent;
	}

	public void setCasForEvent(Set casForEvent) {
		this.casForEvent = casForEvent;
	}

	public Set getTriggerAtrs() {
		return this.triggerAtrs;
	}

	public void setTriggerAtrs(Set triggerAtrs) {
		this.triggerAtrs = triggerAtrs;
	}

	public Set getDasForEvent() {
		return this.dasForEvent;
	}

	public void setDasForEvent(Set dasForEvent) {
		this.dasForEvent = dasForEvent;
	}

	public Set getDasForEndEvent() {
		return this.dasForEndEvent;
	}

	public void setDasForEndEvent(Set dasForEndEvent) {
		this.dasForEndEvent = dasForEndEvent;
	}

	public Set getCasForEndevent() {
		return this.casForEndevent;
	}

	public void setCasForEndevent(Set casForEndevent) {
		this.casForEndevent = casForEndevent;
	}

}