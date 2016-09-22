package com.hhh.web.controller.Fenfa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hhh.dao.entity.fenfa.SsApp;
import com.hhh.dao.entity.fenfa.SsOrder;
import com.hhh.dao.entity.fenfa.SsOrdersService;
import com.hhh.dao.entity.fenfa.SsOrgSer;
import com.hhh.dao.entity.fenfa.SsService;
import com.hhh.dao.entity.fenfa.SsVersService;
import com.hhh.fund.usercenter.ResourcesType;
import com.hhh.fund.usercenter.entity.Company;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.CompanyBean;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.PermissionBean;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.service.fenfa.SsAppService;
import com.hhh.service.fenfa.SsAppVerService;
import com.hhh.service.fenfa.SsOrderService;
import com.hhh.service.fenfa.SsOrderServiceService;
import com.hhh.service.fenfa.SsOrgSerService;
import com.hhh.service.fenfa.SsServiceService;
import com.hhh.service.fenfa.SsVersServiceService;
import com.hhh.util.EncryptDES;
import com.hhh.web.model.ac.User;
import com.hhh.web.model.fenfa.ConstantClassField;
import com.hhh.web.model.fenfa.LicenseBean;
import com.hhh.web.model.fenfa.Regions;
import com.hhh.web.model.fenfa.SsAppBean;
import com.hhh.web.model.fenfa.SsAppVerBean;
import com.hhh.web.model.fenfa.SsOrderBean;
import com.hhh.web.model.fenfa.SsOrdersServiceBean;
import com.hhh.web.model.fenfa.SsServiceBean;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping(value = "/main")
public class FenfaController {
	private static final String PAGE_SIZE = "15";

	@Autowired
	SsServiceService ssService;

	@Autowired
	SsOrderService orderService;

	@Autowired
	SsAppService appService;

	@Autowired
	SsOrgSerService orgSerService;
	@Autowired
	private UserCenterService userCenterService;

	@Autowired
	private SsAppVerService ssAppVerService;
	@Autowired
	private SsVersServiceService ssVersServiceService;

	@Autowired
	private SsOrderServiceService sosService;
	
	@Autowired
	private Environment env;

	/**
	 * 产品列表页面（首页）
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {
		ModelAndView view = new ModelAndView("fenfa/homeList");
		Page<SsApp> ssApps = appService.getAllApp(pageNum, pageSize);
		List<SsApp> ssAppList = ssApps.getContent();
		List<SsAppBean> list = new ArrayList<SsAppBean>();
		SsAppBean ssAppBean = null;
		SsApp ssApp = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssApp = ssAppList.get(i);
			ssAppBean = new SsAppBean();
			ssAppBean.setAppname(ssApp.getAppname());
			ssAppBean.setDescription(ssApp.getDescription());
			ssAppBean.setVersion(ssApp.getVersion());
			ssAppBean.setType(ssApp.getType());
			list.add(ssAppBean);
		}
		view.addObject("ssApp", list);
		view.addObject("pageNum", pageNum);
		view.addObject("totalPageNum", ssApps.getTotalPages());
		view.addObject("totalNum", ssApps.getTotalElements());
		return view;

	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {

		ModelAndView view = new ModelAndView("app/homeList");
		List<SsAppBean> allApp = appService.getAllApp();
		view.addObject("allApp", allApp);
		return view;

	}

	/**
	 * 选择产品及服务列表页面
	 * 
	 * @param appname
	 * @return
	 */
	@RequestMapping(value = "/toSelectService", method = RequestMethod.GET)
	public ModelAndView selectService(String appname) {
		ModelAndView view = new ModelAndView("fenfa/toServiceList");
		List<SsServiceBean> serviceList = ssService.findByappname(appname);
		view.addObject("serviceList", serviceList);
		view.addObject("appname", appname);
		return view;

	}

	/**
	 * 生成订单
	 * 
	 * @param checkids
	 * @param label
	 * @param appname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/insertSsOrder", method = RequestMethod.GET)
	public ModelAndView insertSsOrder(@RequestParam(value = "checkid", defaultValue = "1") String[] checkids, String label, String appname,
			@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,
			HttpSession session) {

		// 生成订单
		SsOrder order = new SsOrder();
		double sum = 0.0;
		double price = 0.0;
		StringBuffer sb = new StringBuffer();
		StringBuffer stringbuf = null;
		for (int i = 0; i < checkids.length; i++) {
			SsService service = ssService.findServiceById((checkids[i]));
			price = service.getPrice();
			sum = sum + price;
			label = service.getLabel();
			stringbuf = sb.append(label).append(",");
			appname = service.getSsApp().getAppname();
		}
		stringbuf.deleteCharAt(stringbuf.length() - 1);// 去除最后一个逗号
		order.setServices(sb.toString());
		order.setOrgName("三和");
		order.setOrderDate(new Date(System.currentTimeMillis()));
		/* Object object = session.getAttribute(StringUtil.CUSTOMER_ID); */
		order.setCustomerId("3h");
		order.setTotal(sum);
		order.setStatus("0");
		order.setAppname(appname);

