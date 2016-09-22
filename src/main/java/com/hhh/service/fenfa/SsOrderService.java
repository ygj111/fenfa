package com.hhh.service.fenfa;



import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsOrder;
import com.hhh.dao.entity.fenfa.SsOrdersService;
import com.hhh.dao.fenfa.SsOrderDao;
import com.hhh.web.model.fenfa.ConstantClassField;
import com.hhh.web.model.fenfa.SsOrderBean;
import com.hhh.web.model.fenfa.SsOrdersServiceBean;

@Component
@Transactional
public class SsOrderService {
	private final static Logger logger = LoggerFactory.getLogger(SsOrderService.class);
	@Autowired
	SsOrderDao orderDao;
	
	/**
	 * 生成订单
	 * @param  数据对象
	 */
	@Transactional( propagation=Propagation.REQUIRED )
	public SsOrder saveOrder(SsOrder order) {
		
		SsOrder ssOrder = orderDao.save(order);
		return ssOrder;
	}

	private SsOrder toSysShop(SsOrderBean orderBean) {
		SsOrder order = new SsOrder();
		order.setCustomerId(orderBean.getCustomerId());
		order.setOrgName(orderBean.getOrgName());
		order.setOrderDate(orderBean.getOrderDate());
		order.setServices(orderBean.getServices());
		order.setDiscount(orderBean.getDiscount());
		
		return order;
	}

	/**
	 * 查询订单
	 * @return
	 */
	@Transactional( propagation=Propagation.REQUIRED )
	public List<SsOrderBean> getAllOrder() {
		List<SsOrder> orders = (List<SsOrder>) orderDao.findAll();
		List<SsOrderBean> orderBeans = new ArrayList<SsOrderBean>();
		
		for (int i = 0; i < orders.size(); i++) {
			SsOrder order = orders.get(i);
			SsOrderBean ssOrderBean = new SsOrderBean();
			
			ssOrderBean.setCustomerId(order.getCustomerId());
			ssOrderBean.setOrderDate(order.getOrderDate());
			ssOrderBean.setOrgName(order.getOrgName());
			ssOrderBean.setServices(order.getServices());
			ssOrderBean.setId(order.getId());
			ssOrderBean.setStatus(order.getStatus());
			ssOrderBean.setAppname(order.getAppname());
			orderBeans.add(ssOrderBean);
		}
		
		return orderBeans;
	}

	/**
	 * 获取分页数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsOrder> getAllOrders(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.ASC, "status").and(new Sort(Sort.Direction.DESC, "orderDate"));
		Pageable pageReq = new PageRequest(pageNum - 1, pageSize, sort);
//		Pageable pageReq = new PageRequest(pageNum - 1, pageSize);
		Page<SsOrder> ssOrder = orderDao.findAll(pageReq);
		return ssOrder;
	}
	
	/**
	 * 根据客户id获取对应的订单
	 * @param customerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<SsOrder> getOrdersByCustomerId(String customerId,int pageNum, int pageSize){
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		Page<SsOrder> ssOrderPage = orderDao.findByCustomerId(customerId,pageReq);
		return ssOrderPage;
	}
	
	/**
	 * 根据企业id获取对应的订单
	 * @param companyId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<SsOrder> getOrdersByCompanyId(String companyId,int pageNum, int pageSize){
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		Page<SsOrder> ssOrderPage = orderDao.findByCompanyIdOrderByOrderDateDesc(companyId,pageReq);
		return ssOrderPage;
	}
	
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsOrder> getApp(int pageNum, int pageSize) {
		
		List<SsOrder> ssOrders = (List<SsOrder>) orderDao.findAll();
		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		for (int i = 0; i < ssOrders.size(); i++) {
			SsOrderBean ssOrderBean = new SsOrderBean();
			SsOrder ssOrder = ssOrders.get(i);
			String status = ssOrder.getStatus();
			Pageable pageable = new PageRequest(pageNum-1, pageSize);
			 Page<SsOrder> orders = orderDao.findByStatus(status, pageable);
			 return orders;
		}
		return null;
	}

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public SsOrder findOrderById(String id) {
		
		return orderDao.findOne(id);
	}
	
	/**
	 * 根据id获取订单
	 * @param id
	 * @author 3hygj
	 * @return
	 */
	public SsOrderBean findOrder(String id){
		SsOrderBean bean = null;
		try {
			SsOrder order = orderDao.findOne(id);
			List<SsOrdersService> serviceList = order.getSsOrdersServices();
			SsOrdersServiceBean ssOrdersServiceBean = null;
			List<SsOrdersServiceBean> ssOrdersServiceBeanList = new ArrayList<SsOrdersServiceBean>();
			for(SsOrdersService service:serviceList){
				ssOrdersServiceBean = new SsOrdersServiceBean();
				BeanUtils.copyProperties(ssOrdersServiceBean, service);
				ssOrdersServiceBeanList.add(ssOrdersServiceBean);
			}
			bean = new SsOrderBean();
			BeanUtils.copyProperties(bean, order);
			bean.setSsOrdersServiceBeans(ssOrdersServiceBeanList);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsOrder> getAllApp2(int pageNum, int pageSize) {
			
		String status = ConstantClassField.ORDER_STATUS_MARKET_UNAUDITED;
		Pageable pageable = new PageRequest(pageNum-1, pageSize);
		Page<SsOrder> orders = orderDao.findByStatus(status,pageable);
		return orders;
		}
	
	
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsOrder> getAllApp3(int pageNum, int pageSize) {
		
		String status =	ConstantClassField.ORDER_STATUS_ENFORCE_MANAGE_UNAUDITED;
		Pageable pageable = new PageRequest(pageNum-1, pageSize);
		Page<SsOrder> orders = orderDao.findByStatus(status, pageable);
	    return orders;
		
	}

	public Page<SsOrder> getAllApp4(int pageNum, int pageSize) {
		String status1 = ConstantClassField.ORDER_STATUS_ENFORCE_UNAUDITED;
		String status2 = ConstantClassField.ORDER_STATUS_ENFORCED;
		Pageable pageable = new PageRequest(pageNum-1, pageSize);
		Page<SsOrder> orders = orderDao.findByStatusOrStatus(status1, status2, pageable);
		return orders;
		
		 
	}
    /**
     * 根据状态查询
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
	public Page<SsOrder> getAppByStatus(int pageNum, int pageSize, String status) {
		Pageable pageable = new PageRequest(pageNum-1, pageSize);
		return orderDao.findByStatus(status, pageable);
	}
	
	/**
	 * 根据订单id更新customerId
	 * @param customerId
	 * @param id
	 */
	public void updateCustomerIdByOrderId(String customerId,String id){
		orderDao.updateCustomerId(customerId, id);
	}
	
	/**
	 * 根据订单id更新订单状态
	 * @param status
	 * @param id
	 */
	public void updateStatusByOrderId(String status,String id){
		orderDao.updateStatus(status, id);
	}
}
