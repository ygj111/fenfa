package com.hhh.web.model.fenfa;

import com.hhh.dao.entity.fenfa.SsApp;

public class SsAppVerBean {
	private String id;

	private String appVersion;

	private String appname;

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

	public SsAppVerBean(String id, String appVersion, String appname) {
		super();
		this.id = id;
		this.appVersion = appVersion;
		this.appname = appname;
	}

	public SsAppVerBean() {
		super();
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}
   
	
}
