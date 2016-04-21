package com.domain.def;

import java.util.List;

public class Area {
	private String areaname;
	private String caname;
	private Integer caId;
	private String color;
	private int left;
	private int top;
	private int width;
	private int height;
	private int[] x;
	private int[] y;
	private List<Node> nodes;
	
	public int[] getX() {
		return x;
	}
	public void setX(int[] x) {
		this.x = x;
	}
	public int[] getY() {
		return y;
	}
	public void setY(int[] y) {
		this.y = y;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getCaname() {
		return caname;
	}
	public void setCaname(String caname) {
		this.caname = caname;
	}
	public Integer getCaId() {
		return caId;
	}
	public void setCaId(Integer caId) {
		this.caId = caId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
