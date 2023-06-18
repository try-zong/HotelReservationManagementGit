package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Manager;
import com.example.entity.Room;
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
    public int saveUser(User user,MultipartFile file); //修改用户信息
	//管理员方法
	//根据账号密码获取用户信息
	public Manager findManagerByIdAndpwd(int id,String password);
	public int getManagerId(int id);	//获得一个用户信息

}
