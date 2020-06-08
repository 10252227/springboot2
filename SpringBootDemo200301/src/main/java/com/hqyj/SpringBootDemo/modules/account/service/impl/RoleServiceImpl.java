package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.RoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

	@Override
	public Role getRoleByroleId(int roleId) {
		return roleDao.getRoleByroleId(roleId);
	}

	@Override
	public Result<Role> insertRole(Role role) {
		roleDao.insertRole(role);
		return new Result<Role>(ResultStatus.SUCCESS.status,"Insert success" ,role);
	}

	@Override
	public Result<Role> updateRole(Role role) {
		roleDao.updateRole(role);
		return new Result<Role>(ResultStatus.SUCCESS.status,"Update success",role);
	}

	@Override
	public Result<Role> deleteRole(int roleId) {
		roleDao.deleteRole(roleId);
		return new Result<Role>(ResultStatus.SUCCESS.status,"delete success");
	}
}
