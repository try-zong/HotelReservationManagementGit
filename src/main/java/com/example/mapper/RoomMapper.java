package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.entity.PageInfo;
import com.example.entity.Room;

public interface RoomMapper {
	public Room getOne(int id); //根据id查询房间信息
	@Select("select * from Room")
	public List<Room>findAll();//查询所有房间
    public List<Room> selectRoomByTypes(@Param("types")String types,@Param("offset")int offset,@Param("rows")int rows);//分类分页查询所属房间
    //分类分页查询房间，page包含分页信息
    public List<Room> selectAllRoomByPage(PageInfo page);
    public int selectRoomCount();//查询房间数量
    @Insert("insert into room(id,state,info,types,comment,price) values(#{id},#{state},#{info},#{types},#{comment},#{price}) ")
    public int addRoom(Room room); //添加房间
    @Delete("delete from Room where id=#{id}")
    public int  deleteRoom(int id); //删除一个房间
    public int  updateRoomById(Room room); //修改房间信息
    public int updateRoomStateById(int id); //修改房间状态，用户预订
}
