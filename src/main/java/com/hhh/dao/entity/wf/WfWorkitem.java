package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wf_workitem database table.
 * 
 */
@Entity
@Table(name="wf_workitem")
@NamedQuery(name="WfWorkitem.findAll", query="SELECT w FROM WfWorkitem w")
public class WfWorkitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="task_id")
	private String taskId;

	@Column(name="action_url")
	private String actionUrl;

	private String creator;

	@Column(name="instance_url")
	private String instanceUrl;

	private String operator;

	@Column(name="order_create_time")
	private String orderCreateTime;

	@Column(name="order_expire_time")
	private String orderExpireTime;

	@Column(name="order_id")
	private String orderId;

	@Column(name="order_no")
	private String orderNo;

	@Column(name="order_variable")
	private String orderVariable;

	@Column(name="parent_id")
	private String parentId;

	@Column(name="perform_type")
	private int performType;

	@Column(name="process_id")
	private String processId;

	@Column(name="process_name")
	private String processName;

	@Column(name="task_create_time")
	private String taskCreateTime;

	@Column(name="task_end_time")
	private String taskEndTime;

	@Column(name="task_expire_time")
	private String taskExpireTime;

	@Column(name="task_name")
	private String taskName;

	@Column(name="task_type")
	private int taskType;

	@Column(name="task_variable")
	private String taskVariable;

	public WfWorkitem() {
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getInstanceUrl() {
		return this.instanceUrl;
	}

	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOrderCreateTime() {
		return this.orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderExpireTime() {
		return this.orderExpireTime;
	}

	public void setOrderExpireTime(String orderExpireTime) {
		this.orderExpireTime = orderExpireTime;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderVariable() {
		return this.orderVariable;
	}

	public void setOrderVariable(String orderVariable) {
		this.orderVariable = orderVariable;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getPerformType() {
		return this.performType;
	}

	public void setPerformType(int performType) {
		this.performType = performType;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getTaskCreateTime() {
		return this.taskCreateTime;
	}

	public void setTaskCreateTime(String taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public String getTaskEndTime() {
		return this.taskEndTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getTaskExpireTime() {
		return this.taskExpireTime;
	}

	public void setTaskExpireTime(String taskExpireTime) {
		this.taskExpireTime = taskExpireTime;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskType() {
		return this.taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public String getTaskVariable() {
		return this.taskVariable;
	}

	public void setTaskVariable(String taskVariable) {
		this.taskVariable = taskVariable;
	}

}