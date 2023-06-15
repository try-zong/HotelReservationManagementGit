package com.example.mapper;

import java.util.List;

import com.example.entity.Menu;

public interface MenuMapper {
	List<Menu> selectMenuByParentId(int parent_id);
}
