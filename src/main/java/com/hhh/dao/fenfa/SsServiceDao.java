package com.hhh.dao.fenfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsService;

@Repository
public interface SsServiceDao extends PagingAndSortingRepository<SsService, String>, JpaSpecificationExecutor<SsService>{

	/*@Query("select s from SsService s where s.ssApp.appname=?")*/
	SsService findByssApp(String appname);

	Page<SsService> findBySsAppAppname(String appname, Pageable pageable);
	
	public List<SsService> findBySsAppAppname(String appname);
	
	public SsService findByRealId(String realId);
	
}
