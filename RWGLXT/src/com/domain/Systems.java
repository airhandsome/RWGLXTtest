package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Systems entity. @author MyEclipse Persistence Tools
 */

public class Systems implements java.io.Serializable {

	// Fields

	private Integer systemId;
	private String name;
	private Integer parent;
	private Integer level;
	private Set atrributes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Systems() {
	}

	/** minimal constructor */
	public Systems(String name) {
		this.name = name;
	}

	/** full constructor */
	public Systems(String name, Integer parent, Integer level, Set atrributes) {
		this.name = name;
		this.parent = parent;
		this.level = level;
		this.atrributes = atrributes;
	}

	// Property accessors

	public Integer getSystemId() {
		return this.systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Set getAtrributes() {
		return this.atrributes;
	}

	public void setAtrributes(Set atrributes) {
		this.atrributes = atrributes;
	}

}