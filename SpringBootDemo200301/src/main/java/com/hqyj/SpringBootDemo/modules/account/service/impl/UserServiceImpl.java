package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	
	@Override
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public User getUserByNameAndPass(String userName, String password) {
		return userDao.getUserByNameAndPass(userName, password);
	}

	@Override
	public Result<User> insertUser(User user) {
		userDao.insertUser(user);
		return new Result<User>(ResultStatus.SUCCESS.status,"Insert SUCCESS",user);
	}

	@Override
	public Result<User> updateUser(User user) {
		userDao.updateUser(user);
		return new Result<>(ResultStatus.SUCCESS.status,"Update SUCCESS",user);
	}

	@Override
	public Result<User> deleteUser(int userId) {
		userDao.deleteUser(userId);
		return new Result<>(ResultStatus.SUCCESS.status,"delete Success");
	}

	
}
