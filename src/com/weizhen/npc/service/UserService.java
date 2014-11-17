package com.weizhen.npc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fylaw.security.md5.MD5;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.UserDAO;
import com.weizhen.npc.model.User;

/**
 * 用户相关服务
 * 
 * @author y
 * 
 */
@Service
public class UserService extends BaseService {

	@Autowired
	private UserDAO userDao;

	/**
	 * 登录服务
	 * @param userName 用户名
	 * @param password 密码
	 * @return 如果用户名和密码均正确的话,返回用户对象
	 */
	public User login(String userName, String password) {
		String hql = "from User where userName = ? and password = ? and enabled = ?";
		List<User> users =  userDao.find(hql, userName, MD5.encrypt(password.getBytes()), true);
		if (EntityUtils.notEmpty(users)) {
			User user = users.get(0);
			user.setLastLoginTime(new Date());
			
			return userDao.saveOrUpdate(user);
		}
		
		return null;
	}
	
	public List<User> loadAll() {
		return userDao.loadAll();
	}
	
	public User get(Integer userId) {
		return userDao.get(userId);
	}
	
	public User addUser(User user) throws Exception {
		user.setUserId(null);
		
		List<User> matchedUsers = userDao.find("from User where userName = ?", user.getUserName());
		if (EntityUtils.notEmpty(matchedUsers))
			throw new Exception("用户名已存在");
		
		user.setPassword(MD5.encrypt(user.getPassword().getBytes()));
		
		return userDao.saveOrUpdate(user);
	}
	
	public User updateUser(User user) throws Exception {
		User originalUser = userDao.load(user.getUserId());
		if (null == originalUser)
			throw new Exception("用户不存在");
		
		if (null != user.getEnabled()) {
			originalUser.setEnabled(user.getEnabled());
		}
		if (null != user.getMobile()) { 
			originalUser.setMobile(user.getMobile());
		}
		if (null != user.getPassword()) {
			originalUser.setPassword(user.getPassword());
		}
		if (null != user.getRealName()) {
			originalUser.setRealName(user.getRealName());
		}
		if (null != user.getUserType()) {
			originalUser.setUserType(user.getUserType());
		}
		originalUser.setLastUpdateDate(new Date());
		
		return userDao.update(originalUser);
	}
}
