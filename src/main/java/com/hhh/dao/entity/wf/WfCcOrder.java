package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wf_cc_order database table.
 * 
 */
@Entity
@Table(name="wf_cc_order")
@NamedQuery(name="WfCcOrder.findAll", query="SELECT w FROM WfCcOrder w")
public class WfCcOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfCcOrderPK id;

	private int status;

	public WfCcOrder() {
	}

	public WfCcOrderPK getId() {
		return this.id;
	}

	public void setId(WfCcOrderPK id) {
		this.id = id;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}