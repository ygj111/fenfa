package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wf_hist_order database table.
 * 
 */
@Entity
@Table(name="wf_hist_order")
@NamedQuery(name="WfHistOrder.findAll", query="SELECT w FROM WfHistOrder w")
public class WfHistOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="create_time")
	private String createTime;

	private String creator;

	@Column(name="end_time")
	private String endTime;

	@Column(name="expire_time")
	private String expireTime;

	@Column(name="order_no")
	private String orderNo;

	@Column(name="order_state")
	private int orderState;

	@Column(name="parent_id")
	private String parentId;

	private int priority;

	@Column(name="process_id")
	private String processId;

	private String variable;

	public WfHistOrder() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderState() {
		return this.orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getVariable() {
		return this.variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}