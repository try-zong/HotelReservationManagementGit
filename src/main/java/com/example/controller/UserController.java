package com.example.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.PageInfo;
import com.example.entity.Room;
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
	//展示用户个人信息
	@RequestMapping("/userInfoShow")
	public String userInfoShow(Model model,HttpSession session){
		User user = (User)session.getAttribute("User");
		User userInfo = userService.getOne(user.getAccount());
	//	log.info("img"+userInfo.getImg());
		model.addAttribute("User",userInfo);
		return "userInfoShow"; 
	}
	//编辑用户信息
	@RequestMapping("/userMag")//将请求映射为下列方法的注解     
	public String userMag(Model model,HttpSession session){
		User user = (User)session.getAttribute("User");
		User userInfo = userService.getOne(user.getAccount());
		model.addAttribute("User",userInfo);
		return "userInfoMag"; 
	}
	
		@RequestMapping("/userInfoMag")
		public String userInfoMag(@ModelAttribute("User") User user,
				@RequestParam("file") MultipartFile file,
				HttpSession session, Model model){
			if(userService.saveUser(user, file)>0) {
				session.setAttribute("User",user);
				model.addAttribute("User", user);
				return "userInfoShow";
			}else {
				model.addAttribute("mistake", "修改失败");
				return "redirect:/main";
			}  
		
		}
		//找回密码
		@RequestMapping({"/BackUser"})
		public String BackUser( Model model) {
			User usernew = new User();
			model.addAttribute("User", usernew);
			return "backUser";
		}
		@RequestMapping("/toBackUser")
		public String toBackUser(@ModelAttribute("User") User user, Model model){
			if(user.getAccount()==null) {
				//log.info("totalcount=");
				//按返回按钮，返回登录页面
				return "/login";
			}
		//	log.info(user.getAccount());
			User userInfo = userService.getOne(user.getAccount());
			model.addAttribute("back","密码为"+userInfo.getPassword() );
				return "backUser";
		}  

		//管理员管理部分
		
		@RequestMapping("/magUser")
		public String toMain(Integer totalcount, Integer pageCur,
				HttpSession session, Model model){
			PageInfo  page =pageService.set(totalcount, pageCur);
			List<User> userList = new ArrayList<User>();
			userList =userService.findAll(page);
			totalcount =userService.selectUserCount();
		//	log.info("totalcount="+ totalcount);
			int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;	
			User user = new User();
			model.addAttribute("User",user);
			model.addAttribute("totalcount",totalcount);
			model.addAttribute("userList",userList);
			model.addAttribute("totalpage",totalpage); 
			model.addAttribute("pageCur",page.getPageCur());
			return "magUser";
		}
		//根据用户名筛选用户
		@RequestMapping("/selectUser")
		public String selectUser(Integer totalcount, Integer pageCur,
				HttpSession session, Model model,@ModelAttribute User user ){
			String account = user.getAccount();
			String scale = user.getScale();
			List<User> userList = new ArrayList<User>();
			User userNew = new User();
			PageInfo  page =pageService.set(totalcount, pageCur);
			if(account!= "" && scale != null) {
				userList = Arrays.asList(userService.getOne(account));
			}else if(scale!=null) {
				userList = userService.selectUserByScale(scale, page);
			}else {
				totalcount =userService.selectUserCount();
				// 分页查询 
				userList =userService.findAll(page);
			}
			totalcount =userList.size();
			int totalpage = totalcount %4==0?totalcount/4:totalcount/4+1;		
			model.addAttribute("User",userNew);
			model.addAttribute("totalcount",totalcount);
			model.addAttribute("userList",userList);
			model.addAttribute("totalpage",totalpage); 
			model.addAttribute("pageCur",page.getPageCur());
			return "magUser";
		}
		//管理用户权限
		@RequestMapping("/degradeUser")
		public String degradeUser(Integer totalcount, Integer pageCur,
				HttpSession session, Model model,String account){
			userService.degradeUserScale(account);
			PageInfo  page =pageService.set(totalcount, pageCur);
			List<User> userList = new ArrayList<User>();
			userList =userService.findAll(page);
			totalcount =userService.selectUserCount();
			int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;	
			User user = new User();
			model.addAttribute("User",user);
			model.addAttribute("totalcount",totalcount);
			model.addAttribute("userList",userList);
			model.addAttribute("totalpage",totalpage); 
			model.addAttribute("pageCur",page.getPageCur());
			return "magUser";
		}
		@RequestMapping("/degradeSvipUser")
		public String degradeSvipUser(Integer totalcount, Integer pageCur,
				HttpSession session, Model model,String account){
			userService.degradeUserScaleSvip(account);
			PageInfo  page =pageService.set(totalcount, pageCur);
			List<User> userList = new ArrayList<User>();
			userList =userService.findAll(page);
			totalcount =userService.selectUserCount();
			int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;	
			User user = new User();
			model.addAttribute("User",user);
			model.addAttribute("totalcount",totalcount);
			model.addAttribute("userList",userList);
			model.addAttribute("totalpage",totalpage); 
			model.addAttribute("pageCur",page.getPageCur());
			return "magUser";
		}
		@RequestMapping("/upgradeUser")
		public String upgradeUser(Integer totalcount, Integer pageCur,
				HttpSession session, Model model,String account){
			userService.upgradeUserScale(account);
			PageInfo  page =pageService.set(totalcount, pageCur);
			List<User> userList = new ArrayList<User>();
			userList =userService.findAll(page);
			totalcount =userService.selectUserCount();
			int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;	
			User user = new User();
			model.addAttribute("User",user);
			model.addAttribute("totalcount",totalcount);
			model.addAttribute("userList",userList);
			model.addAttribute("totalpage",totalpage); 
			model.addAttribute("pageCur",page.getPageCur());
			return "magUser";
		}
		@RequestMapping("/upgradeSvipUser")
		public String upgradeSvipUser(Integer totalcount, Integer pageCur,
				HttpSession session, Model model,String account){
			userService.upgradeUserScaleSvip(account);
			PageInfo  page =pageService.set(totalcount, pageCur);
			List<User> userList = new ArrayList<User>();
			userList =userService.findAll(page);
			totalcount =userService.selectUserCount();
			int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;	
			User user = new User();
			model.addAttribute("User",user);
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
