package com.hhh.dao.fenfa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsAppVer;

@Repository
public interface SsAppVerDao extends PagingAndSortingRepository<SsAppVer, String>, JpaSpecificationExecutor<SsAppVer>{
	public List<SsAppVer> findBySsAppAppname(String appname);
	public SsAppVer findByAppVersionAndSsAppAppname(String appversion,String appname);
}
