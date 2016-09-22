package com.hhh.dao.fund;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.fund.SysDict;

@Repository
public interface DictRepository extends PagingAndSortingRepository<SysDict, String>, JpaSpecificationExecutor<SysDict> {
	SysDict findByCode(String code);
}
