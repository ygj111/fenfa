package com.hhh.service.fenfa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsClientInfo;
import com.hhh.dao.fenfa.SsClientDao;
import com.hhh.web.model.fenfa.SsClientInfoBean;

@Component
@Transactional
public class SsClientService {
	@Autowired
	private SsClientDao scd;
	
	/**
	 * 验证客户端程序是否可用
	 * @param bean
	 * @return
	 */
	public Integer validateClient(SsClientInfoBean bean){
		Integer status = null;
		if(!scd.exists(bean.getCode())){
			SsClientInfo sci = new SsClientInfo();
			sci.setCode(bean.getCode());
			sci.setDepname(bean.getDepname());
			sci.setDeviceInfo(bean.getDeviceInfo());
			sci.setSetupPath(bean.getSetupPath());
			sci.setMac(bean.getMac());
			sci.setAccessDate(new Date());
			sci.setStatus(0);//可用
			sci.setAppname(bean.getAppname());
			sci.setLimitDate(bean.getLimitDate());
			scd.save(sci);
			status = sci.getStatus();
		}else{
			SsClientInfo sci = scd.findOne(bean.getCode());
			scd.updateAccessDate(new Date(),bean.getCode());
			status = sci.getStatus();
			if(sci.getLimitDate()!=null){
				if(new Date().getTime()>sci.getLimitDate().getTime()){
					status = 1;
				}
			}
		}
		return status;
	}
}
