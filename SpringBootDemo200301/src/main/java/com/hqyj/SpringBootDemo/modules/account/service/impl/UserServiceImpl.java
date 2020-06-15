package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.account.dao.RoleDao;
import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.dao.UserRoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.entity.UserRole;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.utils.MD5Util;

import net.bytebuddy.asm.Advice.Return;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;

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
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null) {
			return new Result<User>(ResultStatus.FAILD.status,"user name is repeat");
		}
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		userDao.insertUser(user);
		return new Result<User>(ResultStatus.SUCCESS.status,"Insert SUCCESS",user);
	}
	
	@Override
	public Result<User> login(User user) {
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp == null || !userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
			return new Result<User>(ResultStatus.FAILD.status, "User name or password error.");
		}

		return new Result<User>(ResultStatus.SUCCESS.status, "Login success.", userTemp);
	}


	@Override
	public Result<User> updateUser(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null) {
			return new Result<User>(ResultStatus.FAILD.status, "User name is repeat.");
		}
		
		userDao.updateUser(user);
		userRoleDao.deleteRolesByUserId(user.getUserId());
		List<Role> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				userRoleDao.insertUserRole(user.getUserId(), role.getRoleId());
			}
		}
		
		return new Result<>(ResultStatus.SUCCESS.status,"Update SUCCESS",user);
	}

	@Override
	public Result<Object> deleteUser(int userId) {
		userDao.deleteUser(userId);
		userRoleDao.deleteRolesByUserId(userId);
		return new Result<Object>(ResultStatus.SUCCESS.status,"delete Success");
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<>(
				Optional.ofNullable(userDao.getUserBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
		
	}

	
	
}
