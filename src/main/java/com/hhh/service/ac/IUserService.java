package com.hhh.service.ac;

import com.hhh.web.model.ac.User;

public interface IUserService {
	public User createUser(User user);
	
	public void changePassword(String userId, String newPassword);
	
	// 添加用户-角色关系 
	public void correlationRoles(String userId, String... roldIds);
	
	// 移除用户-角色关系 
	public void uncorrelationRoles(String userId, String ... roleIds);
	
	public User findByUsername(String userName);
	
}
