package com.domain;

/**
 * Daatr entity. @author MyEclipse Persistence Tools
 */

public class Daatr implements java.io.Serializable {

	// Fields

	private DaatrId id;
	private Float value;

	// Constructors

	/** default constructor */
	public Daatr() {
	}

	/** minimal constructor */
	public Daatr(DaatrId id) {
		this.id = id;
	}

	/** full constructor */
	public Daatr(DaatrId id, Float value) {
		this.id = id;
		this.value = value;
	}

	// Property accessors

	public DaatrId getId() {
		return this.id;
	}

	public void setId(DaatrId id) {
		this.id = id;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

}