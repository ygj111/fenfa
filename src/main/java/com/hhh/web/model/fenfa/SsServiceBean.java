package com.hhh.web.model.fenfa;

public class SsServiceBean {
	
	private String id;

	
	private String customerId;

	private String label;

	private String pid;

	private double price;
	
	private SsAppBean ssAppBean;
	
	private String realId;

	private String remark;
	
	private String serviceType;

	private String seq;
	
	private String appVersion;
	

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRealId() {
		return realId;
	}

	public void setRealId(String realId) {
		this.realId = realId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public SsServiceBean(String id, String customerId, String label, String pid, double price,SsAppBean ssAppBean,
			String realId, String remark, String seq) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.label = label;
		this.pid = pid;
		this.price = price;
		this.ssAppBean = ssAppBean;
		this.realId = realId;
		this.remark = remark;
		this.seq = seq;
	}

	public SsServiceBean() {
		super();
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public SsAppBean getSsAppBean() {
		return ssAppBean;
	}

	public void setSsAppBean(SsAppBean ssAppBean) {
		this.ssAppBean = ssAppBean;
	}

}
