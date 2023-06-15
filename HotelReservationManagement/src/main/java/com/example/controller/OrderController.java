package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Order;
import com.example.entity.PageInfo;
import com.example.entity.Room;
import com.example.entity.User;
import com.example.service.OrderService;
import com.example.service.PageService;
import com.example.service.RoomService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller//定义控制器的注解
public class OrderController {
	@Autowired//自动装配的注解     
	private PageService pageService;
	@Autowired//自动装配的注解     
	private OrderService orderService;
	@Autowired//自动装配的注解     
	private RoomService roomService;
	//进行房间的预订，生成订单
	@RequestMapping("/reservation")
	public String reservation(Integer id,
			HttpSession session, Model model){
		//提取房间信息
		Room room = roomService.getOne(id);
		//生成订单
		Order order = new Order();
		order.setRoom(room);
		//order.setUser_account2(user.getAccount());
		//order.setRoom_id(roomId);
		order.setTypes(room.getTypes());
		order.setMoney(room.getPrice());
		model.addAttribute("order",order);
		return "reservation";
	}
	@RequestMapping("/toReservation")
	public String toReservation(@ModelAttribute("order") Order order,
			HttpSession session, Model model){
		//提取用户信息
		User user = (User)session.getAttribute("User");
		order.setUser(user);
		log.info("类型"+order.getTypes()+order.getUser().getAccount());
		orderService.addOrder(order);
		roomService.updateRoomStateById(order.getRoom().getId());
		return "redirect:/manageOrder";
	}
	
	//用户订单管理控制
	@RequestMapping("/manageOrder")
	public String toMain(Integer totalcount, Integer pageCur,
			HttpSession session, Model model){
		Order allOrder = new Order();
		User user = (User)session.getAttribute("User");
		PageInfo  page =pageService.set(totalcount, pageCur);
		List<Order> orderList = new ArrayList<Order>();
		orderList =orderService.selectOrderByUserAccountAndPage(user.getAccount(), page);
		totalcount =orderService.selectOrderCountByUserAccountAndPage(user.getAccount());
		log.info("totalcount="+ totalcount);
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("orderList",orderList);
		model.addAttribute("AllOrder", allOrder);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "manageOrder";
	}
	@RequestMapping("/findingOrder")
	public String tofind( Integer totalcount, Integer pageCur, 
			Model model,@ModelAttribute Order orderFid,HttpSession session) {
		User user = (User)session.getAttribute("User");
		int id = orderFid.getId();
		String types = orderFid.getTypes();
		List<Order> orderList = new ArrayList<Order>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Order ordernew = new Order();
		if(id != 0 && types != null) {
			orderList = Arrays.asList(orderService.getOne(id));
		}else if(types!=null) {
			orderList = orderService.selectOrderByUserAccountAndTypesAndPage(user.getAccount(), types, page);
		}else {
			orderList =orderService.selectOrderByUserAccountAndPage(user.getAccount(), page);
		}
		totalcount =orderList.size();
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("orderList",orderList);
		model.addAttribute("AllOrder", ordernew);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "/manageOrder";
	}
	//删除订单控制
	@RequestMapping("/deleteOrder")//将请求映射为下列方法的注解
	public String DeleteOrder(Integer id,Integer totalcount,Model model) {
		orderService.deleteOrderById(id);		
		model.addAttribute("totalcount",totalcount--);
		return "forward:manageOrder";
	}
}
