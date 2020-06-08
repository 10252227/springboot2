package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;

@RestController
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/{userId}")
	public User getUserByUserId(@PathVariable int userId) {
		return userService.getUserByUserId(userId);
		
	}
}
