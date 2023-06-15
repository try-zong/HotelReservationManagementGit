package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.entity.User;
import com.example.mapper.UserMapper;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public User findUserByAccountAndpwd(String account, String password) {
		User user = userMapper.findUserByAccountAndpwd(account, password);
		return user;
	}
	
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User getOne(String account) {
		return userMapper.getOne(account);
	}

	@Override
	public int deleteUser(String account) {
		return  userMapper.deleteUser(account);
	}
	//实现注册
	@Override
	public int addUser(User user) {
		return userMapper.addUser(user);
	}



}
