package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.User;


@Mapper
public interface UserDao {
	
	@Select("select * from user")
	List<User> getAllUser();
	
	@Select("select * from user where user_id=#{userId}")
	User getUserByUserId(int userId);

	@Select("select * from user where user_name=#{userName} and password=#{password}")
	User getUserByNameAndPass(@Param("userName") String userName,@Param("password") String password);
	
	@Select("select * from user where user_name = #{userName}")
	User getUserByUserName(String userName);
	
	@Insert("insert into user(user_name,password,create_date) values(#{userName},#{password},#{createDate})")
	@Options(useGeneratedKeys=true,keyColumn="user_id" ,keyProperty="userId")
	void insertUser(User user);
	
	@Update("update user set user_name=#{userName},password=#{password} where user_id=#{userId}")
	void updateUser(User user);
	
	@Delete("delete from user where user_id = #{userId}")
	void deleteUser(int userId);
}
