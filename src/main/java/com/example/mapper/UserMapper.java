package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Manager;
import com.example.entity.PageInfo;
import com.example.entity.User;


public interface UserMapper {
	public List<User>findAll(PageInfo page);	//获取所有用户信息
	public int selectUserCount();//获取用户数量
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
	public List<User> selectUserByScale(@Param("scale")String scale,@Param("offset")int offset,@Param("rows")int rows );	//根据权限搜索用户
	public int degradeUserScale(String account);	//降低用户权限
	public int upgradeUserScale(String account);	//升级用户权限
}
