package com.hhh.dao.fenfa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fenfa.SsOrgSer;

@Repository
public interface SsOrgSerDao extends PagingAndSortingRepository<SsOrgSer, String>, JpaSpecificationExecutor<SsOrgSer>{

}
