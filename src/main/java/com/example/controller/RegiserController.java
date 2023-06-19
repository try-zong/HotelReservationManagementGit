package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.User;
import com.example.service.UserService;



@Controller//定义控制器的注解
public class RegiserController {
	//注册用户账号
	
		@Autowired
		private UserService userService;
		@RequestMapping({"/add"})                 
		public	String initUser(Model model) {
			User usernew = new User();
			model.addAttribute("User", usernew);
			return "register";
		}
		
		@RequestMapping("/addUser")
		public	String addUser(@ModelAttribute User user,Model model) {
			userService.addUser(user);
			return "redirect:/login";
		}
}
