package com.hhh.service.fenfa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsOrdersService;
import com.hhh.dao.fenfa.SsOrdersServiceDao;
import com.hhh.dao.fenfa.SsVersServiceDao;
import com.hhh.web.model.fenfa.SsOrderBean;
import com.hhh.web.model.fenfa.SsOrdersServiceBean;
import com.hhh.web.model.fenfa.SsServiceBean;

@Component
@Transactional
public class SsOrderServiceService {
	private final static Logger logger = LoggerFactory.getLogger(SsOrderServiceService.class);
	@Autowired
	private SsOrdersServiceDao sosdao;
	@Autowired
	private SsVersServiceDao svsdao;
	
	/**
	 * 根据订单Id查找服务
	 * @param orderid
	 * @return
	 */
	public List<SsOrdersServiceBean> getServiceBeanByOrderid(String orderid) {
		List<SsOrdersService> ssOrdersServiceList = sosdao.findBySsOrderId(orderid);
		List<SsOrdersServiceBean> list = new ArrayList<SsOrdersServiceBean>();
		SsOrdersServiceBean bean = null;
		try {
			for(SsOrdersService sos:ssOrdersServiceList){
				bean = new SsOrdersServiceBean();
				BeanUtils.copyProperties(bean, sos);
				SsOrderBean ssOrderBean = new SsOrderBean();
				BeanUtils.copyProperties(ssOrderBean,sos.getSsOrder());
				bean.setSsOrderBean(ssOrderBean);
				SsServiceBean ssServiceBean = new SsServiceBean();
				BeanUtils.copyProperties(ssServiceBean,sos.getSsService());
				bean.setSsServiceBean(ssServiceBean);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据订单Id查找服务
	 * @param orderid
	 * @return
	 */
	public List<SsOrdersService> getServiceId(String orderid) {
		return sosdao.findBySsOrderId(orderid);
	}
	
}
