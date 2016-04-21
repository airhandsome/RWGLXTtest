package com.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer userid;
	private FightPostion fightPostion;
	private String username;
	private String password;
	private String department;
	private String role;
	private String post;
	private Set isps = new HashSet(0);
	private Set userTypes = new HashSet(0);
	private Set cas = new HashSet(0);
	private Set das = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String username, String password,
			String department, String role, String post) {
		this.username = username;
		this.password = password;
		this.department = department;
		this.role = role;
		this.post = post;
	}

	/** full constructor */
	public Users(FightPostion fightPostion, String username, String password,
			String department, String role, String post, Set isps,
			Set userTypes, Set cas, Set das) {
		this.fightPostion = fightPostion;
		this.username = username;
		this.password = password;
		this.department = department;
		this.role = role;
		this.post = post;
		this.isps = isps;
		this.userTypes = userTypes;
		this.cas = cas;
		this.das = das;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public FightPostion getFightPostion() {
		return this.fightPostion;
	}

	public void setFightPostion(FightPostion fightPostion) {
		this.fightPostion = fightPostion;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Set getIsps() {
		return this.isps;
	}

	public void setIsps(Set isps) {
		this.isps = isps;
	}

	public Set getUserTypes() {
		return this.userTypes;
	}

	public void setUserTypes(Set userTypes) {
		this.userTypes = userTypes;
	}

	public Set getCas() {
		return this.cas;
	}

	public void setCas(Set cas) {
		this.cas = cas;
	}

	public Set getDas() {
		return this.das;
	}

	public void setDas(Set das) {
		this.das = das;
	}

}