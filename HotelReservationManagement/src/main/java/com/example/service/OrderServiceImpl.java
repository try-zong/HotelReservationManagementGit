package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Order;
import com.example.entity.PageInfo;
import com.example.mapper.OrderMapper;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public Order getOne(int id) {
		// TODO Auto-generated method stub
		return orderMapper.getOne(id);
	}

	@Override
	public List<Order> selectOrderByUserAccountAndPage(String account,PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
		int offset = page.getOffset();
		return orderMapper.selectOrderByUserAccountAndPage(account,offset,rows);
	}

	@Override
	public int selectOrderCountByUserAccountAndPage(String account) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderCountByUserAccountAndPage(account);
	}

	@Override
	public List<Order> selectOrderByUserAccountAndTypesAndPage(String account, String types, PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
		int offset = page.getOffset();
		return orderMapper.selectOrderByUserAccountAndTypesAndPage(account, types, offset, rows);
	}

	@Override
	public List<Order> selectAllOrderByPage(PageInfo page) {
		// TODO Auto-generated method stub
		return orderMapper.selectAllOrderByPage(page);
	}

	@Override
	public List<Order> selectOrderByMoney(double money) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderByMoney(money);
	}

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.addOrder(order);
	}
	//删除订单
	@Override
	public int deleteOrderById(int id) {
		// TODO Auto-generated method stub
		return orderMapper.deleteOrderById(id);
	}

}
