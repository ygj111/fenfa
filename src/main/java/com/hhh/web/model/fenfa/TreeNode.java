package com.hhh.web.model.fenfa;

public class TreeNode {
	private String id;
	private String text;
	private String parent;
	private TreeState state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public TreeState getState() {
		return state;
	}
	public void setState(TreeState state) {
		this.state = state;
	}
}
