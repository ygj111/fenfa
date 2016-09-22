package com.hhh.web.model.fenfa;

public class SsVerServiceBean {
	
	private String id;

	private String appVersion;

	private String appname;

	
	private String realId;

	

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


	public String getAppname() {
		return appname;
	}


	public void setAppname(String appname) {
		this.appname = appname;
	}


	public String getRealId() {
		return realId;
	}


	public void setRealId(String realId) {
		this.realId = realId;
	}


	public SsVerServiceBean(String id, String appVersion, String appname, String realId) {
		super();
		this.id = id;
		this.appVersion = appVersion;
		this.appname = appname;
		this.realId = realId;
	}


	public SsVerServiceBean() {
		super();
	}
	
	
}
