package com.domain;

/**
 * Dsatr entity. @author MyEclipse Persistence Tools
 */

public class Dsatr implements java.io.Serializable {

	// Fields

	private DsatrId id;
	private Float value;
	private Integer symbol;

	// Constructors

	/** default constructor */
	public Dsatr() {
	}

	/** full constructor */
	public Dsatr(DsatrId id, Float value, Integer symbol) {
		this.id = id;
		this.value = value;
		this.symbol = symbol;
	}

	// Property accessors

	public DsatrId getId() {
		return this.id;
	}

	public void setId(DsatrId id) {
		this.id = id;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Integer getSymbol() {
		return this.symbol;
	}

	public void setSymbol(Integer symbol) {
		this.symbol = symbol;
	}

}