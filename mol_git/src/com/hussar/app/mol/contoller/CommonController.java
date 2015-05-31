package com.hussar.app.mol.contoller;

import javax.servlet.http.HttpSession;

import com.hussar.framework.common.domain.ListPageInfo;

/**
 * @CommonController.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-30, ©2015 some rights reserved
 */
public class CommonController {
	
	protected ListPageInfo getListPageInfo(Integer page, HttpSession session){
		int currentPage = page == null ? 1 : page;
		ListPageInfo listPageInfo = getListPageInfo(session);
		if(listPageInfo != null && listPageInfo.getPage() != null){
			currentPage = listPageInfo.getPage();
		}else{
			listPageInfo = new ListPageInfo();
			listPageInfo.setPage(currentPage);
		}
		return listPageInfo;
	}
	
	private ListPageInfo getListPageInfo(HttpSession session){
		Object info = session.getAttribute(getListPageInfoTag());
		if(info != null){
			session.removeAttribute(getListPageInfoTag());
			return (ListPageInfo)info;
		}else{
			return null;
		}
	}
	
	public String getListPageInfoTag(){
		return "ListPageInfo_" + this.getClass().getSimpleName();
	}
	
	
}
