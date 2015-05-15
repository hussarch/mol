package com.hussar.framework.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @PagingTag.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-4-23, ©2015 some rights reserved
 */
public class PagingTag extends SimpleTagSupport {

	private PagingCountBean pagingCountBean;
	private String url;
	private String condition;
	
	public void doTag() throws JspException, IOException {
		StringBuilder builder = new StringBuilder(1000);
		builder.append(getPageScript());
		builder.append(getPageForm());
		builder.append("<div style=\"height: 30px; margin-top: 5px;\">\n");
		builder.append(getCurrentPageInfo());
		builder.append(getPage());
		builder.append("</div>\n");
		getJspContext().getOut().append(builder.toString());
	}
	
	/**
	 *  function pageTo(page){
    		document.getElementById("pageIndex").value = page;
    		document.getElementById("pageForm").submit();  
    	}
	 * @return
	 */
	private String getPageScript(){
		StringBuilder builder = new StringBuilder(200);
		builder.append("<script type=\"text/javascript\">\n");
		builder.append("	function pageTo(page){\n");
		builder.append("		document.getElementById(\"pageIndex\").value = page;\n");
		builder.append("		document.getElementById(\"pageForm\").submit()\n");
		builder.append("	}\n");
		builder.append("</script>\n");
		return builder.toString();
	}
	
	/**
	 *  <form action="<spring:url value='/admin/userList.do'/>" method="get" id="pageForm">
     *     	<input type="hidden" name="condition" value="${condition }">
     *     	<input type="hidden" name="page" value="${page }" id="pageIndex">
     *  </form>
	 * @return
	 */
	private String getPageForm(){
		StringBuilder builder = new StringBuilder(300);
		builder.append("<form action=\"").append(url).append("\" method=\"post\" id=\"pageForm\">\n");
		builder.append("   	<input type=\"hidden\" name=\"condition\" value=\"").append(condition).append("\">\n");
		builder.append("   	<input type=\"hidden\" name=\"page\" id=\"pageIndex\">\n");
		builder.append("</form>\n");
		return builder.toString();
	}
	
	/**
	 * <div style="float: left; height: 20px; margin: 0px;">
	 * 当前第${pageInfo.currentPage}页共${pageInfo.totalPageCount}页，共${pageInfo.totalRecordCount}条记录
	 * </div>
	 * @return
	 */
	private String getCurrentPageInfo(){
		StringBuilder builder = new StringBuilder(300);
		builder.append("<div style=\"float: left; height: 20px; margin: 0px;\">");
		builder.append("当前是第").append(this.pagingCountBean.getCurrentPage()).append("页");
		builder.append("共").append(this.pagingCountBean.getTotalPageCount()).append("页，");
		builder.append("共").append(this.pagingCountBean.getTotalRecordCount()).append("条记录");
		builder.append("</div>\n");
		return builder.toString();
	}
	
	private String getPage(){
		StringBuilder builder = new StringBuilder(500);
		builder.append("<div style=\"float: right; height: 20px; margin: 0px;\">");
		builder.append(getPageButton("首页", !pagingCountBean.isFirestPage(), 1));
		builder.append(getPageButton("上一页", pagingCountBean.isLastPageEnable(), pagingCountBean.getLastPage()));
		builder.append(getPageButton("下一页", pagingCountBean.isNextPageEnable(), pagingCountBean.getNextPage()));
		builder.append(getPageButton("末页", !pagingCountBean.isLastPage(), pagingCountBean.getTotalPageCount()));
		builder.append("</div>\n");
		return builder.toString();
	}
	
	
	/**
	 * 2
	 * <span><a href="#" onclick="pageTo(1)">首页</a></span>
	 * <span><a href="#" onclick="pageTo(3)">上一页</a></span>
     * <span><a href="#" onclick="pageTo(5)">下一页</a></span>
     * <span><a href="#" onclick="pageTo(14)">末页</a></span>
	 * @return
	 */
	private String getPageButton(String name, boolean enable, int page){
		StringBuilder builder = new StringBuilder(100);
		builder.append("<span>");
		builder.append("<a");
		if(enable){
			builder.append(" href=\"#\" ");
			builder.append(" onclick=\"pageTo(").append(page).append(")\"");
		}
		builder.append(">");
		builder.append(name);
		builder.append("</a>");
		builder.append("</span>\n");
		return builder.toString();
	}
	

	public PagingCountBean getPagingCountBean() {
		return pagingCountBean;
	}

	public void setPagingCountBean(PagingCountBean pagingCountBean) {
		this.pagingCountBean = pagingCountBean;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
