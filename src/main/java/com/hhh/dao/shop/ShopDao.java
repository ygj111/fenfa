package com.hhh.dao.shop;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.shop.SysShop;

@Repository
public interface ShopDao extends PagingAndSortingRepository<SysShop, String>, JpaSpecificationExecutor<SysShop>{
	
	 public SysShop findByShopname(String shopname);
	

}
