package com.hhh.dao.fenfa;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.entity.fenfa.SsClientInfo;

@Repository
public interface SsClientDao extends CrudRepository<SsClientInfo, String> {
	@Transactional
	@Modifying
	@Query("update SsClientInfo sci set sci.accessDate=:accessDate where sci.code=:code")
	public void updateAccessDate(@Param("accessDate")Date accessDate,@Param("code")String code);
}
