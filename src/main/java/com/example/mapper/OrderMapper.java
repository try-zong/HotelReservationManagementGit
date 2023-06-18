package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.entity.Order;
import com.example.entity.PageInfo;
import com.example.entity.Room;

public interface OrderMapper {
	public int addOrder(Order order); //添加订单
	public Order getOne(int id);//通过订单号查询订单
	//通过用户名查询订单数量
	public int selectRoomIdById(int id);//通过订单号查询订单关联的房间
	//通过用户名查询订单
	public List<Order> selectOrderByUserAccountAndPage(@Param("account")String account,@Param("offset")int offset,@Param("rows")int rows);
	//通过用户名查询订单数量
	public int selectOrderCountByUserAccountAndPage(String account);
	//获取所有订单数量
	public int selectOrderCount();
	//通过用户名和类型查询订单
	public List<Order> selectOrderByUserAccountAndTypesAndPage(@Param("account")String account,@Param("types")String types,@Param("offset")int offset,@Param("rows")int rows);
	public List<Order> selectAllOrderByPage(PageInfo page);	//查询所有订单
	public List<Order> selectOrderByMoney(double money);	//通过金额查询订单
	//删除订单
	@Delete("delete from `Order` where id=#{id}")
	public int deleteOrderById(int id);
	@Update("update `order` set state='已完成'  where id=#{id}")
    public int  updateOrderStateById(int id); //修改房间状态
}
