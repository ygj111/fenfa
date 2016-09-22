package com.hhh.dao.wf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.wf.WfProcess;

@Repository
public class WfProcessDaoImpl implements IWfProcessDao {
	
	private static final Logger logger = LoggerFactory.getLogger(WfProcessDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WfProcess> findAll() {
		
		logger.info("EntityManager Object is " +entityManager );
		Query query = entityManager.createQuery("SELECT w FROM WfProcess w where w.id=:id");
		query.setParameter("id", "8649004d73174c628f857f5cc44a02c4");
		return query.getResultList();
	}
}
