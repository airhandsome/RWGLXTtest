package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Atrribute entity. @author MyEclipse Persistence Tools
 */

public class Atrribute implements java.io.Serializable {

	// Fields

	private Integer atrId;
	private Systems systems;
	private String name;
	private Float value;
	private String tag;
	private Float changes;
	public Float getChanges() {
		return changes;
	}

	public void setChanges(Float changes) {
		this.changes = changes;
	}

	private Set dsatrs = new HashSet(0);
	private Set triggerAtrs = new HashSet(0);
	private Set daatrs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Atrribute() {
	}

	/** minimal constructor */
	public Atrribute(Systems systems, String name, String tag, Float changes) {
		this.systems = systems;
		this.name = name;
		this.tag = tag;
		this.changes = changes;
	}

	/** full constructor */
	public Atrribute(Systems systems, String name, Float value, String tag, Float changes, Set dsatrs,
			Set triggerAtrs, Set daatrs) {
		this.systems = systems;
		this.name = name;
		this.value = value;
		this.tag = tag;
		this.changes = changes;
		this.dsatrs = dsatrs;
		this.triggerAtrs = triggerAtrs;
		this.daatrs = daatrs;
	}

	// Property accessors

	public Integer getAtrId() {
		return this.atrId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setAtrId(Integer atrId) {
		this.atrId = atrId;
	}

	public Systems getSystems() {
		return this.systems;
	}

	public void setSystems(Systems systems) {
		this.systems = systems;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Set getDsatrs() {
		return this.dsatrs;
	}

	public void setDsatrs(Set dsatrs) {
		this.dsatrs = dsatrs;
	}

	public Set getTriggerAtrs() {
		return this.triggerAtrs;
	}

	public void setTriggerAtrs(Set triggerAtrs) {
		this.triggerAtrs = triggerAtrs;
	}

	public Set getDaatrs() {
		return this.daatrs;
	}

	public void setDaatrs(Set daatrs) {
		this.daatrs = daatrs;
	}

}