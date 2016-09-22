package com.hhh.rest.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.service.shop.ShopService;
import com.hhh.web.model.shop.ShopBean;

@Controller
public class ShopController {
  
	
	
	@Autowired
	private ShopService shopService;
	
	private static final String PAGE_SIZE = "10";
	
	@RequestMapping(value="/listShop", method = RequestMethod.GET)
	public @ResponseBody List<ShopBean> getShopWithPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
														@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {
		return shopService.getAllDic(pageNum, pageSize);
	}
	
	@RequestMapping(value="/getShopPageCount", method = RequestMethod.GET)
	public @ResponseBody int getShopPageCount(@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize) {
		return shopService.getShopPageCount(pageSize);
	}
	
	
	/**
	 * 保存商品
	 * @param dictBean
	 * @return 1 success
	 */
	@RequestMapping(value="/saveShop", method = RequestMethod.POST)
	public @ResponseBody int saveShop(@RequestBody ShopBean shopBean) {
		shopService.saveAndUpdateDict(shopBean);
		return 1;
	}
	
	/**
	 * 删除
	 * @param dictBean
	 * @return 1 success
	 */
	@RequestMapping(value="/deleteShop", method = RequestMethod.POST)
	public @ResponseBody int deleteShop(@RequestBody ShopBean shopBean) {
		shopService.deleteShop(shopBean);
		return 1;
	}
}
