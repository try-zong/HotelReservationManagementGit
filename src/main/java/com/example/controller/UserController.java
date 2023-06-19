package com.example.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.PageInfo;
import com.example.entity.User;
import com.example.service.PageService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller//定义控制器的注解
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired//自动装配的注解     
	private PageService pageService;
	
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
				return "redirect:/main";
			}else {
				model.addAttribute("mistake", "修改失败");
				return "redirect:/main";
			}        
		}

		
		//管理员管理部分
		
		@RequestMapping("/magUser")
		public String toMain(Integer totalcount, Integer pageCur,
				HttpSession session, Model model){
			PageInfo  page =pageService.set(totalcount, pageCur);
			List<User> userList = new ArrayList<User>();
			userList =userService.findAll(page);
			totalcount =userService.selectUserCount();
			log.info("totalcount="+ totalcount);
			int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
			model.addAttribute("totalcount",totalcount);
			model.addAttribute("userList",userList);
			model.addAttribute("totalpage",totalpage);
			model.addAttribute("pageCur",page.getPageCur());
			return "magUser";
		}
		//删除订单控制
		@RequestMapping("/deleteUser")//将请求映射为下列方法的注解
		public String DeleteUser(String account,Integer totalcount,Model model) {
			userService.deleteUser(account);	
			model.addAttribute("totalcount",totalcount--);
			return "forward:magUser";
		}
}
