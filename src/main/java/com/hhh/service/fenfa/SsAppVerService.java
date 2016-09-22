package com.hhh.service.fenfa;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor.SAAJPreInInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsAppVer;
import com.hhh.dao.entity.fenfa.SsVersService;
import com.hhh.dao.fenfa.SsAppVerDao;
import com.hhh.dao.fenfa.SsVersServiceDao;
import com.hhh.web.model.fenfa.SsAppVerBean;
import com.hhh.web.model.fenfa.SsVerServiceBean;

@Component
@Transactional
public class SsAppVerService {
	private final static Logger logger = LoggerFactory.getLogger(SsAppVerService.class);
	@Autowired
	private SsAppVerDao ssAppVerDao;
	@Autowired
	private SsVersServiceDao ssVersServiceDao;
	
	/**
	 * 根据产品id获取对应产品的版本
	 * @param appname
	 * @return
	 */
	public List<SsAppVerBean> getVersionsByAppname(String appname){
//		List<SsVersService> ssVersServices = ssVersServiceDao.findByAppname(appname);
		List<SsAppVer> ssAppVerList = ssAppVerDao.findBySsAppAppname(appname);
		SsAppVerBean bean = null;
		List<SsAppVerBean> beanList = new ArrayList<SsAppVerBean>();
		for(SsAppVer ssAppVer:ssAppVerList){
			bean = new SsAppVerBean();
			bean.setId(ssAppVer.getId());
			bean.setAppname(ssAppVer.getSsApp().getAppname());
			bean.setAppVersion(ssAppVer.getAppVersion());
			beanList.add(bean);
		}
		return beanList;
	}
}
