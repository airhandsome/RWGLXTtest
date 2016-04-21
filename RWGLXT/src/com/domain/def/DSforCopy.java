package com.domain.def;

public class DSforCopy implements Cloneable{

	private Integer DS_id;
	private Integer DS_type;
	private String name;
	private String description;
	private Integer CAbelong;
	private Integer preDA;
	private Integer trueExit;
	private Integer falseExit;
	private String code;
	private Integer ifdef;
	private Integer DSbelong;
	private String logic;
	
	private Integer change_ds_id;
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	public Integer getDS_id() {
		return DS_id;
	}

	public void setDS_id(Integer dS_id) {
		DS_id = dS_id;
	}

	public Integer getDS_type() {
		return DS_type;
	}

	public void setDS_type(Integer dS_type) {
		DS_type = dS_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCAbelong() {
		return CAbelong;
	}

	public void setCAbelong(Integer cAbelong) {
		CAbelong = cAbelong;
	}

	public Integer getPreDA() {
		return preDA;
	}

	public void setPreDA(Integer preDA) {
		this.preDA = preDA;
	}

	public Integer getTrueExit() {
		return trueExit;
	}

	public void setTrueExit(Integer trueExit) {
		this.trueExit = trueExit;
	}

	public Integer getFalseExit() {
		return falseExit;
	}

	public void setFalseExit(Integer falseExit) {
		this.falseExit = falseExit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIfdef() {
		return ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public Integer getDSbelong() {
		return DSbelong;
	}

	public void setDSbelong(Integer dSbelong) {
		DSbelong = dSbelong;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public Integer getChange_ds_id() {
		return change_ds_id;
	}

	public void setChange_ds_id(Integer change_ds_id) {
		this.change_ds_id = change_ds_id;
	}
	
}
