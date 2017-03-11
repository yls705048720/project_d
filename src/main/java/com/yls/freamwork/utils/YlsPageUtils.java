/**
 * 
 */
package com.yls.freamwork.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author YLS
 *
 */
public class YlsPageUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//�ܼ�¼��
	private int totalCount;
	//ÿҳ��¼��
	private int pageSize;
	//��ҳ��
	private int totalPage;
	//��ǰҳ��
	private int currPage;
	//�б�����
	private List<?> list;
	
	/**
	 * ��ҳ
	 * @param list        �б�����
	 * @param totalCount  �ܼ�¼��
	 * @param pageSize    ÿҳ��¼��
	 * @param currPage    ��ǰҳ��
	 */
	public YlsPageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
