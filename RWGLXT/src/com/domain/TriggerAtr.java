package com.domain;

/**
 * TriggerAtr entity. @author MyEclipse Persistence Tools
 */

public class TriggerAtr implements java.io.Serializable {

	// Fields

	private TriggerAtrId id;
	private Float value;
	private Integer symbol;

	// Constructors

	/** default constructor */
	public TriggerAtr() {
	}

	/** full constructor */
	public TriggerAtr(TriggerAtrId id, Float value, Integer symbol) {
		this.id = id;
		this.value = value;
		this.symbol = symbol;
	}

	// Property accessors

	public TriggerAtrId getId() {
		return this.id;
	}

	public void setId(TriggerAtrId id) {
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