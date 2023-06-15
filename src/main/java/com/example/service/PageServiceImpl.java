package com.example.service;

import org.springframework.stereotype.Service;

import com.example.controller.RoomInfoController;
import com.example.entity.PageInfo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PageServiceImpl implements PageService{

	@Override
	public PageInfo set(Integer totalcount, Integer pageCur) {
		// TODO Auto-generated method stub
		PageInfo  page = new PageInfo();
		page.setRows(5);
		log.info("接口1PageCur="+ pageCur);
		if (pageCur == null){
			log.info("接口2PageCur="+ pageCur);
			pageCur = 1;
			page.setPageCur(pageCur);
		}else	page.setPageCur(pageCur);
		if(totalcount==null ){
			page.setOffset(0);                      
			}else{
				page.setOffset(5*(pageCur-1));
			}
		return page;
	}

}
