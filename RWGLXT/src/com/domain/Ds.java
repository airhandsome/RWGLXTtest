package com.domain;

import java.util.HashSet;
import java.util.Set;


/**
 * Ds entity. @author MyEclipse Persistence Tools
 */

public class Ds implements java.io.Serializable {

	// Fields

	private Integer dsId;
	private Da daByTrueExit;
	private Da daByFalseExit;
	private Da daByPreDa;
	private Ca ca;
	private Dstype dstype;
	private String name;
	private String description;
	private String code;
	private Integer ifdef;
	private Integer dsbelong;
	private String logic;
	private Set das = new HashSet(0);
	private Set dsatrs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ds() {
	}

	/** minimal constructor */
	public Ds(Integer dsId, Da daByPreDa, Dstype dstype, String name, 
			String description, Integer ifdef, Integer dsbelong, String logic) {
		this.dsId = dsId;
		this.daByPreDa = daByPreDa;
		this.dstype = dstype;
		this.name = name;
		this.description = description;
		this.ifdef = ifdef;
		this.dsbelong = dsbelong;
		this.logic = logic;
	}

	/** full constructor */
	public Ds(Integer dsId, Da daByTrueExit, Da daByFalseExit, Da daByPreDa, Ca ca,
			Dstype dstype, String name, String description, String code,
			Integer ifdef, Integer dsbelong, String logic, Set das, Set dsatrs) {
		this.dsId = dsId;
		this.daByTrueExit = daByTrueExit;
		this.daByFalseExit = daByFalseExit;
		this.daByPreDa = daByPreDa;
		this.dstype = dstype;
		this.name = name;
		this.ca = ca;
		this.description = description;
		this.code = code;
		this.ifdef = ifdef;
		this.dsbelong = dsbelong;
		this.logic = logic;
		this.das = das;
		this.dsatrs = dsatrs;
	}

	// Property accessors

	public Integer getDsId() {
		return this.dsId;
	}

	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}

	public Da getDaByTrueExit() {
		return this.daByTrueExit;
	}

	public void setDaByTrueExit(Da daByTrueExit) {
		this.daByTrueExit = daByTrueExit;
	}

	public Da getDaByFalseExit() {
		return this.daByFalseExit;
	}

	public void setDaByFalseExit(Da daByFalseExit) {
		this.daByFalseExit = daByFalseExit;
	}

	public Da getDaByPreDa() {
		return this.daByPreDa;
	}

	public void setDaByPreDa(Da daByPreDa) {
		this.daByPreDa = daByPreDa;
	}
	
	public Ca getCa() {
		return this.ca;
	}

	public void setCa(Ca ca) {
		this.ca = ca;
	}

	public Dstype getDstype() {
		return this.dstype;
	}

	public void setDstype(Dstype dstype) {
		this.dstype = dstype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIfdef() {
		return this.ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public Integer getDsbelong() {
		return this.dsbelong;
	}

	public void setDsbelong(Integer dsbelong) {
		this.dsbelong = dsbelong;
	}

	public String getLogic() {
		return this.logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public Set getDas() {
		return this.das;
	}

	public void setDas(Set das) {
		this.das = das;
	}

	public Set getDsatrs() {
		return this.dsatrs;
	}

	public void setDsatrs(Set dsatrs) {
		this.dsatrs = dsatrs;
	}

}