package com.domain;

/**
 * UserTypeId entity. @author MyEclipse Persistence Tools
 */

public class UserTypeId implements java.io.Serializable {

	// Fields

	private Type type;
	private Users users;

	// Constructors

	/** default constructor */
	public UserTypeId() {
	}

	/** full constructor */
	public UserTypeId(Type type, Users users) {
		this.type = type;
		this.users = users;
	}

	// Property accessors

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserTypeId))
			return false;
		UserTypeId castOther = (UserTypeId) other;

		return ((this.getType() == castOther.getType()) || (this.getType() != null
				&& castOther.getType() != null && this.getType().equals(
				castOther.getType())))
				&& ((this.getUsers() == castOther.getUsers()) || (this
						.getUsers() != null
						&& castOther.getUsers() != null && this.getUsers()
						.equals(castOther.getUsers())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getUsers() == null ? 0 : this.getUsers().hashCode());
		return result;
	}

}