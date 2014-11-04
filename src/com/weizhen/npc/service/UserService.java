package com.weizhen.npc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User login(String userName, String password) {
		return userDao.login(userName, password);
	}
}
