package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Manager;
import com.example.entity.PageInfo;

import com.example.entity.User;



public interface UserService {
	public List<User>findAll(PageInfo page); 	//获取所有用户信息
	public int selectUserCount();//获取用户数量
	public User getOne(String account);	//获得一个用户信息
	public int addUser(User user);	//注册用户
	//删除用户
	public int deleteUser(String account);
	//更新用户信息
	public int updateUser(User user);
	//根据账号密码获取用户信息
	public User findUserByAccountAndpwd(String account,String password);
    public int saveUser(User user,MultipartFile file); //修改用户信息
    public User getPwd(User user); //找回密码
	//管理员方法
	//根据账号密码获取用户信息
	public Manager findManagerByIdAndpwd(int id,String password);
	public int getManagerId(int id);	//获得一个用户信息
	public List<User> selectUserByScale(String scale,PageInfo page);	//根据权限搜索用户
	public int degradeUserScale(String account);	//降低用户权限
	public int degradeUserScaleSvip(String account);	//降低用户权限
	public int upgradeUserScaleSvip(String account);	//升级用户权限
	public int upgradeUserScale(String account);	//升级用户权限

}
