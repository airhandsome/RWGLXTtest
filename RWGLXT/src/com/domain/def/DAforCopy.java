package com.domain.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAforCopy implements Cloneable{

	private Integer DA_id;
	private Integer type;
	private Integer Defbelong;
	private Integer CAbelong;
	private String name;
	private String description;
	private String triggertype;
	private Integer opeartor;
	private Integer priority;
	private Integer postion;
	private String state;
	private Integer ifAuto;
	private Integer ifdef;
	private Integer postDS;
	private Timestamp planStart;
	private Timestamp planDuration;
	private Timestamp planEnd;
	private String terminate;
	private Timestamp excuteStart;
	private Timestamp excuteEnd;
	private Integer event;
	private Integer endEvent;
	private String code;
	private String nodename;
	
	private Integer change_da_id;
	private List<DSforCopy> ds = new ArrayList<DSforCopy>();

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public Integer getDA_id() {
		return DA_id;
	}

	public void setDA_id(Integer dA_id) {
		DA_id = dA_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDefbelong() {
		return Defbelong;
	}

	public void setDefbelong(Integer defbelong) {
		Defbelong = defbelong;
	}

	public Integer getCAbelong() {
		return CAbelong;
	}

	public void setCAbelong(Integer cAbelong) {
		CAbelong = cAbelong;
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

	public String getTriggertype() {
		return triggertype;
	}

	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}

	public Integer getOpeartor() {
		return opeartor;
	}

	public void setOpeartor(Integer opeartor) {
		this.opeartor = opeartor;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getPostion() {
		return postion;
	}

	public void setPostion(Integer postion) {
		this.postion = postion;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getIfAuto() {
		return ifAuto;
	}

	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}

	public Integer getIfdef() {
		return ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public Integer getPostDS() {
		return postDS;
	}

	public void setPostDS(Integer postDS) {
		this.postDS = postDS;
	}

	public Timestamp getPlanStart() {
		return planStart;
	}

	public void setPlanStart(Timestamp planStart) {
		this.planStart = planStart;
	}

	public Timestamp getPlanDuration() {
		return planDuration;
	}

	public void setPlanDuration(Timestamp planDuration) {
		this.planDuration = planDuration;
	}

	public Timestamp getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(Timestamp planEnd) {
		this.planEnd = planEnd;
	}

	public String getTerminate() {
		return terminate;
	}

	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}

	public Timestamp getExcuteStart() {
		return excuteStart;
	}

	public void setExcuteStart(Timestamp excuteStart) {
		this.excuteStart = excuteStart;
	}

	public Timestamp getExcuteEnd() {
		return excuteEnd;
	}

	public void setExcuteEnd(Timestamp excuteEnd) {
		this.excuteEnd = excuteEnd;
	}

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public Integer getEndEvent() {
		return endEvent;
	}

	public void setEndEvent(Integer endEvent) {
		this.endEvent = endEvent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getChange_da_id() {
		return change_da_id;
	}

	public void setChange_da_id(Integer change_da_id) {
		this.change_da_id = change_da_id;
	}

	public List<DSforCopy> getDs() {
		return ds;
	}

	public void setDs(List<DSforCopy> ds) {
		this.ds = ds;
	}


	
}
