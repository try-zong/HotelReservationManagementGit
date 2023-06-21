package com.example.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Order {
	private Integer id;
	private double money;  
	private double in_time;  
	private double out_time;  
	private String state;
	private String types;
	private String phone;
	private String room_id;
	private String user_account2;
	private LocalDate create;     //创建订单的时间
	private int deleted;	//判断该订单是否删除,1为删除，0为未删
	private LocalDateTime deletedAt;//删除订单的时间
	private Room room;
	private User user;
	
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getUser_account2() {
		return user_account2;
	}
	public void setUser_account2(String user_account2) {
		this.user_account2 = user_account2;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getIn_time() {
		return in_time;
	}
	public void setIn_time(double in_time) {
		this.in_time = in_time;
	}
	public double getOut_time() {
		return out_time;
	}
	public void setOut_time(double out_time) {
		this.out_time = out_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	} 
}
