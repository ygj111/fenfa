package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the wf_cc_order database table.
 * 
 */
@Embeddable
public class WfCcOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id")
	private String orderId;

	@Column(name="actor_id")
	private String actorId;

	public WfCcOrderPK() {
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getActorId() {
		return this.actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WfCcOrderPK)) {
			return false;
		}
		WfCcOrderPK castOther = (WfCcOrderPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.actorId.equals(castOther.actorId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.actorId.hashCode();
		
		return hash;
	}
}