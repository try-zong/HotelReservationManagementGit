package com.example.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Hot;
import com.example.mapper.ShowMapper;
@Service
public class ShowServiceImpl implements ShowService{
	@Autowired
	private ShowMapper showMapper;
	@Override
	public List<Hot> selectTopThreeRoom() {
		// TODO Auto-generated method stub
		return showMapper.selectTopThreeRoom();
	}

	@Override
	public Hot selectTopOneRoom() {
		// TODO Auto-generated method stub
		return showMapper.selectTopOneRoom();
	}

	@Override
	public List<Hot> selectCountGroupByTypes() {
		// TODO Auto-generated method stub
		return showMapper.selectCountGroupByTypes();
	}

}
