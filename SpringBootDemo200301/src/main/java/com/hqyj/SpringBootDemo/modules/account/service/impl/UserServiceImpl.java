package com.hqyj.SpringBootDemo.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}
}
