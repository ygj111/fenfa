package com.hhh.rest.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class SpController {
	
	@RequestMapping(value = "shop_list", method = RequestMethod.GET)
	public String shopList(Model model) {
		return "shop/shop_list";
	}
}
