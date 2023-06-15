package com.example.service;

import java.util.List;


import com.example.entity.Order;
import com.example.entity.PageInfo;

public interface OrderService {
	public int addOrder(Order order); //添加订单
	public Order getOne(int id);//通过订单号查询订单
	//通过用户名查询订单
	public List<Order> selectOrderByUserAccountAndPage(String account,PageInfo page);
	//通过用户名查询订单数量
	public int selectOrderCountByUserAccountAndPage(String account);
	//通过用户名和类型查询订单
	public List<Order> selectOrderByUserAccountAndTypesAndPage(String account,String types,PageInfo page);
	public List<Order> selectAllOrderByPage(PageInfo page);//查询所有订单
	public List<Order> selectOrderByMoney(double money);//通过金额查询订单
	//修改订单状态
	//删除订单
	public int deleteOrderById(int id);
}
