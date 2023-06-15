package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Menu;
import com.example.mapper.MenuMapper;
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public List<Menu> selectMenuByParentId(int parent_id) {
		// TODO Auto-generated method stub
		return menuMapper.selectMenuByParentId(parent_id);
	}

}
