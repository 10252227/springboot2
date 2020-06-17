package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	@RequestMapping("/login")
	public String loginPage() {
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
}
