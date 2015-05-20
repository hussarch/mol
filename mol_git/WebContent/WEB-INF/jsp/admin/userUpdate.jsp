<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>用户注册</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/conf.setting.css">

<script type="text/javascript">  

        function check() {  
            if(!checkEmail()){
            	return false;
            }else if(!isPhoneTel()){
            	return false;
            }else{
            	return true;
            }
        }
        
        function checkEmail(){
        	var email = document.forms['registerForm'].email;
        	var index = email.value.indexOf("@");
    		if(email.value.substring(index) != "@mavenir.com"){
    			email.setCustomValidity("请使用公司邮箱(@mavenir.com)注册");  
                return false;  
    		}else{
    			email.setCustomValidity("");
    			return true;
    		}
        }
        
        function isPhoneTel(){
        	var phone = document.forms['registerForm'].mobileNumber;
            var reg = /^1[3|4|5|8]\d{9}$/;
            if(!reg.test(phone.value)){
            	phone.setCustomValidity("该号码不符合手机号码规范");  
                return false;
            }else{
            	phone.setCustomValidity("");
                return true;
            }
        }

        function checkIsExist(field, type, info) {
        	var fieldValue = field.value;
        	var fieldName = field.name;
        	if(fieldValue == ''){
        		return;
        	}
        	var idValue = '${userInfo.id}';
        	if(idValue == ''){
        		idValue = -1;
        	}
            $.ajax({  
                type:"POST",   //http请求方式  
                url:"<spring:url value='/user/validate.do'/>", //发送给服务器的url  
                data:"fieldName="+fieldName + "&type=" + type + "&value=" + fieldValue + "&id=" + idValue, //发送给服务器的参数  
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

</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="910" name="width"/>
	</jsp:include>
	<section style="height: 340px; width: 750px; margin-top: 80px;" class="content">
		<form:form commandName="userInfo" action="updateUser.do" id="updateUserForm" method="post" onsubmit="return check();">
			<form:hidden path="id"/>
			<section style="height: 100%">
				<div>
					<fieldset>
						<span style="margin: 10px 10px 0; color: red" id="errorMsg">${msg}</span>
						<table style="width: 100%">
							<tr>
								<td width="50%">
									<span class="field_label">姓名<span class="required">*</span></span><form:input path="name" autofocus="true" required="true" onblur="checkIsExist(this, 'string', '该名称已注册')" maxlength="32"/>
								</td>
								<td width="50%">
									<span class="field_label">英文名</span><form:input path="englishName" autofocus="true" maxlength="64"/>
								</td>
							</tr>
							<tr>
								<td><span class="field_label">邮箱<span class="required">*</span></span><form:input path="email" type="email" required="true" onblur="checkIsExist(this, 'string', '该邮箱已注册')" placeholder="请使用公司邮箱" maxlength="64"/></td>
								<td><span class="field_label">Skype ID<span class="required">*</span></span><form:input path="skypeId" required="true" onblur="checkIsExist(this, 'string', '该Skype ID已注册')" maxlength="32"/></td>
							</tr>
							<tr>
								<td><span class="field_label">手机号码<span class="required">*</span></span><form:input type="number" path="mobileNumber" pattern="^1\d{10}$" required="true" onblur="checkIsExist(this, 'string', '该手机号已注册')"/></td>
								<td><span class="field_label">分机号码</span><form:input type="number" path="extensionNumber"/></td>
							</tr>
							<tr>
								<td><span class="field_label">员工ID<span class="required">*</span></span><form:input type="number" path="employeeId" required="true" onblur="checkIsExist(this, 'int', '该员工ID已注册')"/></td>
								<td><span class="field_label">团队</span><form:select placeholder="" path="organization.id" items="${organizationList}"  itemLabel="fullName" itemValue="id"></form:select></td>
							</tr>
							<tr>
								<td>
                                    <span class="field_label">职位<span class="required">*</span></span><form:input path="position" required="true" maxlength="64" list="position_list"/>
                                    <datalist id="position_list">
                                        <option>软件开发工程师</option>
                                        <option>软件测试工程师</option>
                                        <option>管理人员</option>
                                        <option>人事</option>
                                        <option>财务</option>
                                        <option>行政</option>
                                        <option>IT维护</option>
                                        <option>软件配管</option>
                                        <option>项目经理</option>
                                        <option>文档开发</option>
                                    </datalist>
                                </td>
								<td><span class="field_label">办公位位置</span><form:input path="location" maxlength="64"/></td>
							</tr>
							<tr>
								<td><span class="field_label">性别</span>
									<form:radiobutton cssStyle="margin-left: 10px;" path="gender" value="MALE" label="男"/>  
               						<form:radiobutton path="gender" value="FEMALE" label="女"/></td>
								<td><span class="field_label">入职日期</span><form:input path="joinDate" type="date"/></td>
							</tr>
							<tr>
								<td colspan="2" align="right" style="padding-right: 31px; height: 60px;">
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
