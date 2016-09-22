package com.hhh.dao.fenfa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsApp;

@Repository
public interface SsAppDao extends PagingAndSortingRepository<SsApp, String>, JpaSpecificationExecutor<SsApp>{

	SsApp findByappname(String appname);
	Page<SsApp> findByAppname(String appname,Pageable pageable);

}
