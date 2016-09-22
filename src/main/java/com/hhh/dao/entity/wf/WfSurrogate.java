package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wf_surrogate database table.
 * 
 */
@Entity
@Table(name="wf_surrogate")
@NamedQuery(name="WfSurrogate.findAll", query="SELECT w FROM WfSurrogate w")
public class WfSurrogate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String edate;

	private String odate;

	private String operator;

	@Column(name="process_name")
	private String processName;

	private String sdate;

	private int state;

	private String surrogate;

	public WfSurrogate() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdate() {
		return this.edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getOdate() {
		return this.odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getSdate() {
		return this.sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSurrogate() {
		return this.surrogate;
	}

	public void setSurrogate(String surrogate) {
		this.surrogate = surrogate;
	}

}