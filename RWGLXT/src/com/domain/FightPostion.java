package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * FightPostion entity. @author MyEclipse Persistence Tools
 */

public class FightPostion implements java.io.Serializable {

	// Fields

	private Integer postionId;
	private String name;
	private String department;
	private String post;
	private Set userses = new HashSet(0);
	private Set das = new HashSet(0);

	// Constructors

	/** default constructor */
	public FightPostion() {
	}

	/** minimal constructor */
	public FightPostion(String name, String department, String post) {
		this.name = name;
		this.department = department;
		this.post = post;
	}

	/** full constructor */
	public FightPostion(String name, String department, String post, Set userses, Set das) {
		this.name = name;
		this.department = department;
		this.post = post;
		this.userses = userses;
		this.das = das;
	}

	// Property accessors

	public Integer getPostionId() {
		return this.postionId;
	}

	public void setPostionId(Integer postionId) {
		this.postionId = postionId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getDas() {
		return this.das;
	}

	public void setDas(Set das) {
		this.das = das;
	}

}