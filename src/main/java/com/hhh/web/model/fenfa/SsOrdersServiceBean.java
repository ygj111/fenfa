package com.hhh.web.model.fenfa;

public class SsOrdersServiceBean {
	private String id;
	private String appVersion;
	private SsOrderBean ssOrderBean;
	private SsServiceBean ssServiceBean;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public SsOrderBean getSsOrderBean() {
		return ssOrderBean;
	}
	public void setSsOrderBean(SsOrderBean ssOrderBean) {
		this.ssOrderBean = ssOrderBean;
	}
	public SsServiceBean getSsServiceBean() {
		return ssServiceBean;
	}
	public void setSsServiceBean(SsServiceBean ssServiceBean) {
		this.ssServiceBean = ssServiceBean;
	}
}
