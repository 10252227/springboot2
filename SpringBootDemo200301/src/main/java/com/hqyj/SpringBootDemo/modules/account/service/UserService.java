package com.hqyj.SpringBootDemo.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;

public interface UserService {

	User getUserByUserId(int userId);

	PageInfo<User> getUserBySearchVo(SearchVo searchVo);

	Result<Object> deleteUser(int userId);

	User getUserByUserName(String userName);

	Result<User> login(User user);

	Result<User> editUser(User user);
	
	void logout();
}
