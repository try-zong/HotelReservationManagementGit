package com.example.entity;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Order {
	private int id;
	private double money;  
	private double in_time;  
	private double out_time;  
	private String state;
	private String types;
	private String phone;
	private String room_id;
	private String user_account2;
	private int deleted;	//判断该订单是否删除,1为删除，0为未删
	private LocalDateTime deletedAt;//删除订单的时间
	private Room room;
	private User user; 
}
