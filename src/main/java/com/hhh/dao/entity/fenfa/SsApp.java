package com.hhh.dao.entity.fenfa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the ss_apps database table.
 * 
 */
@Entity
@Table(name="ss_apps")
@NamedQuery(name="SsApp.findAll", query="SELECT s FROM SsApp s")
public class SsApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String appname;

	private String description;

	private String version;
	
	private String type;

	//bi-directional many-to-one association to SsService
	@OneToMany(mappedBy="ssApp")
	private List<SsService> ssServices;
	
	@OneToMany(mappedBy="ssApp")
	private List<SsOrgSer> ssOrgSer;
	
	@OneToMany(mappedBy="ssApp")
	private List<SsAppVer> ssAppVer;

	public SsApp() {
	}

	public String getAppname() {
		return this.appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<SsService> getSsServices() {
		return this.ssServices;
	}

	public void setSsServices(List<SsService> ssServices) {
		this.ssServices = ssServices;
	}

	public SsService addSsService(SsService ssService) {
		getSsServices().add(ssService);
		ssService.setSsApp(this);

		return ssService;
	}

	public SsService removeSsService(SsService ssService) {
		getSsServices().remove(ssService);
		ssService.setSsApp(null);

		return ssService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SsOrgSer> getSsOrgSer() {
		return ssOrgSer;
	}

	public void setSsOrgSer(List<SsOrgSer> ssOrgSer) {
		this.ssOrgSer = ssOrgSer;
	}

	public List<SsAppVer> getSsAppVer() {
		return ssAppVer;
	}

	public void setSsAppVer(List<SsAppVer> ssAppVer) {
		this.ssAppVer = ssAppVer;
	}
   
}