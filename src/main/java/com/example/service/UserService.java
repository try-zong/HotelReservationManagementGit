package com.example.service;

import java.util.List;


import com.example.entity.User;



public interface UserService {
	public List<User>findAll(); 	//获取所有用户信息
	public User getOne(String account);	//获得一个用户信息
	public int addUser(User user);	//注册用户
	//删除用户
	public int deleteUser(String account);
	//更新用户信息
	public int updateUser(User user);
	//根据账号密码获取用户信息
	public User findUserByAccountAndpwd(String account,String password);
}
