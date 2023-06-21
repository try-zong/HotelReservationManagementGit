package com.example.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Constants;
import com.example.entity.PageInfo;
import com.example.entity.Room;
import com.example.mapper.RoomMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	public int addRoom(Room room,MultipartFile file) {
		// TODO Auto-generated method stub
		if (!file.isEmpty()){
            // 原始文件名
            String originalFileName = file.getOriginalFilename(); 
            // 获取图片后缀
            String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")); 
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            // 图片存储路径
            String filePath = Constants.IMG_PATH + fileName;
     //       log.info("路径"+filePath);
            File saveFile = new File(filePath);
            try {
        //    	log.info("房间号1"+String.valueOf(room.getId()));
                // 将上传的文件保存到服务器文件系统
                file.transferTo(saveFile);
                // 记录服务器文件系统图片名称
                room.setImg("\\img\\"+fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
	public int updateRoomStateTureById(int id) {
		// TODO Auto-generated method stub
		return roomMapper.updateRoomStateTureById(id);
	}

	@Override
	public int updateRoomStateById(Room room) {
		// TODO Auto-generated method stub
		return roomMapper.updateRoomStateById(room);
	}
	//修改房间信息
	@Override
	public  boolean saveEdit(Room room, MultipartFile file) {
		if (!file.isEmpty()){
            // 原始文件名
            String originalFileName = file.getOriginalFilename(); 
            // 获取图片后缀
            String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")); 
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            // 图片存储路径
            String filePath = Constants.IMG_PATH + fileName;
            //log.info("路径"+filePath);
            File saveFile = new File(filePath);
            try {
            	//log.info("房间号1"+String.valueOf(room.getId()));
                // 将上传的文件保存到服务器文件系统
                file.transferTo(saveFile);
                // 记录服务器文件系统图片名称
                room.setImg(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return roomMapper.saveEdit(room)>0;
	}	
}
