package com.hhh.rest.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.rest.bean.example.WfProcessBean;
import com.hhh.service.example.WfProcessExampleService;

@Controller
@RequestMapping("/exp")
public class ExampleRestService {
	@Autowired
	private WfProcessExampleService service;
	
	@RequestMapping(value="/wf", method = RequestMethod.GET)
	public @ResponseBody List<WfProcessBean> getAllWfProcess() {
		List<WfProcessBean> wfBean = service.getAllWfProcess();
		
		return wfBean;
	}
}
