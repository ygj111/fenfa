package com.hhh.web.model.fenfa;

import java.util.HashMap;
import java.util.Map;

public class ConstantClassField {
	public static final Map<String,String> orderStatusMap = new HashMap<String,String>();
	public static final String ORDER_STATUS_MARKET_UNAUDITED = "0";//市场人员未审核
	public static final String ORDER_STATUS_ENFORCE_MANAGE_UNAUDITED = "1";//实施管理人员未审核
	public static final String ORDER_STATUS_ENFORCE_UNAUDITED = "2";//实施人员未审核
	public static final String ORDER_STATUS_ENFORCED = "3";//已实施
	static{
		orderStatusMap.put("0", "市场人员未审核");
		orderStatusMap.put("1", "实施管理人员未审核");
		orderStatusMap.put("2", "实施人员未审核");
		orderStatusMap.put("3", "已实施");
	}
}
