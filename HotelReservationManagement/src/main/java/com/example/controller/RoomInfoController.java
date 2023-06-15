package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.PageInfo;
import com.example.entity.Room;
import com.example.service.PageService;
import com.example.service.RoomService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller//定义控制器的注解
public class RoomInfoController {
	@Autowired//自动装配的注解     
	private RoomService roomService;
	@Autowired//自动装配的注解     
	private PageService pageService;
	
	@RequestMapping("/main")//将请求映射为下列方法的注解     
	public String toMain(Integer totalcount, Integer pageCur,
			HttpSession session, Model model){
		log.info("传PageCur="+ totalcount);
		PageInfo  page =pageService.set(totalcount, pageCur);
		totalcount =roomService.selectRoomCount();
		// 分页查询         
		List<Room> roomList =roomService.selectAllRoomByPage(page);
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;
		Room roomnew = new Room();
		log.info("totalpag="+String.valueOf(totalpage));
		log.info("PageCur="+String.valueOf(page.getPageCur()));
		model.addAttribute("Room", roomnew);		
		model.addAttribute("roomList",roomList);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "main"; 
		}
	
	@RequestMapping("/finding")
	public String tofind( Integer totalcount, Integer pageCur, Model model,@ModelAttribute Room roomFid) {
		int id = roomFid.getId();
		String types = roomFid.getTypes();
		List<Room> roomList = new ArrayList<Room>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Room roomnew = new Room();
		if(id != 0 && types != null) {
			roomList = Arrays.asList(roomService.getOne(id));
		}else if(types!=null) {
			roomList = roomService.selectRoomByTypes(types,page);
		}else {
			totalcount =roomService.selectRoomCount();
			// 分页查询 
			roomList =roomService.selectAllRoomByPage(page);
		}
		totalcount =roomList.size();
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("roomList",roomList);
		model.addAttribute("Room", roomnew);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "/main";
	}
}
