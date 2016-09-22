package com.hhh.dao.ac;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hhh.dao.entity.ac.SysUser;

@Repository
public interface UserDao extends CrudRepository<SysUser,  String> {
	public SysUser findByUsername(String username);
}
