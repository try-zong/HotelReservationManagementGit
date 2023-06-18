package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.entity.Manager;
import com.example.entity.User;


public interface UserMapper {
	public List<User>findAll();	//获取所有用户信息
	public User getOne(String account);	//获得一个用户信息
	public int addUser(User user);	//添加用户
	public int deleteUser(String account);	//删除用户
	public int updateUser(User user);	//更新用户信息
	//根据账号密码获取用户信息
	public User findUserByAccountAndpwd(String account,String password);
	
	//管理员方法
	//根据账号密码获取用户信息
	public Manager findManagerByIdAndpwd(int id,String password);
	@Select("select id from manager where id=#{id}")
	public int getManagerId(int Id);	//获得一个用户信息
	public int saveUser(User user); //修改用户信息
}
