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

import com.example.entity.Hot;
import com.example.entity.PageInfo;
import com.example.entity.Room;
import com.example.service.PageService;
import com.example.service.RoomService;
import com.example.service.ShowService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller//定义控制器的注解
public class RoomInfoController {
	@Autowired//自动装配的注解     
	private RoomService roomService;
	@Autowired//自动装配的注解     
	private PageService pageService;
	@Autowired//自动装配的注解     
	private ShowService showService;
	
	@RequestMapping("/main")//将请求映射为下列方法的注解     
	public String toMain(Integer totalcount, Integer pageCur,
			 Model model){
		PageInfo  page =pageService.set(totalcount, pageCur);
		totalcount =roomService.selectRoomCount();
		// 分页查询         
		List<Room> roomList =roomService.selectAllRoomByPage(page);
		int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;
		Room roomnew = new Room();
	//	log.info("totalpag="+String.valueOf(totalpage));
	//	log.info("PageCur="+String.valueOf(page.getPageCur()));
		List<Hot> topThree = new ArrayList<Hot>();
		topThree = showService.selectTopThreeRoom();
		model.addAttribute("topThree",topThree);
		model.addAttribute("Room", roomnew);		
		model.addAttribute("roomList",roomList);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "main"; 
		}
	
	@RequestMapping("/finding")
	public String tofind( Integer totalcount, Integer pageCur, Model model,
			@ModelAttribute Room roomFid) {
		Integer id = roomFid.getId();
		String types = roomFid.getTypes();
		List<Room> roomList = new ArrayList<Room>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Room roomnew = new Room();
		if(id != null) {
			roomList = Arrays.asList(roomService.getOne(id));
			totalcount =roomList.size();
		}else if(types!="") {
			roomList = roomService.selectRoomByTypes(types,page);
			totalcount =roomList.size();
		}else {
			totalcount =roomService.selectRoomCount();
			// 分页查询 
			roomList =roomService.selectAllRoomByPage(page);
		}
		
		int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;
		List<Hot> topThree = new ArrayList<Hot>();
		topThree = showService.selectTopThreeRoom();
		model.addAttribute("topThree",topThree);
		model.addAttribute("roomList",roomList);
		model.addAttribute("Room", roomnew);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "/main";
	}
	
	
	
	//管理员管理部分
	//显示所有房间
	@RequestMapping("/magRoom")//将请求映射为下列方法的注解     
	public String toMagRoom(Integer totalcount, Integer pageCur,
			 Model model){
		PageInfo  page =pageService.set(totalcount, pageCur);
		totalcount =roomService.selectRoomCount();
		// 分页查询         
		List<Room> roomList =roomService.selectAllRoomByPage(page);
		int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;
		Room roomnew = new Room();
		model.addAttribute("Room", roomnew);		
		model.addAttribute("roomList",roomList);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "magRoom"; 
		}
	//筛选房间
	@RequestMapping("/selecting")
	public String toSelect( Integer totalcount, Integer pageCur, Model model,
			@ModelAttribute Room roomFid) {
		Integer id = roomFid.getId();
		String types = roomFid.getTypes();
		List<Room> roomList = new ArrayList<Room>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Room roomnew = new Room();
		if(id !=null ) {
			//log.info("1z");
			roomList = Arrays.asList(roomService.getOne(id));
			totalcount =roomList.size();
		}else if(types!="") {
			//log.info("2z");
			roomList = roomService.selectRoomByTypes(types,page);
			totalcount =roomList.size();
		}else {
			totalcount =roomService.selectRoomCount();
			// 分页查询 
			roomList =roomService.selectAllRoomByPage(page);
			//log.info("z");
			
		}
		
		int totalpage = totalcount % 4==0?totalcount/4:totalcount/4+1;	
		model.addAttribute("roomList",roomList);
		model.addAttribute("Room", roomnew);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "/magRoom";
	}
	//编辑房间信息
	@RequestMapping("/editRoomInfo")//将请求映射为下列方法的注解     
	public String editRoom(Integer id,Model model,HttpSession session){
		//提取房间信息
		Room room = roomService.getOne(id);
		//session.setAttribute("room", room);
		model.addAttribute("Room", room);
		return "editRoomInfo"; 
		}
	@RequestMapping("/toEditRoomInfo")
	public String toEditRoomInfo(@ModelAttribute("Room") Room room,
			@RequestParam("file") MultipartFile file,
			HttpSession session, Model model){
		//log.info("价格"+String.valueOf(room.getPrice()));
		if(roomService.saveEdit(room, file))
	//	log.info("该方法执行了");
	//	log.info("房间号"+String.valueOf(room.getId()));
        //model.addAttribute("user", user);
        return "redirect:/magRoom";
		return "redirect:/magRoom";
        
	}
	//删除房间
	@RequestMapping("/deleteRoom")     
	public String deleteRoom(Integer id,Integer totalcount,Model model){
	//	log.info("1"+String.valueOf(totalcount));
		if(roomService.deleteRoom(id)>0) {
	//		log.info("2"+String.valueOf(totalcount));
			model.addAttribute("totalcount",totalcount--);
			return "forward:magRoom"; 
		}else {
			return "redirect:/magRoom";
		}
	}
	//添加房间信息
	@RequestMapping("/addRoomInfo")//将请求映射为下列方法的注解     
	public String addRoomInfo(Integer id,Model model,HttpSession session){
		//提取房间信息
		Room room = new Room();
		model.addAttribute("Room", room);
		return "magAddRoom"; 
		}
	@RequestMapping("/toAddRoom")
	public String toAddRoom(@ModelAttribute("Room") Room room,
			@RequestParam("file") MultipartFile file,
			HttpSession session, Model model){
	//	log.info("file"+String.valueOf(file));
		if(roomService.addRoom(room, file)>0) {
			//model.addAttribute("user", user);
			return "redirect:/magRoom";
		}else {
			model.addAttribute("mistake", "添加失败");
			return "redirect:/magRoom";
		}
	}
}