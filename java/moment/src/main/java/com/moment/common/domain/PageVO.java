package com.moment.common.domain;

import java.util.List;

public class PageVO<E> {
	private int pageSize=10;//分页大小
	private int curPage=1;//当前页
	private int totalPage;//总页数（需要计算）
	private int totalRecord;//总记录数
	private int offset;//起始位置（需要计算）
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	private List<E> records;//记录
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1){
			this.pageSize=1;
		}else{
			this.pageSize = pageSize;
		}
		
		this.setCurPage(this.curPage);
		//避免先设置了当前页导致起始位置错误
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		if(curPage<1){//防止出现负数的情况
			this.curPage=1;
		}else{
			this.curPage = curPage;
		}
		//计算分页的起始位置
		this.offset=(this.curPage-1)*this.pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//计算总页数
		int temp=this.totalRecord/this.pageSize;
		if(this.totalRecord%this.pageSize!=0){
			temp++;
		}
		this.totalPage=temp;
	}
	public List<E> getRecords() {
		return records;
	}
	public void setRecords(List<E> records) {
		this.records = records;
	}
}
