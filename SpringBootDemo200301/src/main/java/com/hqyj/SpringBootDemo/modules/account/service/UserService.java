package com.hqyj.SpringBootDemo.modules.account.service;

import com.hqyj.SpringBootDemo.modules.account.entity.User;

public interface UserService {
	
	User getUserByUserId(int userId);
}