		// 保存订单
		orderService.saveOrder(order);
		return new ModelAndView("redirect:/main/showSsOrder");

	}

	/**
	 * 订单列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/showSsOrder", method = RequestMethod.GET)
	public ModelAndView showSsOrder(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = "5") int pageSize, HttpSession session) {
		UserBean userBean = (UserBean) session.getAttribute("loginUser");
		Page<SsOrder> ssOrders = orderService.getOrdersByCompanyId(userBean.getCompany().getId(), pageNum, pageSize);
		List<SsOrder> ssAppList = ssOrders.getContent();
		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		SsOrderBean ssOrderBean = null;
		SsOrder ssOrder = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssOrder = ssAppList.get(i);
			ssOrderBean = new SsOrderBean();
			ssOrderBean.setCustomerId(ssOrder.getCustomerId());
			ssOrderBean.setOrderDate(ssOrder.getOrderDate());
			ssOrderBean.setOrgName(ssOrder.getOrgName());
			ssOrderBean.setServices(ssOrder.getServices());
			ssOrderBean.setId(ssOrder.getId());
			ssOrderBean.setStatus(ssOrder.getStatus());
			ssOrderBean.setTotal(ssOrder.getTotal());
			ssOrderBean.setDiscount(ssOrder.getDiscount());

			String appname = ssOrder.getAppname();
			SsAppBean appByAppname = appService.getAppByAppname(appname);// 根据appname获取产品
			ssOrderBean.setAppname(appByAppname.getDescription());

			List<SsOrdersService> sos = sosService.getServiceId(ssOrder.getId());// 获取订单明细信息
			StringBuffer sb = new StringBuffer();
			StringBuffer stringbuf = null;
			try {
				for (int j = 0; j < sos.size(); j++) {
					String appVersion = sos.get(j).getAppVersion();// 获取版本
					String label = sos.get(j).getSsService().getLabel();// 获取服务
					stringbuf = sb.append(label).append(" ");
					ssOrderBean.setAppVersion(appVersion);
					ssOrderBean.setServices(stringbuf.toString());

				}
				list.add(ssOrderBean);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		ModelAndView view = new ModelAndView("order/orderManagement");
		view.addObject("ssOrderList", list);
		view.addObject("totalPageNum", ssOrders.getTotalPages());
		view.addObject("totalNum", ssOrders.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;

	}

	/**
	 * 查询订单详情
	 */
	@RequestMapping(value = "/ordertail", method = RequestMethod.GET)
	public ModelAndView ordertail(@RequestParam(value = "id", defaultValue = "1") String id,
			@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {

		SsOrder order = new SsOrder();
		order = orderService.findOrderById(id);

		StringBuffer sb = new StringBuffer();
		StringBuffer stringbuf = null;
		List<SsOrdersService> serviceList = sosService.getServiceId(id);
		ModelAndView view = new ModelAndView("order/orderdetail");
		for (int i = 0; i < serviceList.size(); i++) {
			String appVersion = serviceList.get(i).getAppVersion();// 版本
			String label = serviceList.get(i).getSsService().getLabel();// 服务
			stringbuf = sb.append(label).append(" ");
			view.addObject("order", order);
			view.addObject("appVersion", appVersion);
			view.addObject("service", stringbuf.toString());

		}
		return view;
	}

	/**
	 * 订单详情
	 * 
	 * @param id
	 * @author 3hygj
	 * @return
	 */
	@RequestMapping(value = "orderDetail", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> orderDetail(String id) {
		SsOrderBean orderBean = orderService.findOrder(id);
		List<SsOrdersServiceBean> serviceList = orderBean.getSsOrdersServiceBeans();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", orderBean);
		map.put("serviceList", serviceList);
		return map;
	}
	/**
	 * 订单详情和订单中服务信息
	 * 
	 * @param id
	 * @author 3hzp
	 * @return
	 */
	@RequestMapping(value = "orderDetailAndService", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> orderDetailAndService(String id) {
		SsOrderBean orderBean = orderService.findOrder(id);
		//List<SsOrdersServiceBean> serviceList = orderBean.getSsOrdersServiceBeans();
		List<SsServiceBean> list = new ArrayList<SsServiceBean>();
		List<SsOrdersService> serviceList = sosService.getServiceId(id);
		for(int i=0;i<serviceList.size();i++){
			SsService ssService= serviceList.get(i).getSsService();//orderServiceID
			String label = ssService.getLabel();
			double price = ssService.getPrice();
			SsServiceBean ss = new SsServiceBean();
			ss.setLabel(label);
			ss.setPrice(price);
			list.add(ss);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", orderBean);
		map.put("serviceList", list);
		return map;
	}
	/**
	 * 提交订单
	 * 
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	@RequestMapping(value = "/submitSsOrder", method = RequestMethod.POST)
	public ModelAndView submitSsOrder(@RequestParam(value = "id", defaultValue = "1") String id,
			@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {

		SsOrder order = new SsOrder();
		order = orderService.findOrderById(id);
		String status = order.getStatus();
		// if(status.equals("未提交")){
		order.setStatus("0");
		order.setId(id);
		order.setOrderDate(new Date(System.currentTimeMillis()));
		orderService.saveOrder(order);
		// }
		return new ModelAndView("redirect:/main/showSsOrder");

	}

	/**
	 * 订单审核列表（市场用户）
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/checkOrderList", method = RequestMethod.GET)
	public ModelAndView showCheckOrder(@RequestParam(value = "page", defaultValue = "1") int pageNum, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {

		Page<SsOrder> ssOrders = orderService.getAllApp2(pageNum, pageSize);
		List<SsOrder> ssAppList = ssOrders.getContent();

		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		SsOrderBean ssOrderBean = null;
		SsOrder ssOrder = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssOrder = ssAppList.get(i);
			ssOrderBean = new SsOrderBean();
			ssOrderBean.setCustomerId(ssOrder.getCustomerId());// 客户标识
			ssOrderBean.setOrderDate(ssOrder.getOrderDate());// 订单时间
			UserBean userBean = (UserBean) session.getAttribute("loginUser");// 由session中获取数据
			CompanyBean companyBean = userCenterService.findCompanyById(ssOrder.getCompanyId());
			ssOrderBean.setOrgName(companyBean.getName());//企业名称

			List<SsOrdersService> sos = sosService.getServiceId(ssOrder.getId());// 获取订单明细信息
			StringBuffer sb = new StringBuffer();
			StringBuffer stringbuf = null;
			try {
				for (int j = 0; j < sos.size(); j++) {
					String appVersion = sos.get(j).getAppVersion();// 获取版本
					String label = sos.get(j).getSsService().getLabel();// 获取服务
					stringbuf = sb.append(label).append(" ");
					ssOrderBean.setAppVersion(appVersion);// 产品版本
					ssOrderBean.setServices(stringbuf.toString());// 产品服务
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			ssOrderBean.setId(ssOrder.getId());// 订单id
			ssOrderBean.setStatus(ssOrder.getStatus());// 状态
			ssOrderBean.setTotal(ssOrder.getTotal());// 总价
			ssOrderBean.setDiscount(ssOrder.getDiscount());// 折扣
			ssOrderBean.setExpired(ssOrder.getExpired());// 日期

			SsAppBean ssApp = appService.getAppByAppname(ssOrder.getAppname());// 根据订单appname获取产品信息
			ssOrderBean.setAppname(ssApp.getDescription());// 产品中文名称
			list.add(ssOrderBean);
		}

		ModelAndView view = new ModelAndView("fenfa/check_list");
		view.addObject("ssOrderList", list);
		view.addObject("totalPageNum", ssOrders.getTotalPages());
		view.addObject("totalNum", ssOrders.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;

	}

	/**
	 * 根据状态和分页获取订单信息
	 * 
	 * @param pageNum
	 * @param session
	 * @param request
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/getOrdersByStatusAndPage")
	public @ResponseBody Map<String, Object> getOrdersByStatusAndPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			HttpSession session, HttpServletRequest request, @RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "status") String status) {
		Page<SsOrder> ssOrders = null;
		if (status.isEmpty()) {
			ssOrders = orderService.getAllOrders(pageNum, pageSize);
		} else {
			ssOrders = orderService.getAppByStatus(pageNum, pageSize, status);
		}
		List<SsOrder> ssAppList = ssOrders.getContent();
		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		SsOrderBean ssOrderBean = null;
		SsOrder ssOrder = null;
		for (int i = 0; i < ssAppList.size(); i++) {
			ssOrder = ssAppList.get(i);
			ssOrderBean = new SsOrderBean();
			ssOrderBean.setCustomerId(ssOrder.getCustomerId());// 客户标识
			ssOrderBean.setOrderDate(ssOrder.getOrderDate());// 订单时间
			UserBean userBean = (UserBean) session.getAttribute("loginUser");// 由session中获取数据
			CompanyBean companyBean = userCenterService.findCompanyById(ssOrder.getCompanyId());
			ssOrderBean.setOrgName(companyBean.getName());//企业名称

			List<SsOrdersService> sos = sosService.getServiceId(ssOrder.getId());// 获取订单明细信息
			StringBuffer sb = new StringBuffer();
			StringBuffer stringbuf = null;
			try {
				for (int j = 0; j < sos.size(); j++) {
					String appVersion = sos.get(j).getAppVersion();// 获取版本
					String label = sos.get(j).getSsService().getLabel();// 获取服务
					stringbuf = sb.append(label).append(" ");
					ssOrderBean.setAppVersion(appVersion);// 产品版本
					ssOrderBean.setServices(stringbuf.toString());// 产品服务
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			ssOrderBean.setId(ssOrder.getId());// 订单id
			ssOrderBean.setStatus(ssOrder.getStatus());// 状态
			ssOrderBean.setTotal(ssOrder.getTotal());// 总价
			ssOrderBean.setDiscount(ssOrder.getDiscount());// 折扣
			ssOrderBean.setExpired(ssOrder.getExpired());// 日期

			SsAppBean ssApp = appService.getAppByAppname(ssOrder.getAppname());// 根据订单appname获取产品信息
			ssOrderBean.setAppname(ssApp.getDescription());// 产品中文名称
			list.add(ssOrderBean);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPageNum", ssOrders.getTotalPages());
		map.put("orders", list);
		return map;
	}

	 /**
	  * 根据订单id获取对应企业和服务
	  * @param orderId
	  * @return
	  */
	 @RequestMapping(value="/findCompanyAndServicesByOrderId",method=RequestMethod.GET)
	 public @ResponseBody Map<String, Object> findCompanyAndServicesByOrderId(String orderId){
		 SsOrderBean ssOrderBean = orderService.findOrder(orderId);
		 CompanyBean companyBean = userCenterService.findCompanyById(ssOrderBean.getCompanyId());
		 List<SsOrdersServiceBean> ssOrdersServiceList = sosService.getServiceBeanByOrderid(orderId);
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("company", companyBean);
		 map.put("serviceList", ssOrdersServiceList);
		 map.put("order", ssOrderBean);
		 return map;
	 }

	/**
	 * 根据状态查询订单
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/orderSearch", method = RequestMethod.GET)
	public ModelAndView orderSearch(@RequestParam(value = "page", defaultValue = "1") int pageNum, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "status", defaultValue = "1") String status) {

		try {
			System.out.println(status);
			byte source[] = status.getBytes("iso8859-1");
			status = new String(source, "UTF-8");
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
		}

		Page<SsOrder> ssOrders = orderService.getAppByStatus(pageNum, pageSize, status);
		List<SsOrder> ssAppList = ssOrders.getContent();

		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		SsOrderBean ssOrderBean = null;
		SsOrder ssOrder = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssOrder = ssAppList.get(i);
			ssOrderBean = new SsOrderBean();
			ssOrderBean.setCustomerId(ssOrder.getCustomerId());// 客户标识
			ssOrderBean.setOrderDate(ssOrder.getOrderDate());// 订单时间
			UserBean userBean = (UserBean) session.getAttribute("loginUser");// 由session中获取数据
			CompanyBean companyBean = userCenterService.findCompanyById(ssOrder.getCompanyId());
			ssOrderBean.setOrgName(companyBean.getName());//企业名称

			List<SsOrdersService> sos = sosService.getServiceId(ssOrder.getId());// 获取订单明细信息
			StringBuffer sb = new StringBuffer();
			StringBuffer stringbuf = null;
			try {
				for (int j = 0; j < sos.size(); j++) {
					String appVersion = sos.get(j).getAppVersion();// 获取版本
					String label = sos.get(j).getSsService().getLabel();// 获取服务
					stringbuf = sb.append(label).append(" ");
					ssOrderBean.setAppVersion(appVersion);// 产品版本
					ssOrderBean.setServices(stringbuf.toString());// 产品服务
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			ssOrderBean.setId(ssOrder.getId());// 订单id
			ssOrderBean.setStatus(ssOrder.getStatus());// 状态
			ssOrderBean.setTotal(ssOrder.getTotal());// 总价
			ssOrderBean.setDiscount(ssOrder.getDiscount());// 折扣
			ssOrderBean.setExpired(ssOrder.getExpired());// 日期

			SsAppBean ssApp = appService.getAppByAppname(ssOrder.getAppname());// 根据订单appname获取产品信息
			ssOrderBean.setAppname(ssApp.getDescription());// 产品中文名称
			list.add(ssOrderBean);
		}

		ModelAndView view = new ModelAndView("fenfa/check_list");
		view.addObject("ssOrderList", list);
		view.addObject("totalPageNum", ssOrders.getTotalPages());
		view.addObject("totalNum", ssOrders.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;

	}

	/**
	 * 订单审核（市场用户）
	 * 
	 * @param id
	 * @param discount
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/checkOrder", method = RequestMethod.GET)
	public ModelAndView getOrder(@RequestParam(value = "id") String id, String discount,
			@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "expired") String expired, HttpSession session) {

		SsOrder order = orderService.findOrderById(id);
		double d = Double.parseDouble(discount.replace("%", "")) * 0.01;// 折扣转换
		order.setDiscount(d);
		order.setStatus(ConstantClassField.ORDER_STATUS_ENFORCE_MANAGE_UNAUDITED);// 设置状态
		// order.setOrderDate(new Date(System.currentTimeMillis()));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(expired);
			order.setExpired(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 获取customerId
		String companyId = order.getCompanyId();
		CompanyBean companyBean = userCenterService.findCompanyById(companyId);
		String firstRegion = companyBean.getFirstRegion();
		String secondRegion = companyBean.getSecondRegion();
		String thirdRegion = companyBean.getThirdRegion();
		String companyType = companyBean.getType();
		String companyName = companyBean.getName();
		String customerId = null;
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient("http://saas.ccqm.cn:8800/codeService/codeService?wsdl");
			Object[] obj = client.invoke("provideCustomerId", firstRegion, secondRegion, thirdRegion, companyType, companyName);
			List obj2 = (List) obj[0];
			customerId = (String) obj2.get(obj2.size() - 1);
			userCenterService.updateCustomerIdByCompanyId(customerId, companyId);//更新company表的CustomerId
			UserBean userBean = (UserBean)session.getAttribute("loginUser");
			userCenterService.updateCustomerIdByAccountId(customerId, userBean.getUserId());//更新account表的customerId
		} catch (Exception e) {
			e.printStackTrace();
		}
		order.setCustomerId(customerId);
		orderService.saveOrder(order);
		return new ModelAndView("redirect:/main/checkOrderList");

	}

	/**
	 * 实施订单列表(实施服务管理人员)
	 * 
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/checkOrderList2", method = RequestMethod.GET)
	public ModelAndView checkOrderList2(@RequestParam(value = "page", defaultValue = "1") int pageNum, HttpSession session,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {

		Page<SsOrder> ssOrders = orderService.getAllApp3(pageNum, pageSize);
		List<SsOrder> ssAppList = ssOrders.getContent();

		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		SsOrderBean ssOrderBean = null;
		SsOrder ssOrder = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssOrder = ssAppList.get(i);
			ssOrderBean = new SsOrderBean();
			UserBean userBean = (UserBean) session.getAttribute("loginUser");// 由session中获取数据customerid
			ssOrderBean.setCustomerId(userBean.getCustomerId());
			ssOrderBean.setOrderDate(ssOrder.getOrderDate());
			ssOrderBean.setOrgName(ssOrder.getOrgName());

			List<SsOrdersService> sos = sosService.getServiceId(ssOrder.getId());// 获取订单明细信息
			StringBuffer sb = new StringBuffer();
			StringBuffer stringbuf = null;
			try {
				for (int j = 0; j < sos.size(); j++) {
					String appVersion = sos.get(j).getAppVersion();// 获取版本
					String label = sos.get(j).getSsService().getLabel();// 获取服务
					stringbuf = sb.append(label).append(" ");
					ssOrderBean.setAppVersion(appVersion);// 产品版本
					ssOrderBean.setServices(stringbuf.toString());// 产品服务
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
			ssOrderBean.setId(ssOrder.getId());
			ssOrderBean.setStatus(ssOrder.getStatus());
			ssOrderBean.setTotal(ssOrder.getTotal());
			ssOrderBean.setDiscount(ssOrder.getDiscount() * 100);

			SsAppBean ssApp = appService.getAppByAppname(ssOrder.getAppname());// 根据订单appname获取产品信息
			ssOrderBean.setAppname(ssApp.getDescription());// 产品中文名称
			list.add(ssOrderBean);
		}
		ModelAndView view = new ModelAndView("fenfa/check_list2");
		view.addObject("ssOrderList", list);
		view.addObject("totalPageNum", ssOrders.getTotalPages());
		view.addObject("totalNum", ssOrders.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;

	}

	/**
	 * 实施服务单审核（实施服务管理人员）
	 * 
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	@RequestMapping(value = "/checkOrder2", method = RequestMethod.GET)
	public ModelAndView checkOrder2(@RequestParam(value = "id") String id,
			@RequestParam(value = "page", defaultValue = "1") int pageNum, @RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,
			HttpServletRequest request) {
		SsOrder order = orderService.findOrderById(id);
		String customerId = order.getCustomerId();
		createLicenseFile(customerId, request, id);// 生成文件
		orderService.updateStatusByOrderId(ConstantClassField.ORDER_STATUS_ENFORCE_UNAUDITED, id);
		return new ModelAndView("redirect:/main/checkOrderList2");

	}

	/**
	 * 实施服务单License文件
	 * 
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @return
	 */

	@RequestMapping(value = "/fileList", method = RequestMethod.GET)
	public ModelAndView fileList(@RequestParam(value = "id", defaultValue = "1") String id,
			@RequestParam(value = "page", defaultValue = "1") int pageNum, HttpSession session,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "status", defaultValue = "1") String status) {

		Page<SsOrder> ssOrders = orderService.getAllApp4(pageNum, pageSize);
		List<SsOrder> ssAppList = ssOrders.getContent();

		List<SsOrderBean> list = new ArrayList<SsOrderBean>();
		SsOrderBean ssOrderBean = null;
		SsOrder ssOrder = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssOrder = ssAppList.get(i);
			ssOrderBean = new SsOrderBean();
			ssOrderBean.setCustomerId(ssOrder.getCustomerId());
			ssOrderBean.setOrderDate(ssOrder.getOrderDate());
			ssOrderBean.setOrgName(ssOrder.getOrgName());

			List<SsOrdersService> sos = sosService.getServiceId(ssOrder.getId());// 获取订单明细信息
			StringBuffer sb = new StringBuffer();
			StringBuffer stringbuf = null;
			try {
				for (int j = 0; j < sos.size(); j++) {
					String appVersion = sos.get(j).getAppVersion();// 获取版本
					String label = sos.get(j).getSsService().getLabel();// 获取服务
					stringbuf = sb.append(label).append(" ");
					ssOrderBean.setAppVersion(appVersion);// 产品版本
					ssOrderBean.setServices(stringbuf.toString());// 产品服务
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
			ssOrderBean.setId(ssOrder.getId());
			ssOrderBean.setStatus(ssOrder.getStatus());
			ssOrderBean.setTotal(ssOrder.getTotal());
			ssOrderBean.setDiscount(ssOrder.getDiscount() * 100);
			SsAppBean ssApp = appService.getAppByAppname(ssOrder.getAppname());// 根据订单appname获取产品信息
			ssOrderBean.setAppname(ssApp.getDescription());// 产品中文名称
			list.add(ssOrderBean);
		}
		ModelAndView view = new ModelAndView("fenfa/file_list");
		view.addObject("ssOrderList", list);
		view.addObject("totalPageNum", ssOrders.getTotalPages());
		view.addObject("totalNum", ssOrders.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;
	}

	/**
	 * 生成License文件
	 */
	public void createLicenseFile(String customerId, HttpServletRequest request, String id) {
		String path = request.getSession().getServletContext().getRealPath("fenfa/upload");// path表示创建文件的路径
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		SsOrder order = orderService.findOrderById(id);
		String fileName = order.getOrgName() +"_"+id+ ".lic";// fileName表示创建的文件名；
		LicenseBean licenseBean = new LicenseBean();
		SsAppBean ssAppBean = appService.getAppByAppname(order.getAppname());// 根据订单的appname获取产品信息
		licenseBean.setAppname(ssAppBean.getDescription());// 获取产品
		licenseBean.setCustomerId(order.getCustomerId());// 获取客户标识
		licenseBean.setExpire(order.getExpired());// 获取授权日期
		List<SsOrdersService> serviceList = sosService.getServiceId(order.getId());// 获取服务ID
		StringBuffer sb = new StringBuffer();
		StringBuffer stringbuf = null;
		for (int i = 0; i < serviceList.size(); i++) {
			SsOrdersService ssOrdersService = serviceList.get(i);
			String label = ssOrdersService.getSsService().getLabel();
			stringbuf = sb.append(label).append(",");
		}
		stringbuf.deleteCharAt(stringbuf.length() - 1);// 去除最后一个逗号
		licenseBean.setServices(sb.toString());

		ObjectMapper mapper = new ObjectMapper();// 转换为json格式
		try {
			String json = mapper.writeValueAsString(licenseBean);
			System.out.println(json);
			EncryptDES encryptDES = new EncryptDES(env.getProperty("desKey"));
			encryptDES.encryptStringToFile(json, path+"/"+fileName);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 向文件写入内容license
	 * 
	 * @param file
	 * @param content
	 * @throws Exception
	 */
	private void createcontentToTxt(File file, String content) {
		byte bt[] = new byte[1024];
		bt = content.getBytes();
		try {
			FileOutputStream in = new FileOutputStream(file);
			try {
				in.write(bt, 0, bt.length);
				in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/download")
	public String download(String id, HttpServletRequest request, HttpServletResponse response) {
		String contentType = "application/octet-stream";
		SsOrder order = orderService.findOrderById(id);
		String storeName = order.getOrgName() +"_"+id+ ".lic";
		download(request, response, contentType, storeName);// 下载文件
		orderService.updateStatusByOrderId(ConstantClassField.ORDER_STATUS_ENFORCED, id);
		return null;
	}

	private void download(HttpServletRequest request, HttpServletResponse response, String contentType, String storeName) {
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			// 获取项目根目录
			String ctxPath = request.getSession().getServletContext().getRealPath("fenfa/upload");

			// 获取文件名
			String downLoadPath = ctxPath + "/" + storeName;

			// 获取文件
			File file = new File(downLoadPath);
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
				return;
			}
			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();

			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(storeName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
			bis.close();
			bos.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 产品管理
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/listApp")
	public ModelAndView getAllapp(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {

		ModelAndView view = new ModelAndView("fenfa/AppList");
		Page<SsApp> ssApps = appService.getAllApp(pageNum, pageSize);

		List<SsApp> ssAppList = ssApps.getContent();

		List<SsAppBean> list = new ArrayList<SsAppBean>();
		SsAppBean ssAppBean = null;
		SsApp ssApp = null;

		for (int i = 0; i < ssAppList.size(); i++) {
			ssApp = ssAppList.get(i);
			ssAppBean = new SsAppBean();
			ssAppBean.setAppname(ssApp.getAppname());
			ssAppBean.setDescription(ssApp.getDescription());
			ssAppBean.setVersion(ssApp.getVersion());
			ssAppBean.setType(ssApp.getType());
			list.add(ssAppBean);
		}
		view.addObject("AppList", list);
		view.addObject("pageNum", pageNum);
		view.addObject("totalPageNum", ssApps.getTotalPages());
		view.addObject("totalNum", ssApps.getTotalElements());
		view.addObject("regions", new Regions().getRegionOrgType());
		return view;
	}

	/**
	 * 产品新增、修改
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ssAppBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveApp", method = RequestMethod.POST)
	public ModelAndView handleRequest(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, SsAppBean ssAppBean) throws Exception {

		appService.saveAndUpdateApp(ssAppBean);
		return new ModelAndView("redirect:/main/listApp");

	}

	/**
	 * 产品删除
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ssAppBean
	 * @return
	 */
	@RequestMapping(value = "/deleteApp")
	public ModelAndView deleteApp(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, SsAppBean ssAppBean) {
		String appname = ssAppBean.getAppname();
		appService.deleteByAppname(ssAppBean);
		return new ModelAndView("redirect:/main/listApp");

	}

	/**
	 * 产品服务列表
	 * 
	 * @param appname
	 * @return
	 */
	@RequestMapping(value = "/serviceList", method = RequestMethod.GET)
	public ModelAndView serviceList(String appname, @RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, HttpSession session) {
		ModelAndView view = new ModelAndView("fenfa/ServiceList");

		// 显示产品服务列表
		Page<SsService> ssServiceS = ssService.getAllServices(pageNum, pageSize);
		List<SsService> ssServiceContent = ssServiceS.getContent();

		List<SsServiceBean> list = new ArrayList<SsServiceBean>();
		SsServiceBean ssServiceBean = null;
		SsService ssService = null;
		for (int i = 0; i < ssServiceContent.size(); i++) {
			ssService = ssServiceContent.get(i);
			ssServiceBean = new SsServiceBean();
			ssServiceBean.setId(ssService.getId());
			UserBean userBean = (UserBean) session.getAttribute("loginUser");
			ssServiceBean.setCustomerId(userBean.getCustomerId());
			ssServiceBean.setLabel(ssService.getLabel());
			ssServiceBean.setPid(ssService.getPid());
			ssServiceBean.setPrice(ssService.getPrice());
			ssServiceBean.setRealId(ssService.getRealId());
			ssServiceBean.setRemark(ssService.getRemark());
			ssServiceBean.setSeq(ssService.getSeq());
			SsAppBean ssAppBean = new SsAppBean();
			try {
				BeanUtils.copyProperties(ssAppBean, ssService.getSsApp());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ssServiceBean.setSsAppBean(ssAppBean);
			ssServiceBean.setServiceType(ssService.getServiceType());
			String appname2 = ssService.getSsApp().getAppname();// 获取到产品id
			String realId = ssService.getRealId();// 获取服务真实Id
			List<SsVersService> ssVerServiceList = ssVersServiceService.getAppVersion(appname2, realId);// 根据产品Id和服务reald查找版本服务实体
			for(int j=0;j<ssVerServiceList.size();j++){
				String appVersion = ssVerServiceList.get(j).getAppVersion();// 获取版本
				ssServiceBean.setAppVersion(appVersion);
				list.add(ssServiceBean);
			}


		}
		List<SsAppBean> appList = appService.getAllApp();
		view.addObject("appList", appList);
		view.addObject("serviceList", list);
		view.addObject("totalPageNum", ssServiceS.getTotalPages());
		view.addObject("totalNum", ssServiceS.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;

	}
	/**
	 * 产品服务列表上传文件后
	 * 
	 * @param appname
	 * @return
	 */
	@RequestMapping(value = "/serviceList1")
	public ModelAndView serviceList1(String appname, @RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, HttpSession session) {
		ModelAndView view = new ModelAndView("fenfa/ServiceList");
		// 显示产品服务列表
		Page<SsService> ssServiceS = ssService.findServicesByAppname(appname, pageNum, pageSize);
		List<SsService> ssServiceContent = ssServiceS.getContent();
		List<SsServiceBean> list = new ArrayList<SsServiceBean>();
		SsServiceBean ssServiceBean = null;
		SsService ssService = null;
		for (int i = 0; i < ssServiceContent.size(); i++) {
			ssService = ssServiceContent.get(i);
			ssServiceBean = new SsServiceBean();
			ssServiceBean.setId(ssService.getId());
			UserBean userBean = (UserBean) session.getAttribute("loginUser");
			ssServiceBean.setCustomerId(userBean.getCustomerId());
			ssServiceBean.setLabel(ssService.getLabel());
			ssServiceBean.setPid(ssService.getPid());
			ssServiceBean.setPrice(ssService.getPrice());
			ssServiceBean.setRealId(ssService.getRealId());
			ssServiceBean.setRemark(ssService.getRemark());
			ssServiceBean.setSeq(ssService.getSeq());
			SsAppBean ssAppBean = new SsAppBean();
			try {
				BeanUtils.copyProperties(ssAppBean, ssService.getSsApp());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ssServiceBean.setSsAppBean(ssAppBean);
			ssServiceBean.setServiceType(ssService.getServiceType());
			String appname2 = ssService.getSsApp().getAppname();// 获取到产品id
			String realId = ssService.getRealId();// 获取服务真实Id
			List<SsVersService> ssVerServiceList = ssVersServiceService.getAppVersion(appname2, realId);// 根据产品Id和服务reald查找版本服务实体
			//一个服务有两个版本，但是服务列表只能有一条服务
			if(ssVerServiceList.size()>1){
				String appVersion ="";
				String appver = "";
				for(int j=0;j<ssVerServiceList.size();j++){
					appVersion += ssVerServiceList.get(j).getAppVersion()+";";// 获取版本
					appver = appVersion.substring(0, appVersion.length()-1);
				}
				ssServiceBean.setAppVersion(appver);
			}else{
				String appVersion = ssVerServiceList.get(0).getAppVersion();
				ssServiceBean.setAppVersion(appVersion);
			}
			list.add(ssServiceBean);

		}
		List<SsAppBean> appList = appService.getAllApp();
		view.addObject("appList", appList);
		view.addObject("serviceList", list);
		view.addObject("appName", appname);
		view.addObject("totalPageNum", ssServiceS.getTotalPages());
		view.addObject("totalNum", ssServiceS.getTotalElements());
		view.addObject("pageNum", pageNum);
		return view;

	}
	/**
	 * 分页获取产品下的服务列表
	 * 
	 * @param appname
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/getServiceList")
	public String getServiceList(String appname, @RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, Model model)
			throws IllegalAccessException, InvocationTargetException {
		Page<SsService> services = ssService.findServicesByAppname(appname, pageNum, pageSize);

		List<SsService> serviceList = services.getContent();
		List<SsServiceBean> serviceBeanList = new ArrayList<SsServiceBean>();

		SsService service = null;
		SsServiceBean serviceBean = null;

		for (int i = 0; i < serviceList.size(); i++) {
			service = serviceList.get(i);
			serviceBean = new SsServiceBean();

			// 获取所属销售版本信息
			//String ver = ssVersServiceService.getServiceSaleVer(service.getRealId());
			String appname1 = service.getSsApp().getAppname();
			List<SsVersService> ssVerServiceList = ssVersServiceService.getAppVersion(appname1, service.getRealId());// 根据产品Id和服务reald查找版本服务实体
			//一个服务有两个版本，但是服务列表只能有一条服务
			if(ssVerServiceList.size()>1){
				String appVersion ="";
				String appver = "";
				for(int j=0;j<ssVerServiceList.size();j++){
					appVersion += ssVerServiceList.get(j).getAppVersion()+";";// 获取版本
					appver = appVersion.substring(0, appVersion.length()-1);
				}
				serviceBean.setAppVersion(appver);
			}else{
				String appVersion = ssVerServiceList.get(0).getAppVersion();
				serviceBean.setAppVersion(appVersion);
			}
			BeanUtils.copyProperties(serviceBean, service);
			//serviceBean.setAppVersion(ver);

			serviceBeanList.add(serviceBean);
		}

		model.addAttribute("appName", appname);
		model.addAttribute("serviceList", serviceBeanList);
		model.addAttribute("totalPageNum", services.getTotalPages());
		model.addAttribute("totalNum", services.getTotalElements());
		model.addAttribute("pageNum", pageNum);
		return "fenfa/ServiceList";
	}

	/**
	 * 按产品名称查询服务列表
	 * 
	 * @param appname
	 * @return
	 */
	@RequestMapping(value = "/findServiceByappname", method = RequestMethod.GET)
	public ModelAndView findServiceByappname(String appname, @RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {
		ModelAndView view = new ModelAndView("fenfa/ServiceList");
		List<SsServiceBean> serviceList = ssService.findByappname(appname);
		List<SsAppBean> appList = appService.getAllApp();
		view.addObject("appList", appList);
		view.addObject("serviceList", serviceList);
		view.addObject("appName", appname);
		return view;
	}

	/**
	 * 保存服务
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ssServiceBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveService", method = RequestMethod.POST)
	public ModelAndView saveService(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, SsServiceBean ssServiceBean) throws Exception {

		ssService.saveAndUpdateApp(ssServiceBean);
		return new ModelAndView("redirect:/main/serviceList");

	}

	/**
	 * 删除服务
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param ssServiceBean
	 * @return
	 */

	@RequestMapping(value = "/deleteService")
	public ModelAndView deleteService(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize, String id) {
		ssService.deleteByPks(id);
		return new ModelAndView("redirect:/main/serviceList");
	}

	/**
	 * 导入excel
	 * 
	 * @param uploadExcel
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public ModelAndView importExcel(@RequestParam("uploadExcel") CommonsMultipartFile uploadExcel,String appName) throws Exception {
		System.out.println("=========");
		// 1.获取上传文件
		if (uploadExcel != null) {
			// 2.导入文件

			ssService.importExcel(appName,uploadExcel);
		}
		ModelAndView view = new ModelAndView("redirect:/main/serviceList1");
		view.addObject("appname", appName);
		return view;

	}
	
	/**
	 * 导入excel
	 * 
	 * @param uploadExcel
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/importExcel1", method = RequestMethod.POST)
	@ResponseBody
	public String importExcel1(@RequestParam("uploadExcel") CommonsMultipartFile uploadExcel,String appName,Model model) throws Exception {
		System.out.println("=========");
		String succ ="";//判断导入是否成功
		// 1.获取上传文件
		if (uploadExcel != null) {
			// 2.导入文件
			succ = ssService.importExcel1(appName,uploadExcel);
		}
		model.addAttribute("succ", succ);
		model.addAttribute("appname", appName);
		return succ;

	}
	/**
	 * 产品新增时，查找产品是否已存在
	 * @param appname
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/findAppByAppname")
	@ResponseBody
	public String findAppByAppname(String appname,Model model){
		System.out.println("=========");
		String isExist= appService.findAppByAppname(appname);
		model.addAttribute("isExist", isExist);
		return isExist;

	}
	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister(Model model) {
		Regions regions = new Regions();
		String orgType[] = regions.getRegionOrgType();
		String firstRegions[] = regions.getFirstRegions();
		model.addAttribute("orgType", orgType);
		model.addAttribute("firstRegions", firstRegions);
		return "register";
	}

	/**
	 * 获取二级行政区
	 * 
	 * @return
	 */
	@RequestMapping("getSecondRegions")
	public @ResponseBody String[] getSecondRegions(String firstRegion) {
		Regions regions = new Regions();
		String secondRegions[] = regions.getSecondRegions(firstRegion);
		return secondRegions;
	}

	/**
	 * 获取三级行政区
	 * 
	 * @return
	 */
	@RequestMapping("getThirdRegions")
	public @ResponseBody String[] getThirdRegions(String firstRegion, String secondRegion) {
		Regions regions = new Regions();
		String thirdRegions[] = regions.getThirdRegions(firstRegion, secondRegion);
		return thirdRegions;
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "register")
	public @ResponseBody int register(UserBean userBean) {
		userCenterService.saveUser(userBean);
		return 1;
	}

	/**
	 * 注册校验用户是否存在
	 * 
	 * @return
	 */
	@RequestMapping("getUsername")
	public @ResponseBody int getUsername(String username) {
		UserBean user = userCenterService.findByUsername(username);
		if(user == null){
			return 1;
		}
		return 2;
	}
	/**
	 * 根据用户类型跳转到相应的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "goToPage", method = RequestMethod.GET)
	public String goToPage(HttpSession session) {
		UserBean userBean = (UserBean) session.getAttribute("loginUser");
		// 根据用户获取对应菜单 start
		Set<RoleBean> roles = userCenterService.getUserRole(userBean.getUsername());
		List<PermissionBean> permissionList = new ArrayList<PermissionBean>();
		Iterator<RoleBean> it = roles.iterator();
		while (it.hasNext()) {
			List<PermissionBean> permissions = userCenterService.findPermissionByRoleId(it.next().getId());
			for (PermissionBean bean : permissions) {
				permissionList.add(bean);
			}
		}
		List<MenuBean> menuBeanList = new ArrayList<MenuBean>();
		for (int i = 0; i < permissionList.size(); i++) {
			PermissionBean permissionBean = permissionList.get(i);
			if (permissionBean.getResType() == ResourcesType.Menu) {
				MenuBean menuBean = userCenterService.findMeunById(permissionBean.getResId());
				menuBeanList.add(menuBean);
			}
		}
		// end
		session.setAttribute("menuList", menuBeanList);
		Iterator<RoleBean> iter = roles.iterator();
		RoleBean roleBean = null;
		String url = "";
		if (!userBean.isAdmin()) {
			url = "redirect:/main/welcome";
			while (iter.hasNext()) {
				roleBean = iter.next();
				if (roleBean.getName().equals("市场人员")) {
					url = "redirect:/main/checkOrderList";
				} else if (roleBean.getName().equals("实施服务管理人员")) {
					url = "redirect:/main/checkOrderList2";
				} else if (roleBean.getName().equals("实施人员")) {
					url = "redirect:/main/fileList";
				} else if (roleBean.getName().equals("研发管理人员")) {
					url = "redirect:/main/listApp";
				}
			}
		} else {
			url = "redirect:/main/checkOrderList";
		}
		return url;
	}
	
}