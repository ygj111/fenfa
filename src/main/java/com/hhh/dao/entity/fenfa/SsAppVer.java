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
 * The persistent class for the ss_app_vers database table.
 * 
 */
@Entity
@Table(name="ss_app_vers")
@NamedQuery(name="SsAppVer.findAll", query="SELECT s FROM SsAppVer s")
public class SsAppVer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="idGenerator") 
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;

	@Column(name="app_version")
	private String appVersion;

	//bi-directional many-to-one association to SsApp
	@ManyToOne
	@JoinColumn(name="appname")
	private SsApp ssApp;

	public SsAppVer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public SsApp getSsApp() {
		return this.ssApp;
	}

	public void setSsApp(SsApp ssApp) {
		this.ssApp = ssApp;
	}

}