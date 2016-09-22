package com.hhh.service.ac;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.dao.ac.UserDao;
import com.hhh.dao.entity.ac.SysUser;
import com.hhh.web.model.ac.User;

/**
 * 系统用户管理
 * @author mars.zhong
 *
 */
@Component
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * 新建用户
	 */
//	@Override
	@Transactional( propagation=Propagation.REQUIRED )
	public User createUser(User user) {
		EncryptHelper helper = new EncryptHelper();
		helper.entryptPassword(user);
		
		SysUser sysUser = new SysUser();
		sysUser.setUsername(user.getUserName());
		sysUser.setPassword(user.getPassword());
		sysUser.setSalt(user.getSalt());
		sysUser.setEmail(user.getEmail());
		sysUser.setPhone(user.getPhone());
		sysUser.setDisplayname(user.getDisplayName());
		sysUser.setCreatetime(new Date());
		
		userDao.save(sysUser);
		
		return user;
	}



//	@Override
	@Transactional(propagation =Propagation.NOT_SUPPORTED, readOnly=true)
	public User findByUsername(String userName) {
		SysUser sysUser = userDao.findByUsername(userName);
		
		if (sysUser != null) {
			User user = new User();
			
			user.setUserId(sysUser.getId());
			user.setUserName(sysUser.getUsername());
			user.setPassword(sysUser.getPassword());
			user.setSalt(sysUser.getSalt());
			user.setDisplayName(sysUser.getDisplayname());
			user.setEmail(sysUser.getEmail());
			user.setPhone(sysUser.getPhone());
			
			return user;
		}
		
		return null;
	}

}
