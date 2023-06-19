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
}
