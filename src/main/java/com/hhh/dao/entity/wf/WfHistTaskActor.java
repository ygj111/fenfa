package com.hhh.dao.entity.wf;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wf_hist_task_actor database table.
 * 
 */
@Entity
@Table(name="wf_hist_task_actor")
@NamedQuery(name="WfHistTaskActor.findAll", query="SELECT w FROM WfHistTaskActor w")
public class WfHistTaskActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfHistTaskActorPK id;

	public WfHistTaskActor() {
	}

	public WfHistTaskActorPK getId() {
		return this.id;
	}

	public void setId(WfHistTaskActorPK id) {
		this.id = id;
	}

}