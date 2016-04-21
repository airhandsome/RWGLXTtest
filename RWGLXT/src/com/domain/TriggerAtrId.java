package com.domain;

/**
 * TriggerAtrId entity. @author MyEclipse Persistence Tools
 */

public class TriggerAtrId implements java.io.Serializable {

	// Fields

	private Atrribute atrribute;
	private EventTrigger eventTrigger;

	// Constructors

	/** default constructor */
	public TriggerAtrId() {
	}

	/** full constructor */
	public TriggerAtrId(Atrribute atrribute, EventTrigger eventTrigger) {
		this.atrribute = atrribute;
		this.eventTrigger = eventTrigger;
	}

	// Property accessors

	public Atrribute getAtrribute() {
		return this.atrribute;
	}

	public void setAtrribute(Atrribute atrribute) {
		this.atrribute = atrribute;
	}

	public EventTrigger getEventTrigger() {
		return this.eventTrigger;
	}

	public void setEventTrigger(EventTrigger eventTrigger) {
		this.eventTrigger = eventTrigger;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TriggerAtrId))
			return false;
		TriggerAtrId castOther = (TriggerAtrId) other;

		return ((this.getAtrribute() == castOther.getAtrribute()) || (this
				.getAtrribute() != null
				&& castOther.getAtrribute() != null && this.getAtrribute()
				.equals(castOther.getAtrribute())))
				&& ((this.getEventTrigger() == castOther.getEventTrigger()) || (this
						.getEventTrigger() != null
						&& castOther.getEventTrigger() != null && this
						.getEventTrigger().equals(castOther.getEventTrigger())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAtrribute() == null ? 0 : this.getAtrribute().hashCode());
		result = 37
				* result
				+ (getEventTrigger() == null ? 0 : this.getEventTrigger()
						.hashCode());
		return result;
	}

}