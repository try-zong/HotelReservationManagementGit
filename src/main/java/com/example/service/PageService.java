package com.example.service;

import com.example.entity.PageInfo;

public interface PageService {
	public PageInfo set(Integer totalcount, Integer pageCur);
}
