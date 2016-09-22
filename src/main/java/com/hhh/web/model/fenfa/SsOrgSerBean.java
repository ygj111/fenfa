package com.hhh.web.model.fenfa;

import java.sql.Timestamp;

public class SsOrgSerBean {
	private String customerId;

	private Timestamp expire;

	private String services;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Timestamp getExpire() {
		return expire;
	}

	public void setExpire(Timestamp expire) {
		this.expire = expire;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public SsOrgSerBean(String customerId, Timestamp expire, String services) {
		super();
		this.customerId = customerId;
		this.expire = expire;
		this.services = services;
	}

	public SsOrgSerBean() {
		super();
	}

	//private SsApp ssApp;
   
}
