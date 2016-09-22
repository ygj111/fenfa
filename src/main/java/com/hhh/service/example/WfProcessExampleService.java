package com.hhh.service.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhh.dao.entity.wf.WfProcess;
import com.hhh.dao.wf.IWfProcessDao;
import com.hhh.rest.bean.example.WfProcessBean;

@Component
public class WfProcessExampleService {

	@Autowired
	private IWfProcessDao dao;
	
	public WfProcessBean getWfProcess() {
		WfProcessBean wfBean = new WfProcessBean();
		wfBean.setId("1");
		wfBean.setDisplayName("testFlow");
		wfBean.setName("test");
		wfBean.setVersion(4);
		wfBean.setCreateTime("2015-04-01");
		
		return wfBean;
	}
	
	public List<WfProcessBean> getAllWfProcess() {
		List<WfProcess> wfs = dao.findAll();
		
		List<WfProcessBean> beanList = new ArrayList<WfProcessBean>();
		WfProcessBean wfBean = null;
		WfProcess wf = null;
		
		for (int i = 0; i < wfs.size(); i++) {
			wfBean = new WfProcessBean();
			
			wf = wfs.get(i);
			wfBean.setId(wf.getId());
			wfBean.setDisplayName(wf.getDisplayName());
			wfBean.setName(wf.getName());
			wfBean.setVersion(wf.getVersion());
			wfBean.setCreateTime(wf.getCreateTime());
			
			beanList.add(wfBean);
		}
		
		return beanList;
	}
}
