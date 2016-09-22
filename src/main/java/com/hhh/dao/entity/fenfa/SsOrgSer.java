package com.hhh.dao.entity.fenfa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The persistent class for the ss_org_ser database table.
 * 
 */
@Entity
@Table(name="ss_org_ser")
@NamedQuery(name="SsOrgSer.findAll", query="SELECT s FROM SsOrgSer s")
public class SsOrgSer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_id")
	private String customerId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expire;

	private String services;

	//bi-directional many-to-one association to SsApp
	@ManyToOne
	@JoinColumn(name="appname")
	private SsApp ssApp;

	public SsOrgSer() {
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getExpire() {
		return this.expire;
	}

	public void setExpire(Date date) {
		this.expire = date;
	}

	public String getServices() {
		return this.services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public SsApp getSsApp() {
		return this.ssApp;
	}

	public void setSsApp(SsApp ssApp) {
		this.ssApp = ssApp;
	}

}