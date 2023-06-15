package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Order;
import com.example.entity.PageInfo;

public interface OrderMapper {
	public int addOrder(Order order); //添加订单
	public Order getOne(int id);//通过订单号查询订单
	//通过用户名查询订单
	public List<Order> selectOrderByUserAccountAndPage(@Param("account")String account,@Param("offset")int offset,@Param("rows")int rows);
	//通过用户名查询订单数量
	public int selectOrderCountByUserAccountAndPage(String account);
	//通过用户名和类型查询订单
	public List<Order> selectOrderByUserAccountAndTypesAndPage(@Param("account")String account,@Param("types")String types,@Param("offset")int offset,@Param("rows")int rows);
	public List<Order> selectAllOrderByPage(PageInfo page);//查询所有订单
	public List<Order> selectOrderByMoney(double money);//通过金额查询订单
	//删除订单
	@Delete("delete from `Order` where id=#{id}")
	public int deleteOrderById(int id);
}
