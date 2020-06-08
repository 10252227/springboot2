package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface RoleService {

	List<Role> getAllRole();
	
	Role getRoleByroleId(int roleId);
	
	Result<Role> insertRole(Role role);
	
	Result<Role> updateRole(Role role);
	
	Result<Role> deleteRole(int roleId);
}
