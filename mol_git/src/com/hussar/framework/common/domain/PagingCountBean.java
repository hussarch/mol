package com.hussar.framework.common.domain;

/**
 * @PagingCountBean.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-4-12, ©2015 some rights reserved
 */
public class PagingCountBean {

	private int totalRecordCount;
	private int totalPageCount;
	private int currentPage;
	private int pageSize;

	public PagingCountBean(int totalRecordCount, int currentPage, int pageSize) {
		this.totalRecordCount = totalRecordCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.init(totalRecordCount, pageSize);
	}

	private void init(int totalRecordCount, int pageSize) {
		if (totalRecordCount <= pageSize) {
			this.totalPageCount = 1;
		} else {
			int size = totalRecordCount / pageSize;
			if (size * pageSize == totalRecordCount) {
				this.totalPageCount = size;
			} else {
				this.totalPageCount = size + 1;
			}
		}
	}

	public boolean isFirestPage() {
		return this.currentPage == 1;
	}

	public boolean isLastPageEnable() {
		return this.currentPage > 1;
	}

	public boolean isNextPageEnable() {
		return this.currentPage < this.totalPageCount;
	}

	public boolean isLastPage() {
		return this.currentPage == this.totalPageCount;
	}

	public int getNextPage() {
		return this.currentPage + 1;
	}
	
	public int getLastPage(){
		return this.currentPage - 1;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
