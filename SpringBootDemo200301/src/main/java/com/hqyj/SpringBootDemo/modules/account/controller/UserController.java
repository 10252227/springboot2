package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 127.0.0.1/api/users
	 */
	@PostMapping(value="/users",consumes="application/json")
	public PageInfo<User> getUserBySearchVo(@RequestBody SearchVo searchVo) {
		return userService.getUserBySearchVo(searchVo);
	}
	
	/**
	 * 127.0.0.1/api/user/1
	 */
	@RequestMapping("/user/{userId}")
	public User getUserByUserId(@PathVariable int userId) {
		return userService.getUserByUserId(userId);
		
	}
	
	/**
	 * 127.0.0.1/account/user?userName=张三&password=111
	 */
	@RequestMapping("/user")
	public User getUserByNameAndPass(@RequestParam String userName,@RequestParam String password) {
		return userService.getUserByNameAndPass(userName, password);
	}
	
	/**
	 * 127.0.0.1/account/user
	 */
	@PostMapping(value="/user",consumes="application/json")
	public Result<User> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	@PostMapping(value="/login",consumes="application/json")
	public Result<User> login(@RequestBody User user) {
		return userService.login(user);
	}
	
	/**
	 *127.0.0.1/account/updateUser
	 */
	@PutMapping(value="user",consumes="application/json")
	public Result<User> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	/**
	 * 127.0.0.1/account/user/4
	 */
	@DeleteMapping("/user/{userId}")
	public Result<Object> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
}
