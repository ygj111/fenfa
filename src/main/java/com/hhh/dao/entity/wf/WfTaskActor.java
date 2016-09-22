package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wf_task_actor database table.
 * 
 */
@Entity
@Table(name="wf_task_actor")
@NamedQuery(name="WfTaskActor.findAll", query="SELECT w FROM WfTaskActor w")
public class WfTaskActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfTaskActorPK id;

	public WfTaskActor() {
	}

	public WfTaskActorPK getId() {
		return this.id;
	}

	public void setId(WfTaskActorPK id) {
		this.id = id;
	}

}