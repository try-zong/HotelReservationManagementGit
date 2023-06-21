package com.example.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.LoginController;
import com.example.entity.Order;
import com.example.entity.PageInfo;
import com.example.mapper.OrderMapper;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public Order getOne(int id) {
		// TODO Auto-generated method stub
		return orderMapper.getOne(id);
	}

	@Override
	public int selectRoomIdById(int id) {
		// TODO Auto-generated method stub
		return orderMapper.selectRoomIdById(id);
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
	public int selectOrderCount() {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderCount();
	}

	@Override
	public List<Order> selectOrderByUserAccountAndTypesAndPage(String account, String types, PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
		int offset = page.getOffset();
	//log.info(String.valueOf(rows));
		//log.info(String.valueOf(offset));
		return orderMapper.selectOrderByUserAccountAndTypesAndPage(account, types, offset, rows);
	}

	@Override
	public List<Order> selectDeletedOrderByUserAccountAndTypesAndPage(String account, String types, PageInfo page) {
		int rows = page.getRows();
		int offset = page.getOffset();
		return orderMapper.selectDeletedOrderByUserAccountAndTypesAndPage(account, types, offset, rows);
	}

	@Override
	public List<Order> selectAllOrderByPage(PageInfo page) {
		// TODO Auto-generated method stub
		return orderMapper.selectAllOrderByPage(page);
	}

	@Override
	public List<Order> selectOrderByTypes(String types, PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
		int offset = page.getOffset();
	//	log.info(String.valueOf(rows));
	//	log.info(String.valueOf(offset));
		return orderMapper.selectOrderByTypes(types, offset, rows);
	}

	@Override
	public List<Order> selectOrderByMoney(double money) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderByMoney(money);
	}

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		order.setCreate(LocalDate.now());
		//log.info("chaungjian"+String.valueOf(order.getCreate()));
		return orderMapper.addOrder(order);
	}
	
	@Override
	public int updateOrderStateById(int id) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderStateById(id);
	}

	//删除订单
	@Override
	public int deleteOrderById(int id) {
		// TODO Auto-generated method stub
		Order order =orderMapper.getOne(id);
        order.setDeleted(1);
        order.setDeletedAt(LocalDateTime.now()); //返回当前删除时间
		return orderMapper.updateOrderByOrder(order);
	}

	@Override
	public int recoverOrder(int id) {
		// TODO Auto-generated method stub
		Order order =orderMapper.getOne(id);
        order.setDeleted(0);
        order.setDeletedAt(null); //返回当前删除时间
		return orderMapper.updateOrderByOrder(order);
	}

	@Override
	public List<Order> selectDeletedOrderByUserAccountAndPage(String account, PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
	    int offset = page.getOffset();
		return orderMapper.selectDeletedOrderByUserAccountAndPage(account, offset, rows);
	}

	@Override
	public int selectDeletedOrderCountByUserAccountAndPage(String account) {
		// TODO Auto-generated method stub
		return orderMapper.selectDeletedOrderCountByUserAccountAndPage(account);
	}
	//彻底删除订单
	@Override
	public int delOrderWholly(int id) {
		// TODO Auto-generated method stub
		return orderMapper.deleteOrderById(id);
	}

}
