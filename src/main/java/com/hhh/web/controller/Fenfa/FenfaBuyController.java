package com.hhh.web.controller.Fenfa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.dao.entity.fenfa.SsAppVer;
import com.hhh.dao.entity.fenfa.SsOrder;
import com.hhh.dao.entity.fenfa.SsOrdersService;
import com.hhh.dao.entity.fenfa.SsService;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.web.model.UserBean;
import com.hhh.service.fenfa.SsAppService;
import com.hhh.service.fenfa.SsAppVerService;
import com.hhh.service.fenfa.SsOrderService;
import com.hhh.service.fenfa.SsOrgSerService;
import com.hhh.service.fenfa.SsServiceService;
import com.hhh.service.fenfa.SsVersServiceService;
import com.hhh.web.model.fenfa.SsAppBean;
import com.hhh.web.model.fenfa.SsAppVerBean;
import com.hhh.web.model.fenfa.SsServiceBean;
import com.hhh.web.model.fenfa.SsVerServiceBean;
import com.hhh.web.model.fenfa.TreeNode;
import com.hhh.web.model.fenfa.TreeState;

@Controller
@RequestMapping("/buy")
public class FenfaBuyController {
	@Autowired 
	private SsServiceService ssService;
	@Autowired 
	private SsOrderService orderService;
	@Autowired 
	private SsAppService appService;
	@Autowired 
	private SsOrgSerService orgSerService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private SsAppVerService ssAppVerService;
	@Autowired
	private SsVersServiceService ssVersServiceService;
	@Autowired
	private SsServiceService sss;
	
	/**
	 * 跳转到购买产品页面
	 * @param appname
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toBuyApp",method=RequestMethod.POST)
	public String toBuyApp(String appname,Model model){
		SsAppBean ssAppBean = appService.getAppByAppname(appname);
		double totalPrice = ssService.getTotalPriceByAppname(appname);
		List<SsAppVerBean> versionList = ssAppVerService.getVersionsByAppname(appname);
		model.addAttribute("ssAppBean", ssAppBean);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("versionList", versionList);
		return "buy/buyApp";
	}
	
	/**
	 * 购买后跳转到订单页面
	 * @param appname
	 * @param totalPrice
	 * @param serviceIds
	 * @param model
	 * @return
	 */
	@RequestMapping(value="buy",method=RequestMethod.POST)
	public String buy(String appname,String serviceIds[],Model model){
		SsAppBean ssAppBean = appService.getAppByAppname(appname);
		List<SsServiceBean> serviceList = new ArrayList<SsServiceBean>();
		for(String serviceId:serviceIds){
			serviceList.add(ssService.getServiceByRealId(serviceId));
		}
		//把label设成如"监督服务->采购服务->售后服务"的形式
		for(SsServiceBean bean:serviceList){
			StringBuffer sb = new StringBuffer(bean.getLabel());
			String pid = bean.getPid();
			String realId = bean.getRealId();
			while(pid!=null&&!pid.equals("")){
				SsService service = ssService.findServiceByRealId(pid);
				sb.insert(0, service.getLabel()+"->");
				pid = service.getPid();
			}
			if(bean.getPrice()!=0)bean.setLabel(sb.toString());
		}
		List<SsServiceBean> choseServices = new ArrayList<SsServiceBean>();
		for (int i = 0; i < serviceList.size(); i++) {
			if(serviceList.get(i).getPrice()!=0)choseServices.add(serviceList.get(i));
		}
		double totalPrice = getTotalPriceByServices(serviceIds);//总价
		model.addAttribute("ssAppBean", ssAppBean);
		model.addAttribute("choseServices", choseServices);
		model.addAttribute("totalPrice", totalPrice);
		return "buy/order";
	}
	
	/**
	 * 根据产品id,版本号和服务类型获取对应的服务
	 * @param appname
	 * @param appVersion
	 * @return
	 */
	@RequestMapping("getServicesByAppnameAndVersion")
	public @ResponseBody List<TreeNode> getServicesByAppnameAndVersion(String appname,String appVersion,String serviceType){
		List<SsServiceBean> ssServiceList = ssVersServiceService.getServicesByAppnameAndVersion(appname,appVersion);
		List<TreeNode> list = new ArrayList<TreeNode>();
		for(SsServiceBean bean : ssServiceList){
			TreeNode node = new TreeNode();
			node.setId(bean.getRealId());
			node.setText(bean.getLabel());
			if(bean.getPid()==null||bean.getPid().equals("")){
				node.setParent("#");
				node.setText("菜单");
			}else{
				node.setParent(bean.getPid());
			}
			if(serviceType.equals(bean.getServiceType())){
				list.add(node);
			}
		}
		return list;
	}
	
