package com.example.entity;

import lombok.Data;

@Data
public class PageInfo {
	   private int pageCur;
	   private int offset;  //该页起始记录

	   private int rows;   //每页记录数

	public int getPageCur() {
		return pageCur;
	}

	public void setPageCur(int pageCur) {
		this.pageCur = pageCur;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
