package com.example.entity;
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
	private Room room;
	private User user; 
}