	/**
	 * 根据产品Id和服务类型获取对应服务,对应产品版本的服务打勾
	 * @param appname
	 * @return
	 */
	@RequestMapping("getAllServices")
	public @ResponseBody List<TreeNode> getAllServices(String appname,String appVersion,String serviceType){
		List<SsServiceBean> ssServiceList = sss.findByAppName(appname);
		List<SsServiceBean> listOfVersion = ssVersServiceService.getServicesByAppnameAndVersion(appname,appVersion);
		List<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode node = null;
		node = new TreeNode();
		node.setId("0");
		if(serviceType==null){
			node.setText("服务");
		}else{
			if(serviceType.equals("0")){//功能
				node.setText("功能");
			}else{//非功能
				node.setText("非功能");
			}
		}
		node.setParent("#");
		TreeState state1 = new TreeState();
		state1.setOpened(true);
		node.setState(state1);
		list.add(node);
		for(int i=0;i<ssServiceList.size();i++){
			node = new TreeNode();
			node.setId(ssServiceList.get(i).getRealId());
			node.setText(ssServiceList.get(i).getLabel());
			if(ssServiceList.get(i).getPid().equals("")||ssServiceList.get(i).getPid()==null){
				node.setParent("0");
			}else{
				node.setParent(ssServiceList.get(i).getPid());
			}
			for(SsServiceBean s : listOfVersion){//勾选对应版本的服务
				TreeState state2 = new TreeState();
				if(ssServiceList.get(i).getId().equals(s.getId())){
					state2.setSelected(true);
					node.setState(state2);
				}else{
					state2.setSelected(false);
					node.setState(state2);
				}
			}
			if(serviceType==null){
				list.add(node);
			}else{
				if(serviceType.equals(ssServiceList.get(i).getServiceType())){
					list.add(node);
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据服务获取总价格
	 */
	@RequestMapping("getTotalPriceByServices")
	public @ResponseBody double getTotalPriceByServices(@RequestParam(value="ids[]")String realIds[]){
		double totalPrice = 0;
		for(String realId:realIds){
			SsServiceBean service = ssService.getServiceByRealId(realId);
			totalPrice += service.getPrice();
		}
		return totalPrice;
	}
	
	/**
	 * 生成订单
	 */
	@RequestMapping("generateOrder")
	public String generateOrder(HttpSession session,String appname,String appVersion,String totalPrice,@RequestParam(value="serviceIds")String serviceIds[]){
		UserBean userBean = (UserBean)session.getAttribute("loginUser");
		SsOrder ssOrder = new SsOrder();
		SsAppBean ssAppBean = appService.getAppByAppname(appname);
//		ssOrder.setCustomerId(userBean.getCustomerId());//企业id
		ssOrder.setCompanyId(userBean.getCompany().getId());
		ssOrder.setOrgName(userBean.getCompany().getName());//企业名称
		ssOrder.setAppname(appname);//产品id
		Date date = new Date();
		ssOrder.setOrderDate(date);//订单生成时间
		ssOrder.setTotal(Double.valueOf(totalPrice));//订单总价
		ssOrder.setStatus("0");//状态
		List<SsOrdersService> ssOrdersServiceList = new ArrayList<SsOrdersService>();
		SsOrdersService ssOrderService = null;
		for(String serviceId : serviceIds){
			ssOrderService = new SsOrdersService();
			ssOrderService.setSsOrder(ssOrder);
			ssOrderService.setAppVersion(appVersion);
			ssOrderService.setSsService(ssService.findServiceByRealId(serviceId));
			ssOrdersServiceList.add(ssOrderService);
		}
		ssOrder.setSsOrdersServices(ssOrdersServiceList);//订单细表
		orderService.saveOrder(ssOrder);
		return "redirect:/main/showSsOrder";
	}
}
