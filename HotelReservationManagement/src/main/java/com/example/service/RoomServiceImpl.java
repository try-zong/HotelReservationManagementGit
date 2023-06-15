package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.PageInfo;
import com.example.entity.Room;
import com.example.mapper.RoomMapper;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomMapper roomMapper;
	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return roomMapper.findAll();
	}

	@Override
	public Room getOne(int room_id) {
		// TODO Auto-generated method stub
		return roomMapper.getOne(room_id);
	}

	@Override
	public List<Room> selectRoomByTypes(String types,PageInfo page) {
		// TODO Auto-generated method stub
		int rows = page.getRows();
		int offset = page.getOffset();
		return roomMapper.selectRoomByTypes(types,offset,rows);
	}

	@Override
	public Room selectRoomByState(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room selectRoomByPrice(String price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> selectAllRoomByPage(PageInfo page) {
		// TODO Auto-generated method stub
		return roomMapper.selectAllRoomByPage(page);
	}

	@Override
	public int selectRoomCount() {
		// TODO Auto-generated method stub
		return roomMapper.selectRoomCount();
	}

	@Override
	public int addRoom(Room room) {
		// TODO Auto-generated method stub
		return roomMapper.addRoom(room);
	}

	@Override
	public int deleteRoom(int id) {
		// TODO Auto-generated method stub
		return roomMapper.deleteRoom(id);
	}

	@Override
	public int updateRoomById(Room room) {
		// TODO Auto-generated method stub
		return roomMapper.updateRoomById(room);
	}

	@Override
	public int updateRoomStateById(int id) {
		// TODO Auto-generated method stub
		return roomMapper.updateRoomStateById(id);
	}

}
