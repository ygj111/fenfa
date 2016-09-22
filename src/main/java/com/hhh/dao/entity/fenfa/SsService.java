package com.hhh.dao.entity.fenfa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the ss_service database table.
 * 
 */
@Entity
@Table(name="ss_service")
@NamedQuery(name="SsService.findAll", query="SELECT s FROM SsService s")
public class SsService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="idGenerator") 
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;

	@Column(name="customer_id")
	private String customerId;

	private String label;

	private String pid;

	private double price;

	@Column(name="real_id")
	private String realId;

	private String remark;
	
	@Column(name="service_type")
	private String serviceType;//服务类型0：功能；1：非功能（例如项目）
	
	private String seq;

	//bi-directional many-to-one association to SsApp
	@ManyToOne
	@JoinColumn(name="appname")
	private SsApp ssApp;
   
	public SsService() {
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

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRealId() {
		return this.realId;
	}

	public void setRealId(String realId) {
		this.realId = realId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public SsApp getSsApp() {
		return this.ssApp;
	}

	public void setSsApp(SsApp ssApp) {
		this.ssApp = ssApp;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

}