package com.hqyj.SpringBootDemo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@RestController
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 127.0.0.1/account/allUser
	 */
	@RequestMapping("allUser")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	/**
	 * 127.0.0.1/account/user/1
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
	 * 127.0.0.1/account/addUser
	 */
	@PostMapping(value="/addUser",consumes="application/json")
	public Result<User> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	/**
	 *127.0.0.1/account/updateUser
	 */
	@PutMapping(value="updateUser",consumes="application/x-www-form-urlencoded")
	public Result<User> updateUser(User user) {
		return userService.updateUser(user);
	}
	
	/**
	 * 127.0.0.1/account/deleteUser/4
	 */
	@DeleteMapping("/deleteUser/{userId}")
	public Result<User> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
}
