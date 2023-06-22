package com.example.service;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Constants;
import com.example.entity.Manager;
import com.example.entity.PageInfo;
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
	public int selectUserCount() {
		// TODO Auto-generated method stub
		return userMapper.selectUserCount();
	}

	@Override
	public User findUserByAccountAndpwd(String account, String password) {
		User user = userMapper.findUserByAccountAndpwd(account, password);
		return user;
	}
	
	@Override
	public List<User> findAll(PageInfo page) {
		return userMapper.findAll(page);
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

	@Override
	public int  saveUser(User user, MultipartFile file) {
		// TODO Auto-generated method stub
		if (!file.isEmpty()){
            // 原始文件名
            String originalFileName = file.getOriginalFilename(); 
            // 获取图片后缀
            String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")); 
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            // 图片存储路径
            String filePath = Constants.IMG_PATH + fileName;
            File saveFile = new File(filePath);
            try {
                // 将上传的文件保存到服务器文件系统
                file.transferTo(saveFile);
                // 记录服务器文件系统图片名称
                user.setImg("\\img\\"+fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return userMapper.saveUser(user);
	}

	//管理员方法
	//根据账号密码获取用户信息


	@Override
	public Manager findManagerByIdAndpwd(int id, String password) {
		// TODO Auto-generated method stub
		return userMapper.findManagerByIdAndpwd(id, password);
	}

	@Override
	public int getManagerId(int id) {
		// TODO Auto-generated method stub
		return userMapper.getManagerId(id);
	}

	@Override
	public List<User> selectUserByScale(String scale, PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
		int offset = page.getOffset();
		return userMapper.selectUserByScale(scale, offset, rows);
	}

	@Override
	public int degradeUserScale(String account) {
		// TODO Auto-generated method stub
		return userMapper.degradeUserScale(account);
	}

	@Override
	public int upgradeUserScale(String account) {
		// TODO Auto-generated method stub
		return userMapper.upgradeUserScale(account);
	}
	
}
