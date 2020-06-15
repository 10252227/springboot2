package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;

public interface UserService {
	
	List<User> getAllUser();
	
	User getUserByUserId(int userId);
	
	User getUserByNameAndPass(String userName,String password);
	
	PageInfo<User> getUserBySearchVo(SearchVo searchVo);
	
	Result<User> insertUser(User user);
	
	Result<User> updateUser(User user);
	
	Result<Object> deleteUser(int userId);
	
	User getUserByUserName(String userName);
	
	Result<User> login(User user);
}
