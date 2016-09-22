package com.hhh.web.model.fenfa;

import java.util.Date;

public class SsClientInfoBean {
	private String code;
	private Date accessDate;
	private String appname;
	private String depname;
	private String deviceInfo;
	private Date limitDate;
	private String mac;//多网卡mac用分号“;”隔开；
	private String setupPath;
	private int status;//0:可用,1:不可用
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSetupPath() {
		return setupPath;
	}
	public void setSetupPath(String setupPath) {
		this.setupPath = setupPath;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
