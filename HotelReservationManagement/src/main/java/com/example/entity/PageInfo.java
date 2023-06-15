package com.example.entity;

import lombok.Data;

@Data
public class PageInfo {
	   private int pageCur;
	   private int offset;  //该页起始记录

	   private int rows;   //每页记录数
}
