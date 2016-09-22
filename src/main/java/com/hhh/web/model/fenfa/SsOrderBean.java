package com.hhh.web.model.fenfa;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class SsOrderBean {
	private String id;
	
	private String customerId;

	private double discount;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;

	private String orgName;

	private String services;

	private String status;

	private double total;
	
	private Date expired;

	private String appname;
	
   private String appVersion;
   
   private List<SsOrdersServiceBean> ssOrdersServiceBeans;
   
   private String companyId;
   
	public String getAppVersion() {
	return appVersion;
}

public void setAppVersion(String appVersion) {
	this.appVersion = appVersion;
}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public SsOrderBean(String id, String customerId, double discount, Date orderDate, String orgName, String services,
			String status, double total, Date expired, String appname) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.discount = discount;
		this.orderDate = orderDate;
		this.orgName = orgName;
		this.services = services;
		this.status = status;
		this.total = total;
		this.expired = expired;
		this.appname = appname;
	}

	public SsOrderBean() {
		super();
	}

	public List<SsOrdersServiceBean> getSsOrdersServiceBeans() {
		return ssOrdersServiceBeans;
	}

	public void setSsOrdersServiceBeans(List<SsOrdersServiceBean> ssOrdersServiceBeans) {
		this.ssOrdersServiceBeans = ssOrdersServiceBeans;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


}
