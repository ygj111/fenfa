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


/**订单细表
 * The persistent class for the ss_orders_service database table.
 * 
 */
@Entity
@Table(name="ss_orders_service")
@NamedQuery(name="SsOrdersService.findAll", query="SELECT s FROM SsOrdersService s")
public class SsOrdersService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;

	@Column(name="app_version")
	private String appVersion;

	//bi-directional many-to-one association to SsOrder
	@ManyToOne
	@JoinColumn(name="order_id")
	private SsOrder ssOrder;

	//bi-directional many-to-one association to SsService
	@ManyToOne
	@JoinColumn(name="real_id", referencedColumnName="real_id")
	private SsService ssService;

	public SsOrdersService() {
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

	public SsOrder getSsOrder() {
		return this.ssOrder;
	}

	public void setSsOrder(SsOrder ssOrder) {
		this.ssOrder = ssOrder;
	}

	public SsService getSsService() {
		return this.ssService;
	}

	public void setSsService(SsService ssService) {
		this.ssService = ssService;
	}

}