package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Hot;
import com.example.service.PageService;
import com.example.service.ShowService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ShowController {
	
	@Autowired//自动装配的注解     
	private ShowService showService;
	
	@RequestMapping("/show")
	public String show( Model model) {
		Hot topOne = new Hot();
		List<Hot> topThree = new ArrayList<Hot>();
		List<Hot> roomScale = new ArrayList<Hot>();
		List<Hot> orderCount = new ArrayList<Hot>();
		List<Hot> sumMoney = new ArrayList<Hot>();
		List<Hot> heightRoom = new ArrayList<Hot>();
		topOne = showService.selectTopOneRoom();
		topThree = showService.selectTopThreeRoom();
		roomScale = showService.selectCountGroupByTypes();
		orderCount = showService.selectOrderCountGroupByCreate();
		sumMoney = showService.sumMoneyGroupByCreate();
		heightRoom = showService.heightCommentRoom();
		//log.info("比例"+String.valueOf(orderCount.get(1).getName()));
		model.addAttribute("topOne",topOne);
		model.addAttribute("topThree",topThree);
		model.addAttribute("roomScale",roomScale);
		model.addAttribute("orderCount",orderCount);
		model.addAttribute("sumMoney",sumMoney);
		model.addAttribute("heightRoom",heightRoom);
		return "show";
	}
	@RequestMapping("/magMain")
	public String magMain( Model model) {
		Hot topOne = new Hot();
		List<Hot> topThree = new ArrayList<Hot>();
		List<Hot> roomScale = new ArrayList<Hot>();
		List<Hot> orderCount = new ArrayList<Hot>();
		List<Hot> sumMoney = new ArrayList<Hot>();
		topOne = showService.selectTopOneRoom();
		topThree = showService.selectTopThreeRoom();
		roomScale = showService.selectCountGroupByTypes();
		orderCount = showService.selectOrderCountGroupByCreate();
		sumMoney = showService.sumMoneyGroupByCreate();
		//log.info("比例"+String.valueOf(orderCount.get(1).getName()));
		model.addAttribute("topOne",topOne);
		model.addAttribute("topThree",topThree);
		model.addAttribute("roomScale",roomScale);
		model.addAttribute("orderCount",orderCount);
		model.addAttribute("sumMoney",sumMoney);
		return "magMain";
	}
}
