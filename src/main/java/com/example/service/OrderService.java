package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.entity.Order;
import com.example.entity.PageInfo;

public interface OrderService {
	public int addOrder(Order order); //添加订单
	public Order getOne(int id);//通过订单号查询订单
	public int selectRoomIdById(int id);//通过订单号查询订单关联的房间
	//通过用户名查询订单
	public List<Order> selectOrderByUserAccountAndPage(String account,PageInfo page);
	//通过用户名查询订单数量
	public int selectOrderCountByUserAccountAndPage(String account);
	//获取所有订单数量
	public int selectOrderCount();
	//通过用户名和类型查询订单
	public List<Order> selectOrderByUserAccountAndTypesAndPage(String account,String types,PageInfo page);
	//通过用户名和类型查询已删除订单
	public List<Order> selectDeletedOrderByUserAccountAndTypesAndPage(String account,String types,PageInfo page);
	public List<Order> selectAllOrderByPage(PageInfo page);	//查询所有订单
	public List<Order> selectOrderByMoney(double money);	//通过金额查询订单
	public List<Order> selectOrderByTypes(String types,PageInfo page);	//通过类型查询订单
	//修改订单状态
    public int  updateOrderStateById(int id);
	//删除订单
	public int deleteOrderById(int id);
	//回收站部分
	//通过用户名查询已删除订单
	public List<Order> selectDeletedOrderByUserAccountAndPage(String account,PageInfo page);
	//通过用户名查询已删除订单数量
	public int selectDeletedOrderCountByUserAccountAndPage(String account);
	public int recoverOrder(int id); //恢复订单
	public int delOrderWholly(int id); //彻底删除订单
}
