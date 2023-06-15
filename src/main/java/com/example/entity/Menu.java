package com.example.entity;

import java.util.List;

import lombok.Data;

@Data
public class Menu {
	private int id;
	private String name;
	private int parent_id;
	List<Menu> chilidren;
}
