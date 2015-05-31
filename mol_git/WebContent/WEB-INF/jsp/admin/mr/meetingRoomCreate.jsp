<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>创建会议室</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/conf.setting.css">

<script type="text/javascript">  

function checkIsExist(field, info) {
	var fieldValue = field.value;
	var fieldName = field.name;
	if(fieldValue == ''){
		return;
	}
	var idValue = '${orgInfo.id}';
	if(idValue == ''){
		idValue = -1;
	}
    $.ajax({  
        type:"POST",   //http请求方式  
        url:"<spring:url value='/admin/mr/validate.do'/>", //发送给服务器的url  
        data:"fieldName="+fieldName + "&value=" + fieldValue + "&id=" + idValue, //发送给服务器的参数  
        dataType:"json",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)  
        complete:function(msg) {
            if (msg.responseText != 'false') {  
            	showErrorMsg(fieldName, info);
            }else{
            	showErrorMsg(fieldName, "");
            }
        }//定义交互完成,并且服务器正确返回数据时调用回调函数   
    });  
}  

function showErrorMsg(fieldName, info){
	var inputField = "input[name='"+fieldName+"']";
	if(inputField){
		var field = document.querySelector(inputField);
		field.setCustomValidity(info);
	}
}
	
	
	
</script>
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
	<jsp:include page="../../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="910" name="width"/>
	</jsp:include>
	<section style="height: 278px; width: 510px; margin-top: 80px;" class="content">
		<form:form commandName="entity" action="create.do" id="creatForm" method="post">
			<form:hidden path="id"/>
			<section style="height: 100%; width: 100%;">
				<div>
					<fieldset>
						<span style="margin: 10px 10px 0; color: red" id="errorMsg">${msg}</span>
						<table style="width: 100%">
							<tr>
								<td>
									<span class="field_label" style="width:170px;">会议室名称<span class="required">*</span></span>
									<form:input path="name" autofocus="true" required="true" onblur="checkIsExist(this, '该名称已使用')" placeholder="请输入中文名称"/>
								</td>
							</tr>
							<tr>
								<td style="display: inline;">
									<span class="field_label" style="width:170px;">Meeting Room Name<span class="required">*</span></span>
									<form:input path="zhName" required="true" onblur="checkIsExist(this, '该名称已使用')" placeholder="请输入英文名称" maxlength="64"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label" style="width:170px;">地点-位置<span class="required">*</span></span>
                                    <form:input path="location" required="true" onblur="checkIsExist(this, '该地点已使用')" placeholder="请输入英文名称" maxlength="64"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="field_label" style="width:170px;">座位数量<span class="required"></span></span>
                                    <form:input path="seatNumber" pattern="^[0-9]*[1-9][0-9]*$"/>
								</td>
							</tr>
							<tr>
								<td align="right" style="padding-right: 18px; height: 60px;">
									<input type="button" class="blueButton" value="取消" onclick="window.location = '<spring:url value='/admin/mr/list.do'/>'">
									<input type="submit" class="blueButton" value="确定">
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</section>
		</form:form>
	</section>
	<jsp:include page="../../common/footer.jsp" flush="false"/>
</body>
</html>
