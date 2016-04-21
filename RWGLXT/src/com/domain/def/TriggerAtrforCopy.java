package com.domain.def;

public class TriggerAtrforCopy {
	private Integer Atr_id;
	private Integer trigger_id;
	private Float value;
	private Integer symbol;
	
	public Integer getAtr_id() {
		return Atr_id;
	}
	public void setAtr_id(Integer atr_id) {
		Atr_id = atr_id;
	}
	public Integer getTrigger_id() {
		return trigger_id;
	}
	public void setTrigger_id(Integer trigger_id) {
		this.trigger_id = trigger_id;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Integer getSymbol() {
		return symbol;
	}
	public void setSymbol(Integer symbol) {
		this.symbol = symbol;
	}
}
