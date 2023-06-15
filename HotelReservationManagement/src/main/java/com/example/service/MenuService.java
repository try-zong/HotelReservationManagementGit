package com.example.service;

import java.util.List;

import com.example.entity.Menu;

public interface MenuService {
	  List<Menu> selectMenuByParentId(int parent_id);  					

}
