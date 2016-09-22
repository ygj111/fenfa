package com.hhh.dao.fenfa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsVersService;

@Repository
public interface SsVersServiceDao extends PagingAndSortingRepository<SsVersService, String>, JpaSpecificationExecutor<SsVersService> {
	public List<SsVersService> findByAppnameAndAppVersion(String appname,String appVersion);

	public List<SsVersService> findByAppnameAndRealId(String appname, String realId);
	
	public List<SsVersService> findByAppname(String appname);
	
	public List<SsVersService> findByRealId(String realId);
}
