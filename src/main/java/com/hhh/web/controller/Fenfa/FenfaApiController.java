package com.hhh.web.controller.Fenfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.service.fenfa.SsClientService;
import com.hhh.web.model.fenfa.SsClientInfoBean;

@Controller
@RequestMapping("/api")
public class FenfaApiController {
	private final static Logger logger = LoggerFactory.getLogger(FenfaApiController.class);
	@Autowired
	private SsClientService scs;
	
	/**
	 * 查询客户端是否可用
	 * @param bean
	 * @return 0:可用,1:不可用
	 */
	@RequestMapping(value="validateClient",method=RequestMethod.POST)
	public @ResponseBody Integer validateClient(SsClientInfoBean bean){
		try {
			if(bean.getCode()==null) throw new NullPointerException("code is null!");
			if(bean.getSetupPath()==null) throw new NullPointerException("setupPath is null!");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		Integer status = scs.validateClient(bean);
		logger.info("validateClient--传入参数code:"+bean.getCode()+",depname:"+bean.getDepname()+",deviceInfo:"+bean.getDeviceInfo()
		+",setupPath:"+bean.getSetupPath()+",mac:"+bean.getMac()+"----返回值status:"+status);
		return status;
	}
	
}
