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
table tr td input[type=text]{
	margin-left: 0px;
	width: 280px;
}
	
select {
	width: 284px;
	font-size: 16px;
	height: 36px;
	margin-left: 0;
}
	
</style>
</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="910" name="width"/>
	</jsp:include>
	<section style="height: 310px; width: 480px; margin-top: 80px;" class="content">
		<form:form commandName="manager" action="setPosition.do" id="setPositionForm" method="post" onsubmit="return check();">
			<form:hidden path="id"/>
            <form:hidden path="user.id"/>
			<section style="height: 100%">
				<div>
					<fieldset>
						<span style="margin: 10px 10px 0; color: red" id="errorMsg">${msg}</span>
						<table style="width: 100%">
							<tr>
								<td>
									<span class="field_label" style="width:120px;">管理人员姓名<span class="required"></span></span>
									${userInfo.name }
								</td>
							</tr>
                            <tr>
                                <td>
                                    <span class="field_label" style="width:120px;">职位<span class="required">*</span></span>
                                    <form:input path="position" placeholder="请输入中文职位名称"/>
                                </td>
                            </tr>
							<tr>
								<td style="display: inline;">
									<span class="field_label" style="width:120px;">Title<span class="required">*</span></span>
									<form:input path="title" required="true" placeholder="请输入英文Title" maxlength="64"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label" style="width:120px;">职位级别<span class="required">*</span></span>
                                    <form:input path="type" required="true" maxlength="64" list="position_level"/>
                                    <datalist id="position_level">
                                        <option>VP</option>
                                        <option>Director</option>
                                        <option>Manager</option>
                                        <option>Leader</option> 
                                    </datalist>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label" style="width:120px;">直属上级</span>
									<form:select path="superManager.id">
                                        <option></option>
                                        <form:options items="${managerList}" itemLabel="fullTitle" itemValue="id"/>  
                                    </form:select>
								</td>
							</tr>
							<tr>
								<td align="right" style="padding-right: 38px; height: 60px;">
									<input type="button" class="blueButton" value="取消" onclick="window.location = '<spring:url value='/admin/userList.do'/>'">
									<input type="submit" class="blueButton" value="确定">
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</section>
		</form:form>
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
