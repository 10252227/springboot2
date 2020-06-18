package com.hqyj.SpringBootDemo.modules.account.controller;

import org.apache.shiro.authc.LogoutAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.SpringBootDemo.modules.account.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}
	
	@RequestMapping("/logout")
	public String LogOut(ModelMap modelMap){
		userService.logout();
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";
	}

	@RequestMapping("/register")
	public String register() {
		return "indexSimple";
	}

	/**
	 * 127.0.0.1/account/users
	 * 
	 * @return
	 */
	@RequestMapping("/users")
	public String userPage() {
		return "index";
	}

	/**
	 * http://127.0.0.1/account/roles
	 */
	@RequestMapping("/roles")
	public String rolesPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/resource
	 */
	@RequestMapping("/resource")
	public String resourcePage(){
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/resource
	 */
	@RequestMapping("/profile")
	public String profilePage(){
		return "index";
	}
}
