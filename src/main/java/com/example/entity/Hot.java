package com.example.entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class Hot implements Serializable{
	private static final long serialVersionUID = 1L;
	//数据可视化实体类
	private String name;
	private double scale;
	private int num;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
