package com.example.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.example.entity.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller//定义控制器的注解
public class UserController {
	@Autowired
	private UserService userService;
	
	//编辑用户信息
	@RequestMapping("/userMag")//将请求映射为下列方法的注解     
	public String userMag(Model model,HttpSession session){
		User user = (User)session.getAttribute("User");
		User userInfo = userService.getOne(user.getAccount());
		model.addAttribute("User",userInfo);
		return "userInfoMag"; 
	}
		@RequestMapping("/userInfoMag")
		public String toEditRoomInfo(@ModelAttribute("User") User user,
				@RequestParam("file") MultipartFile file,
				HttpSession session, Model model){
			if(userService.saveUser(user, file)>0) {
				session.setAttribute("User",user);
				model.addAttribute("User", user);
				log.info("zhi");
				return "redirect:/main";
			}else {
				model.addAttribute("mistake", "修改失败");
				return "redirect:/main";
			}        
		}

}
