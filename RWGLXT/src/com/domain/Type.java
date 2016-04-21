package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Type entity. @author MyEclipse Persistence Tools
 */

public class Type implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private Set cas = new HashSet(0);
	private Set userTypes = new HashSet(0);
	private Set das = new HashSet(0);

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String name) {
		this.name = name;
	}

	/** full constructor */
	public Type(String name, Set cas, Set userTypes, Set das) {
		this.name = name;
		this.cas = cas;
		this.userTypes = userTypes;
		this.das = das;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getCas() {
		return this.cas;
	}

	public void setCas(Set cas) {
		this.cas = cas;
	}

	public Set getUserTypes() {
		return this.userTypes;
	}

	public void setUserTypes(Set userTypes) {
		this.userTypes = userTypes;
	}

	public Set getDas() {
		return this.das;
	}

	public void setDas(Set das) {
		this.das = das;
	}

}