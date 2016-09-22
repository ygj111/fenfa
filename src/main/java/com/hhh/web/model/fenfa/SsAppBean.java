package com.hhh.web.model.fenfa;

import java.util.ArrayList;
import java.util.List;

import com.hhh.dao.entity.fenfa.SsService;

public class SsAppBean {
	private String appname;

	private String description;

	private String version;
	
	private String type;
	
	//private List<SsOrgSer> ssOrgSers;
	
	private List<SsService> ssServices=new ArrayList<SsService>();

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	
	public List<SsService> getSsServices() {
		return ssServices;
	}

	public void setSsServices(List<SsService> ssServices) {
		this.ssServices = ssServices;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SsAppBean(String appname, String description, String version, String type, List<SsService> ssServices) {
		super();
		this.appname = appname;
		this.description = description;
		this.version = version;
		this.type = type;
		this.ssServices = ssServices;
	}

	public SsAppBean() {
		super();
	}

	

	
  
	
}
