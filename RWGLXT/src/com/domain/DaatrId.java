package com.domain;

/**
 * DaatrId entity. @author MyEclipse Persistence Tools
 */

public class DaatrId implements java.io.Serializable {

	// Fields

	private Atrribute atrribute;
	private Da da;

	// Constructors

	/** default constructor */
	public DaatrId() {
	}

	/** full constructor */
	public DaatrId(Atrribute atrribute, Da da) {
		this.atrribute = atrribute;
		this.da = da;
	}

	// Property accessors

	public Atrribute getAtrribute() {
		return this.atrribute;
	}

	public void setAtrribute(Atrribute atrribute) {
		this.atrribute = atrribute;
	}

	public Da getDa() {
		return this.da;
	}

	public void setDa(Da da) {
		this.da = da;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DaatrId))
			return false;
		DaatrId castOther = (DaatrId) other;

		return ((this.getAtrribute() == castOther.getAtrribute()) || (this
				.getAtrribute() != null
				&& castOther.getAtrribute() != null && this.getAtrribute()
				.equals(castOther.getAtrribute())))
				&& ((this.getDa() == castOther.getDa()) || (this.getDa() != null
						&& castOther.getDa() != null && this.getDa().equals(
						castOther.getDa())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAtrribute() == null ? 0 : this.getAtrribute().hashCode());
		result = 37 * result + (getDa() == null ? 0 : this.getDa().hashCode());
		return result;
	}

}