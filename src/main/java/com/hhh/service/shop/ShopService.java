package com.hhh.service.shop;

import java.math.BigDecimal;
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

import com.hhh.dao.entity.shop.SysShop;
import com.hhh.dao.shop.ShopDao;
import com.hhh.web.model.shop.ShopBean;

@Component
@Transactional
public class ShopService {
	private final static Logger logger = LoggerFactory.getLogger(ShopService.class);
	@Autowired
	private ShopDao shopDao;
	
	/**
	 * 分页商品数据
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public List<ShopBean> getAllDic(int pageNum, int pageSize) {
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		
		Page<SysShop> shops = shopDao.findAll(pageReq);
		
		List<SysShop> shopList = shops.getContent();
		List<ShopBean> list = new ArrayList<ShopBean>();
		ShopBean shopBean = null;
		SysShop shop = null;
		
		for (int i =0; i < shopList.size(); i++) {
			shop = shopList.get(i);
			shopBean = new ShopBean();
			
			shopBean.setId(shop.getId());
			shopBean.setDiscount(shop.getDiscount());
			shopBean.setPrice(shop.getPrice());
			shopBean.setShopname(shop.getShopname());
			shopBean.setRemark(shop.getRemark());
			
			list.add(shopBean);
		}
		
		return list;
	}
	
	/**
	 * 获取商品信息分页总数
	 * @param pageSize 页面显示记录数
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public int getShopPageCount(int pageSize) {
		logger.info("count=" + shopDao.count() );
		return (int)Math.ceil( (double)shopDao.count() / pageSize);
	}
	
	
	
	
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public ShopBean findByName(String shopname) {
		SysShop sysShop = shopDao.findByShopname(shopname);
		
		if (sysShop != null) {
			/**
			 * 实体对象转换
			 * @param shopBean 页面实体Bean
			 * @return 数据实体
			 */
			ShopBean shopBean = new ShopBean();
			
			/*user.setUserId(sysUser.getId());
			user.setUserName(sysUser.getUsername());
			user.setPassword(sysUser.getPassword());
			user.setSalt(sysUser.getSalt());
			user.setDisplayName(sysUser.getDisplayname());
			user.setEmail(sysUser.getEmail());
			user.setPhone(sysUser.getPhone());*/
			shopBean.setId(sysShop.getId());
			shopBean.setDiscount(sysShop.getDiscount());
			shopBean.setPrice(sysShop.getPrice());
			shopBean.setShopname(sysShop.getShopname());
			shopBean.setRemark(sysShop.getRemark());
			//shopBean.setId(UUID.randomUUID().toString());
			return shopBean;
		}
		
		return null;
	}
	
	/**
	 * 新增/修改列表数据
	 * @param shop 数据对象
	 */
	@Transactional( propagation=Propagation.REQUIRED )
	public void saveAndUpdateDict(ShopBean shopBean) {
		SysShop shop = toSysShop(shopBean);
		shopDao.save(shop);
	}

	/**
	 * 实体对象转换
	 * @param shopBean 页面实体Bean
	 * @return 数据实体
	 */
	private SysShop toSysShop(ShopBean shopBean) {
		
		SysShop shop = new SysShop();
		
		/*dict.setCode(dictBean.getCode());
		dict.setName(dictBean.getName());
		dict.setStatus(dictBean.getStatus());
		dict.setParent(dictBean.getParent());
		dict.setCategory(dictBean.getCategory());*/
		
		//shop.setId(UUID.randomUUID().toString());
		shop.setId(shopBean.getId());
		shop.setDiscount((BigDecimal) shopBean.getDiscount());
		shop.setPrice((BigDecimal) shopBean.getPrice());
		shop.setShopname(shopBean.getShopname());;
		shop.setRemark(shopBean.getRemark());
		return shop;
	}
	@Transactional( propagation=Propagation.REQUIRED )
	public void deleteShop(ShopBean shopBean) {
		SysShop shop = toSysShop(shopBean);
		shopDao.delete(shop);
		
	}
}
