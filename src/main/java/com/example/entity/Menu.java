package com.example.entity;

import java.util.List;

import lombok.Data;

@Data
public class Menu {
	private int id;
	private String name;
	private int parent_id;
	List<Menu> chilidren;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public List<Menu> getChilidren() {
		return chilidren;
	}
	public void setChilidren(List<Menu> chilidren) {
		this.chilidren = chilidren;
	}
}
