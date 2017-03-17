/**
 * 
 */
package com.yls.freamwork.utils;

import java.util.List;

import com.github.pagehelper.Page;

/**
 * @author Lian Shan Yang
 *
 */
public class YlsResultForGrid<T> {

	//当前页码
	private int currPage;
	//总页数
	private int totalPage;
	//总记录数
	private int totalCount;
	
	private List<T> list;
	
	public YlsResultForGrid(List<T> list) {
		super();
		this.list = list;
		if(list instanceof Page){
			Page<T> page = (Page<T>) list;
			this.currPage = page.getPageNum();
			this.totalPage = page.getPages();
			this.totalCount = (int) page.getTotal();
		}
	}

	public YlsResultForGrid() {
		super();
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
