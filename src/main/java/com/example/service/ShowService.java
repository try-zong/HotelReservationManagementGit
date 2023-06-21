package com.example.service;

import java.util.List;

import com.example.entity.Hot;

public interface ShowService {
	public List<Hot> selectTopThreeRoom();	//获取最热门的3个房间
	public Hot selectTopOneRoom();//获取最热门的1个房间
	public List<Hot> selectCountGroupByTypes();  //根据房间类型对租借量进行统计
	public List<Hot> selectOrderCountGroupByCreate();  //根据订单创建时间对订单进行统计
	public List<Hot> sumMoneyGroupByCreate();  //根据订单创建时间对盈利进行统计
}
