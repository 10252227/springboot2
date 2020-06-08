package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface UserService {
	
	List<User> getAllUser();
	
	User getUserByUserId(int userId);
	
	User getUserByNameAndPass(String userName,String password);
	
	Result<User> insertUser(User user);
	
	Result<User> updateUser(User user);
	
	Result<User> deleteUser(int userId);
}
