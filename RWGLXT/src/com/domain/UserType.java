package com.domain;

/**
 * UserType entity. @author MyEclipse Persistence Tools
 */

public class UserType implements java.io.Serializable {

	// Fields

	private UserTypeId id;

	// Constructors

	/** default constructor */
	public UserType() {
	}

	/** full constructor */
	public UserType(UserTypeId id) {
		this.id = id;
	}

	// Property accessors

	public UserTypeId getId() {
		return this.id;
	}

	public void setId(UserTypeId id) {
		this.id = id;
	}

}