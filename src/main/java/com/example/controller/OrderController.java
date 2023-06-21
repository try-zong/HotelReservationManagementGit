package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Hot;
import com.example.entity.Order;
import com.example.entity.PageInfo;
import com.example.entity.Room;
import com.example.entity.User;
import com.example.service.OrderService;
import com.example.service.PageService;
import com.example.service.RoomService;
import com.example.service.ShowService;

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
		order.setTypes(room.getTypes());
		order.setMoney(room.getPrice());
		session.setAttribute("room", room);
		model.addAttribute("order",order);
		return "reservation";
	}
	@RequestMapping("/toReservation")
	public String toReservation(@ModelAttribute("order") Order order,
			HttpSession session, Model model){
	    //log.info(String.valueOf(order.getRoom().getId())+"前"+String.valueOf(order.getRoom().getVersion()));
		//提取用户信息
		User user = (User)session.getAttribute("User");
		order.setUser(user);
		//提取房间信息
		Room room = (Room)session.getAttribute("room");
		//乐观锁，判断是否有人提前预订
		int i = roomService.updateRoomStateById(room);
		if(i == 0) {
			return "redirect:/main";
		}else {
			//预订成功，返回订单管理页面
			orderService.addOrder(order);
			return "redirect:/manageOrder";
		}
		
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
		//log.info("totalcount="+ totalcount);		
	    //List<Hot> topThree = new ArrayList<Hot>();			
		//topThree = showService.selectTopThreeRoom();
	    //model.addAttribute("topThree",topThree);		
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
		Integer id = orderFid.getId();
		String types = orderFid.getTypes();
		List<Order> orderList = new ArrayList<Order>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Order ordernew = new Order();
		if(id != null && types != null) {
			orderList = Arrays.asList(orderService.getOne(id));
		}else if(types!= null) {
			orderList = orderService.selectOrderByUserAccountAndTypesAndPage(user.getAccount(), types, page);
		}else {
			orderList =orderService.selectOrderByUserAccountAndPage(user.getAccount(), page);
		}
		totalcount=orderList.size();
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
		//roomService.updateRoomStateTureById(orderService.selectRoomIdById(id));
		orderService.deleteOrderById(id);	
		model.addAttribute("totalcount",totalcount--);
		return "forward:manageOrder";
	}
	//修改订单状态
	@RequestMapping("/updateOrderState")//将请求映射为下列方法的注解
	public String updateOrderState(Integer id,Integer totalcount,Model model) {
		orderService.updateOrderStateById(id);
		roomService.updateRoomStateTureById(orderService.selectRoomIdById(id));
		model.addAttribute("totalcount",totalcount);
		return "forward:manageOrder";
	}
	//回收站功能
	@RequestMapping("/recover")
	public String recover(Integer totalcount, Integer pageCur,
			HttpSession session, Model model){
		Order allOrder = new Order();
		User user = (User)session.getAttribute("User");
		PageInfo  page =pageService.set(totalcount, pageCur);
		List<Order> orderList = new ArrayList<Order>();
		orderList =orderService.selectDeletedOrderByUserAccountAndPage(user.getAccount(), page);
		totalcount =orderService.selectDeletedOrderCountByUserAccountAndPage(user.getAccount());
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("orderList",orderList);
		model.addAttribute("AllOrder", allOrder);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "recover";
	}
	@RequestMapping("/findingDeletedOrder")
	public String toFindDeleted( Integer totalcount, Integer pageCur, 
			Model model,@ModelAttribute Order orderFid,HttpSession session) {
		User user = (User)session.getAttribute("User");
		Integer id = orderFid.getId();
		String types = orderFid.getTypes();
		List<Order> orderList = new ArrayList<Order>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Order ordernew = new Order();
		if(id != null && types != null) {
			orderList = Arrays.asList(orderService.getOne(id));
		}else if(types!= null) {
			orderList = orderService.selectOrderByUserAccountAndTypesAndPage(user.getAccount(), types, page);
		}else {
			orderList =orderService.selectOrderByUserAccountAndPage(user.getAccount(), page);
		}
		totalcount=orderList.size();
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("orderList",orderList);
		model.addAttribute("AllOrder", ordernew);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "/recover";
	}
	//彻底删除订单控制
	@RequestMapping("/delOrderWholly")//将请求映射为下列方法的注解
	public String DelOrderWholly(Integer id,Integer totalcount,Model model) {
		roomService.updateRoomStateTureById(orderService.selectRoomIdById(id));
		orderService.delOrderWholly(id);	
		model.addAttribute("totalcount",totalcount--);
		return "forward:recover";
	}
	//恢复订单
	@RequestMapping("/recoverOrder")//将请求映射为下列方法的注解
	public String RecoverOrder(Integer id,Integer totalcount,Model model) {
		orderService.recoverOrder(id);	
		model.addAttribute("totalcount",totalcount--);
		return "forward:recover";
	}
	//管理员部分订单控制

	//所有订单管理
	@RequestMapping("/magOrder")
	public String magOrder(Integer totalcount, Integer pageCur,
			HttpSession session, Model model){
		Order allOrder = new Order();
		//Manager manager = (Manager)session.getAttribute("User");
		PageInfo  page =pageService.set(totalcount, pageCur);
		List<Order> orderList = new ArrayList<Order>();
		orderList =orderService.selectAllOrderByPage(page);
		totalcount =orderService.selectOrderCount();
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("orderList",orderList);
		model.addAttribute("AllOrder", allOrder);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "magOrder";
	}
	//筛选订单
	@RequestMapping("/selectOrder")
	public String toSelect( Integer totalcount, Integer pageCur, 
			Model model,@ModelAttribute Order orderFid,HttpSession session) {
		Integer id = orderFid.getId();
		String types = orderFid.getTypes();
		List<Order> orderList = new ArrayList<Order>();
		PageInfo  page = pageService.set(totalcount, pageCur);
		Order ordernew = new Order();
		if(id != null && types != null) {
			orderList = Arrays.asList(orderService.getOne(id));
		}else if(types != null) {
			log.info(types);
			orderList = orderService.selectOrderByTypes(types, page);
		}else {
			orderList =orderService.selectAllOrderByPage(page);
		}
		totalcount=orderList.size();
		int totalpage = totalcount % 5==0?totalcount/5:totalcount/5+1;	
		model.addAttribute("orderList",orderList);
		model.addAttribute("AllOrder", ordernew);
		model.addAttribute("totalcount",totalcount);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pageCur",page.getPageCur());
		return "/magOrder";
	}
}
