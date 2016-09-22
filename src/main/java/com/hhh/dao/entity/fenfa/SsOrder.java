package com.hhh.dao.entity.fenfa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the ss_orders database table.
 * 
 */
@Entity
@Table(name="ss_orders")
@NamedQuery(name="SsOrder.findAll", query="SELECT s FROM SsOrder s")
public class SsOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="idGenerator") 
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;

	@Column(name="customer_id")
	private String customerId;

	private double discount;
	
	@Column(name="order_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;

	@Column(name="org_name")
	private String orgName;

	private String services;

	private String status;//0:市场人员未审核;1：实施管理人员未审核；2：实施人员未审核；3：已实施

	private double total;
	
	private Date expired;
	
	private String appname;
	
	@OneToMany(mappedBy="ssOrder",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<SsOrdersService> ssOrdersServices;
	
	@Column(name="company_id")
	private String companyId;

	public SsOrder() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getServices() {
		return this.services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}
	
	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public List<SsOrdersService> getSsOrdersServices() {
		return ssOrdersServices;
	}

	public void setSsOrdersServices(List<SsOrdersService> ssOrdersServices) {
		this.ssOrdersServices = ssOrdersServices;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}