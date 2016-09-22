package com.hhh.dao.entity.ac;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the sys_resource database table.
 * 
 */
@Entity
@Table(name="sys_resource")
@NamedQuery(name="SysResource.findAll", query="SELECT s FROM SysResource s")
public class SysResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String icon;

	private String name;

	private String parentid;

	private String position;

	private int status;

	private String type;

	//bi-directional many-to-many association to SysRole
	@ManyToMany(mappedBy="sysResources")
	private List<SysRole> sysRoles;

	public SysResource() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

}