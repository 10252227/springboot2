package com.hqyj.SpringBootDemo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@RestController
@RequestMapping("/account")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 127.0.0.1/account/allRole
	 */
	@RequestMapping("/allRole")
	public List<Role> getAllRole() {
		return roleService.getAllRole();
	}
	
	/**
	 *  127.0.0.1/account/role/1
	 */
	@RequestMapping("/role/{roleId}")
	public Role getRoleByroleId(@PathVariable int roleId) {
		return roleService.getRoleByroleId(roleId);
	}
	
	/**
	 *  127.0.0.1/account/addRole
	 */
	@PostMapping(value="/addRole",consumes="application/json")
	public Result<Role> insertRole(@RequestBody Role role) {
		return roleService.insertRole(role);
	}
	
	/**
	 * 127.0.0.1/account/updateRole
	 */
	@PutMapping(value="updateRole",consumes="application/x-www-form-urlencoded")
	public Result<Role> updateRole(Role role) {
		return roleService.updateRole(role);
	}
	
	/**
	 * 127.0.0.1/account/deleteRole/3
	 */
	@DeleteMapping("/deleteRole/{roleId}")
	public Result<Role> deleteRole(@PathVariable int roleId) {
		return roleService.deleteRole(roleId);
	}
}
