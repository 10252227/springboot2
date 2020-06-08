package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;

@Mapper
public interface RoleDao {
	
	@Select("select * from role")
	List<Role> getAllRole();
	
	@Select("select * from role where role_id = #{roleId}")
	Role getRoleByroleId(int roleId);
	
	@Insert("insert into role(role_name) values(#{roleName})")
	@Options(useGeneratedKeys=true,keyColumn="role_id",keyProperty="roleId")
	void insertRole(Role role);
	
	@Update("update role set role_name = #{roleName}")
	void updateRole(Role role);
	
	@Delete("delete from role where role_id = #{roleId}")
	void deleteRole(int roleId);
}	
