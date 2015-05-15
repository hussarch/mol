<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>职务设置</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/conf.setting.css">

<style type="text/css">

.field_label{
    height:39px;
    text-align: right;
    width: 120px;
    display: inline-block; 
    color: #FFFFFF;
    background-color: #95CACA;
    border-radius: 3px;
    margin: 0;
    padding: 0;
    line-height:39px;
}
	
</style>
</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="910" name="width"/>
	</jsp:include>
	<section style="height: 310px; width: 480px; margin-top: 80px;" class="content">
		<section style="height: 100%">
			<div>
				<fieldset>
					<span style="margin: 10px 10px 0; color: red" id="errorMsg">${msg}</span>
					<table style="width: 100%">
						<tr>
							<td>
								<span class="field_label" style="width:120px;">管理人员姓名<span class="required"></span></span>
								<span class="field_value">${manager.user.name }</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="field_label" style="width:120px;">Title<span class="required">*</span></span>
								<span class="field_value">${manager.title}</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="field_label" style="width:120px;">职位<span class="required">*</span></span>
								<span class="field_value">${manager.position}</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="field_label" style="width:120px;">职位级别<span class="required">*</span></span>
								<span class="field_value">${manager.type }</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="field_label" style="width:120px;">直属上级</span>
                                <span class="field_value">${manager.superManager.user.name }</span>
							</td>
						</tr>
						<tr>
							<td align="right" style="padding-right: 0px; height: 60px;">
                             <form:form commandName="manager" action="deletePosition.do">
                                <form:hidden path="id"/>
								<input type="button" class="blueButton" value="确定" onclick="window.location = '<spring:url value='/admin/managerList.do'/>'">
								<input type="submit" class="blueButton" value="删除">
							</form:form>
                            </td>
						</tr>
					</table>
				</fieldset>
			</div>
		</section>
	</section>
	<footer>
		<div class="footer-left">
            <div class="comm-footer">
			<section>Mavenir ShangHai Office Service on Line © hussar HTML5</section>
			<section>请使用Cherome登录</section>
			</div>
		</div>
	</footer>
</body>
</html>
