package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.PageInfo;
import com.example.entity.Room;

public interface RoomService {
	public Room getOne(int id); //根据id查询房间信息
	public List<Room>findAll();//查询所有房间
	//分类分页查询
    public List<Room> selectRoomByTypes(String types,PageInfo page);//分房间类别查询
    public Room selectRoomByState(String state);//分房间状态查询
    public Room selectRoomByPrice(String price);//分房间价格查询
    //分类分页查询房间，page包含分页信息
    public List<Room> selectAllRoomByPage(PageInfo page);
    public int selectRoomCount();
    public int addRoom(Room room,MultipartFile file); //添加房间
    public int deleteRoom(int id); //删除一个房间
    public int updateRoomById(Room room); //修改房间信息
    public int updateRoomStateTureById(int id); //修改房间状态可租
    public int updateRoomStateById(Room room); //修改房间状态，用户预订
    public boolean saveEdit(Room room,MultipartFile file); //修改房间信息
}
