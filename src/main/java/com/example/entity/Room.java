package com.example.entity;
import lombok.Data;

@Data
public class Room {
	private int id;
	private String state;  
	private String types; 
	private String info;  
	private String comment;  
	private double price; 
	private long version;
	private String img;
	private String user_account1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUser_account1() {
		return user_account1;
	}
	public void setUser_account1(String user_account1) {
		this.user_account1 = user_account1;
	} 
	
}
