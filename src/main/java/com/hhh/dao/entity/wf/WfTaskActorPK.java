package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the wf_task_actor database table.
 * 
 */
@Embeddable
public class WfTaskActorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="task_id")
	private String taskId;

	@Column(name="actor_id")
	private String actorId;

	public WfTaskActorPK() {
	}
	public String getTaskId() {
		return this.taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
		if (!(other instanceof WfTaskActorPK)) {
			return false;
		}
		WfTaskActorPK castOther = (WfTaskActorPK)other;
		return 
			this.taskId.equals(castOther.taskId)
			&& this.actorId.equals(castOther.actorId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taskId.hashCode();
		hash = hash * prime + this.actorId.hashCode();
		
		return hash;
	}
}