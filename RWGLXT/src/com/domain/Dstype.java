package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Dstype entity. @author MyEclipse Persistence Tools
 */

public class Dstype implements java.io.Serializable {

	// Fields

	private Integer dsTypeId;
	private String name;
	private Set dses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dstype() {
	}

	/** minimal constructor */
	public Dstype(Integer dsTypeId, String name) {
		this.dsTypeId = dsTypeId;
		this.name = name;
	}

	/** full constructor */
	public Dstype(Integer dsTypeId, String name, Set dses) {
		this.dsTypeId = dsTypeId;
		this.name = name;
		this.dses = dses;
	}

	// Property accessors

	public Integer getDsTypeId() {
		return this.dsTypeId;
	}

	public void setDsTypeId(Integer dsTypeId) {
		this.dsTypeId = dsTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getDses() {
		return this.dses;
	}

	public void setDses(Set dses) {
		this.dses = dses;
	}

}