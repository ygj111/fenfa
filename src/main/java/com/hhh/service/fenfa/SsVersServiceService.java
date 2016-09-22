package com.hhh.service.fenfa;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhh.dao.entity.fenfa.SsService;
import com.hhh.dao.entity.fenfa.SsVersService;
import com.hhh.dao.fenfa.SsServiceDao;
import com.hhh.dao.fenfa.SsVersServiceDao;
import com.hhh.web.model.fenfa.SsAppBean;
import com.hhh.web.model.fenfa.SsServiceBean;

@Component
@Transactional
public class SsVersServiceService {
	private final static Logger logger = LoggerFactory.getLogger(SsVersService.class);
	@Autowired
	private SsVersServiceDao ssVersServiceDao;
	@Autowired
	private SsServiceDao ssServiceDao;
	
	/**
	 * 根据产品id和产品版本获取对应服务
	 * @param appname
	 * @param appVersion
	 * @param serviceType
	 * @return
	 */
	@RequestMapping("getServicesByAppnameAndVersion")
	public List<SsServiceBean> getServicesByAppnameAndVersion(String appname,String appVersion){
		List<SsVersService> ssVersServiceList = ssVersServiceDao.findByAppnameAndAppVersion(appname, appVersion);
		List<SsServiceBean> beanList = new ArrayList<SsServiceBean>();
		SsServiceBean bean = null;
		for(SsVersService ssVersService:ssVersServiceList){
			bean = new SsServiceBean();
			SsService ssService = ssServiceDao.findByRealId(ssVersService.getRealId());
			bean.setId(ssService.getId());
			bean.setCustomerId(ssService.getCustomerId());
			bean.setPrice(ssService.getPrice());
			bean.setSeq(ssService.getSeq());
			bean.setLabel(ssService.getLabel());
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
			bean.setSsAppBean(ssAppBean);
			bean.setPid(ssService.getPid());
			bean.setRealId(ssService.getRealId());
			bean.setRemark(ssService.getRemark());
			bean.setServiceType(ssService.getServiceType());
			beanList.add(bean);
		}
		return beanList;
	}
	
	
	/**
	 * 根据产品realid和appname查找版本
	 * @param appname
	 * @param realId
	 * @return
	 */
	public List<SsVersService> getAppVersion(String appname, String realId) {
		
		return ssVersServiceDao.findByAppnameAndRealId(appname,realId);
	
		
	}
	
	/**
	 * 根据服务id查找所属的销售版本，如果有多个版本，拼接成“;”号隔开的字符串
	 * @param realId
	 * @return
	 */
	public String getServiceSaleVer(String realId) {
		String ver = "";
		List<SsVersService> vers = ssVersServiceDao.findByRealId(realId);
		
		if (vers != null) {
			for (int i = 0; i < vers.size(); i++) {
				ver += vers.get(i).getAppVersion() + ";";
			}
			
			// 去掉最后的分号
			if (ver.length() > 2)
				ver = ver.substring(0, ver.length() - 2);
		}
		
		return ver;
	}

	/**
	 * 根据产品id获取对应服务
	 * @param appname
	 * @param serviceType
	 * @return
	 */
	public List<SsServiceBean> getAllServices(String appname) {
		List<SsVersService> ssVersServiceList = ssVersServiceDao.findByAppname(appname);
		List<SsServiceBean> beanList = new ArrayList<SsServiceBean>();
		SsServiceBean bean = null;
		for(SsVersService ssVersService:ssVersServiceList){
			bean = new SsServiceBean();
			SsService ssService = ssServiceDao.findByRealId(ssVersService.getRealId());
			bean.setId(ssService.getId());
			bean.setCustomerId(ssService.getCustomerId());
			bean.setPrice(ssService.getPrice());
			bean.setSeq(ssService.getSeq());
			bean.setLabel(ssService.getLabel());
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
			bean.setSsAppBean(ssAppBean);
			bean.setPid(ssService.getPid());
			bean.setRealId(ssService.getRealId());
			bean.setRemark(ssService.getRemark());
			bean.setServiceType(ssService.getServiceType());
			beanList.add(bean);
		}
		return beanList;
	}
}
