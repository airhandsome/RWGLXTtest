package com.domain;

/**
 * DsatrId entity. @author MyEclipse Persistence Tools
 */

public class DsatrId implements java.io.Serializable {

	// Fields

	private Atrribute atrribute;
	private Ds ds;

	// Constructors

	/** default constructor */
	public DsatrId() {
	}

	/** full constructor */
	public DsatrId(Atrribute atrribute, Ds ds) {
		this.atrribute = atrribute;
		this.ds = ds;
	}

	// Property accessors

	public Atrribute getAtrribute() {
		return this.atrribute;
	}

	public void setAtrribute(Atrribute atrribute) {
		this.atrribute = atrribute;
	}

	public Ds getDs() {
		return this.ds;
	}

	public void setDs(Ds ds) {
		this.ds = ds;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DsatrId))
			return false;
		DsatrId castOther = (DsatrId) other;

		return ((this.getAtrribute() == castOther.getAtrribute()) || (this
				.getAtrribute() != null
				&& castOther.getAtrribute() != null && this.getAtrribute()
				.equals(castOther.getAtrribute())))
				&& ((this.getDs() == castOther.getDs()) || (this.getDs() != null
						&& castOther.getDs() != null && this.getDs().equals(
						castOther.getDs())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAtrribute() == null ? 0 : this.getAtrribute().hashCode());
		result = 37 * result + (getDs() == null ? 0 : this.getDs().hashCode());
		return result;
	}

}