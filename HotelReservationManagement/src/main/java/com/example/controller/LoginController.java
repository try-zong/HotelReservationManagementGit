package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.User;
import com.example.service.MenuService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@RequestMapping({"/login","/"})
	public String toLogin( Model model) {
		User usernew = new User();
		model.addAttribute("User", usernew);
		return "login";
	}
	@RequestMapping("/toLogin")
	public String login(@ModelAttribute User userLog,HttpSession session, Model model) {
		String accountLog = userLog.getAccount();
		String passwordLog  = userLog.getPassword();
		User user = userService.findUserByAccountAndpwd(accountLog, passwordLog);
		log.info("密码"+passwordLog);
		log.info("菜单"+String.valueOf(menuService.selectMenuByParentId(0)));
		if(user != null) {
			//登录的用户信息存入session并跳转主页面
			session.setAttribute("User",user);
			//生成树形菜单并存入session
			
			session.setAttribute("Menu",menuService.selectMenuByParentId(0));
			log.info(String.valueOf(menuService.selectMenuByParentId(0)));
			return "redirect:/main";
		}else {
			//登录失败，跳回登录页面
			model.addAttribute("User",userLog);
			return "login";
		}
	}
}
