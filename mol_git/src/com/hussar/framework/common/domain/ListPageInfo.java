package com.hussar.framework.common.domain;

/**
 * @ListPageInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-4-28, ©2015 some rights reserved
 */
public class ListPageInfo {

	private Integer id;
	private String condition;
	private Integer page;
	private String flag;
	private Integer maxCountOfPerPage;
	
	public ListPageInfo(){
		maxCountOfPerPage = 10;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id=").append(id);
		builder.append(", condition=").append(condition);
		builder.append(", page=").append(page);
		builder.append(", flag=").append(flag);
		builder.append(", maxCountOfPerPage=").append(maxCountOfPerPage);
		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getMaxCountOfPerPage() {
		return maxCountOfPerPage;
	}

	public void setMaxCountOfPerPage(Integer maxCountOfPerPage) {
		this.maxCountOfPerPage = maxCountOfPerPage;
	}

}
