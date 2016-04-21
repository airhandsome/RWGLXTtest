package com.domain;

/**
 * Sequence entity. @author MyEclipse Persistence Tools
 */

public class Sequence implements java.io.Serializable {

	// Fields

	private Integer sequenceId;
	private String preType;
	private Integer pre;
	private String postType;
	private Integer post;

	// Constructors

	/** default constructor */
	public Sequence() {
	}

	/** full constructor */
	public Sequence(String preType, Integer pre, String postType, Integer post) {
		this.preType = preType;
		this.pre = pre;
		this.postType = postType;
		this.post = post;
	}

	// Property accessors

	public Integer getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getPreType() {
		return this.preType;
	}

	public void setPreType(String preType) {
		this.preType = preType;
	}

	public Integer getPre() {
		return this.pre;
	}

	public void setPre(Integer pre) {
		this.pre = pre;
	}

	public String getPostType() {
		return this.postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public Integer getPost() {
		return this.post;
	}

	public void setPost(Integer post) {
		this.post = post;
	}

}