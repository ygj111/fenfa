package com.hhh.dao.wf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.wf.WfProcess;

@Repository
public interface WfProcessRepository extends JpaRepository<WfProcess, Long> {
	public List<WfProcess> findAll();
}
