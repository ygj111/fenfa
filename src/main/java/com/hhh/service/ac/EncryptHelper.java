package com.hhh.service.ac;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hhh.web.model.ac.User;

/**
 * 密码加密工具类
 * @author mars.zhong
 *
 */
public class EncryptHelper {
	public static final String HASH_ALGORITHM = "md5";
	public static final int HASH_INTERATIONS = 2;
	
	/**
	 * 密码加密
	 * @param user
	 */
	public void entryptPassword(User user) {
		RandomNumberGenerator randomNumberGenerator =  
			     new SecureRandomNumberGenerator(); 
		user.setSalt(randomNumberGenerator.nextBytes().toHex()); 
		
		String newPassword = new SimpleHash(  
				HASH_ALGORITHM,  
                user.getPassword(),  
                ByteSource.Util.bytes(user.getSalt()),  
                HASH_INTERATIONS).toHex();
		user.setPassword(newPassword);
	}
}
