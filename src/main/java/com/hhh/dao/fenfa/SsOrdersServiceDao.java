package com.hhh.dao.fenfa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsOrdersService;

@Repository
public interface SsOrdersServiceDao extends PagingAndSortingRepository<SsOrdersService, String>, JpaSpecificationExecutor<SsOrdersService> {

	public List<SsOrdersService> findBySsOrderId(String orderid);

	
}

