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
		topOne = showService.selectTopOneRoom();
		topThree = showService.selectTopThreeRoom();
		roomScale = showService.selectCountGroupByTypes();
		//log.info("比例"+String.valueOf(roomScale.get(0).getScale()));
		model.addAttribute("topOne",topOne);
		model.addAttribute("topThree",topThree);
		model.addAttribute("roomScale",roomScale);
		return "show";
	}
}
