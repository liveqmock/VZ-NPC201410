package com.weizhen.npc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fylaw.security.md5.MD5;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.User;

/**
 * 
 * @author y
 * 
 */
@Repository
public class UserDAO extends BaseEntityDaoSupport<User> {

	/**
	 * 登录服务
	 * @param userName 用户名
	 * @param password 密码
	 * @return 如果用户名和密码均正确的话,返回用户对象
	 */
	public User login(String userName, String password) {
		String hql = "from User where username = ? and password = ?";
		List<User> users =  this.find(hql, userName, MD5.encrypt(password.getBytes()));
		if (EntityUtils.notEmpty(users))
			return users.get(0);
		
		return null;
	}
}
