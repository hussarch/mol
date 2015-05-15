<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>员工详细信息</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/conf.setting.css">

<style type="text/css">

fieldset{
	border: 0px;
}

table{
      padding-top: 0;
	  width: 100%;
	  height: 100%;
	  border-spacing: 0;
      border-collapse: collapse;
}

tr{
	height: 40px;
}

.field_label{
	height:39px;
	text-align: right;
	width: 120px;
	display: inline-block; 
	color: #FFFFFF;
	background-color: #458B74;
	border-radius: 3px;
	margin: 0;
	padding: 0;
	line-height:39px;
}

tr, td, th {
    padding: 0;
    margin: 0;
}
	
</style>

<script type="text/javascript">
	function gotoList(){
		window.location = "<spring:url value='/admin/userList.do'/>";
	}
	
	function gotoUpdate(){
		window.location = "<spring:url value='/admin/operation.do'/>?flag=update&id=${listPageInfo.id}";
	}
	
</script>

</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="910" name="width"/>
	</jsp:include>
	<section style="height: 315px; width: 750px; margin-top: 80px; padding-bottom: 0;" class="content">
			<section style="height: 100%; width: 100%; margin-top: 0;">
				<div>
					<fieldset>
						<table style="width: 100%;">
							<tr>
								<td width="50%">
									<span class="field_label">姓名</span>
									<span class="field_value">${userInfo.name}</span>
								</td>
								<td width="50%">
									<span class="field_label">英文名</span>
									<span class="field_value">${userInfo.englishName}</span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label">邮箱</span>
									<span class="field_value">${userInfo.email}</span>
								</td>
								<td>
									<span class="field_label">Skype ID</span>
									<span class="field_value">${userInfo.skypeId}</span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label">手机号码</span>
									<span class="field_value">${userInfo.mobileNumber}</span>
								</td>
								<td>
									<span class="field_label">分机号码</span>
									<span class="field_value">${userInfo.extensionNumber}</span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label">员工ID</span>
									<span class="field_value">${userInfo.employeeId}</span>
								</td>
								<td>
									<span class="field_label">团队</span>
									<span class="field_value">${userInfo.organization}</span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label">职位</span>
									<span class="field_value">${userInfo.position}</span>
								</td>
								<td>
									<span class="field_label">办公位位置</span>
									<span class="field_value">${userInfo.location}</span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label">性别</span>
									<span class="field_value">${userInfo.gender}</span>
               					</td>
								<td>
									<span class="field_label">入职日期</span>
									<span class="field_value">${userInfo.joinDate}</span>
								</td>
							</tr>
							<tr>
								<form:form action="deleteUser.do"  onsubmit="return confirm('确定要删除该员工信息吗？')">
								<td colspan="2" align="right" style="height: 56px; padding-right: 5px;">
									<input type="hidden" name="id" value="${listPageInfo.id }">
									<input type="button" class="blueButton" value="修改" onclick="gotoUpdate()"> 
									<input type="submit" class="blueButton" value="删除">
									<input type="button" class="blueButton" value="取消" onclick="gotoList()">
								</td>
								</form:form>
							</tr>
						</table>
					</fieldset>
				</div>
			</section>
	</section>
	<jsp:include page="../common/footer.jsp" flush="false"/>
</body>
</html>
