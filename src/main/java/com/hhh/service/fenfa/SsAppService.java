package com.hhh.service.fenfa;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsApp;
import com.hhh.dao.fenfa.SsAppDao;
import com.hhh.web.model.fenfa.SsAppBean;

@Component
@Transactional
public class SsAppService {
	private final static Logger logger = LoggerFactory.getLogger(SsAppService.class);
   
	@Autowired
	private SsAppDao appDao;
	@Autowired
	SsServiceService ssService;
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public List<SsAppBean> getAllApp() {
		
		List<SsApp> appnames = (List<SsApp>) appDao.findAll();
		
		List<SsAppBean> appnameBeans = new ArrayList<SsAppBean>();
		
		for (int i = 0; i < appnames.size(); i++) {
			SsApp ssApp = appnames.get(i);
			SsAppBean appBean = new SsAppBean();
			appBean.setAppname(ssApp.getAppname());
			appBean.setDescription(ssApp.getDescription());
			appBean.setVersion(ssApp.getVersion());
			appBean.setType(ssApp.getType());
			appnameBeans.add(appBean);
		}
		
		return appnameBeans;
	}
	
	/**
	 * 保存
	 * @param ssAppBean
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public void saveAndUpdateApp(SsAppBean ssAppBean) {
		SsApp ssApp=toSsApp(ssAppBean);
		appDao.save(ssApp);
	}
	
   /**
    * 实体转换
    * @param ssAppBean
    * @return
    */
	private SsApp toSsApp(SsAppBean ssAppBean) {
		SsApp ssApp = new SsApp();
		ssApp.setAppname(ssAppBean.getAppname());
		ssApp.setDescription(ssAppBean.getDescription());
		ssApp.setSsServices(ssAppBean.getSsServices());
		ssApp.setVersion(ssAppBean.getVersion());
		ssApp.setType(ssAppBean.getType());
		return ssApp;
	}
	/**
	 * 删除
	 * @param ssAppBean
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
		public void deleteByPks(SsAppBean ssAppBean) {
			SsApp ssApp=toSsApp(ssAppBean);
			appDao.delete(ssApp);
		}

	/**
	 * 删除产品
	 * @param ssAppBean
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
		public void deleteByAppname(SsAppBean ssAppBean) {
			String appname = ssAppBean.getAppname();
			//判断是否是多选删除
			if(appname.indexOf(",") != -1){//多选删除
				String[] apps = appname.split(",");
				for(int i=0;i<apps.length;i++){
					String appName = apps[i];
					SsAppBean ssAppBean1 = new SsAppBean();
					ssAppBean1.setAppname(appName);
					SsApp ssApp=toSsApp(ssAppBean1);
					ssService.deleteImport(appName);
					appDao.delete(ssApp);
				}
			}else{//单选删除
				SsApp ssApp=toSsApp(ssAppBean);
				ssService.deleteImport(appname);
				appDao.delete(ssApp);
			}
			
		}
	/**
	 * 获取分页数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public Page<SsApp> getAllApp(int pageNum, int pageSize) {
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		Page<SsApp> ssApps = appDao.findAll(pageReq);
			return ssApps;
	}
	
	/**
	 * 根据appname获取产品
	 * @param appname
	 * @return
	 */
	public SsAppBean getAppByAppname(String appname){
		SsApp ssApp = appDao.findOne(appname);
		SsAppBean bean = new SsAppBean();
		bean.setAppname(ssApp.getAppname());
		bean.setDescription(ssApp.getDescription());
		bean.setType(ssApp.getType());
		bean.setVersion(ssApp.getVersion());
		bean.setSsServices(ssApp.getSsServices());
		return bean;
	}
	/**
	 * 根据appname获取产品
	 * @param appname
	 * @return
	 */
	public String findAppByAppname(String appname){
		SsApp ssApp = appDao.findOne(appname);
		//String appName = ssApp.getAppname();
		if(ssApp != null){
			return "false";
		}else{
			return "true";
		}
	}
}
