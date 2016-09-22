package com.hhh.dao.entity.fenfa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ss_client_info database table.
 * 
 */
@Entity
@Table(name="ss_client_info")
@NamedQuery(name="SsClientInfo.findAll", query="SELECT s FROM SsClientInfo s")
public class SsClientInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="access_date")
	private Date accessDate;

	private String appname;

	private String depname;

	@Column(name="device_info")
	private String deviceInfo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="limit_date")
	private Date limitDate;

	private String mac;//多网卡mac用分号“;”隔开；

	@Column(name="setup_path")
	private String setupPath;

	private int status;//0:可用,1:不可用

	public SsClientInfo() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getAccessDate() {
		return this.accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	public String getAppname() {
		return this.appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getDepname() {
		return this.depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public String getDeviceInfo() {
		return this.deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public Date getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSetupPath() {
		return this.setupPath;
	}

	public void setSetupPath(String setupPath) {
		this.setupPath = setupPath;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}