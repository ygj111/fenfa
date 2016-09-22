package com.hhh.service.fenfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsOrgSer;
import com.hhh.dao.fenfa.SsOrgSerDao;

@Component
@Transactional
public class SsOrgSerService {
	private final static Logger logger = LoggerFactory.getLogger(SsOrgSerService.class);
    
	@Autowired
	SsOrgSerDao ssOrgSerDao;
	
	/**
	 * 保存
	 * @param ssOrgSer
	 * 
	 */
	public void saveOrgSerService(SsOrgSer ssOrgSer) {
		ssOrgSerDao.save(ssOrgSer);
		
	}
	/**
	 * 查询内容
	 */
	@Transactional(readOnly=true)
	public SsOrgSer findServiceBycustomerid(String id) {
		
		SsOrgSer ssOrgSer = ssOrgSerDao.findOne(id);
		return ssOrgSer;
	}
}
