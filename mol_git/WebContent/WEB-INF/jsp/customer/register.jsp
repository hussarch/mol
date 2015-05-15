<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<title>会员注册_租车</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="旅游路线，小轿车、商务车、中巴、大巴车车租赁" name="keywords">
<meta content="四川全境旅游路线介绍，并且提供 相关路线租车服务，提供优秀景区经验多年经验司机服务" name="description">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css"></link>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"></link>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/messages_zh.js" type="text/javascript"></script>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<script type="text/javascript">

//手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");   


// 电话号码验证   
jQuery.validator.addMethod("phone", function(value, element) {
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

$().ready(function() {
	// validate the comment form when it is submitted
	$("#registerForm").validate({
		
		rules: {
			userName: {
				required: true,
				maxlength: 18
			},
			mobilePhoneNumber: {
				required: true,
				mobile: true
			},
			password: {
				required: true,
				minlength: 6,
				maxlength: 18
			},
			repetPassword: {
				equalTo: "#passwordId"
			},
			email: {
				required: true,
				email:	  true
			},
			chkSelected: "required"
		},
		messages: {
			chkSelected: "注册前请先接受该协议"
		}
		
	});
	
});
</script>

<style>
    
    .error {
        color: red;
        font-style: italic;
        padding-left: 15px;
    }
</style>

</head>
<body>
    <!--header-->
    <div class="logo-head">
        <div class="logo-box">
            <h1>
                <a name="header-logo" title="欢迎来到租车">租车</a>
            </h1>
        </div>
    </div>
    <div class="head-container">
        <div class="header center">
            <div class="login-tab">
                <div class="tab-line"></div>
                <div class="tab-rg">
                    <span class="tab-lg-center" style="color: #EB5904;">会员注册</span>
                </div>
            </div>
            <div class="login-tab">
                <div class="tab-lg-none">
                </div>
                  <div class="tab-rg-no-border">
                    <div class="tab-lg-center"><a href="intoLogin.do">我要登录</a></div>
                </div>
            </div>
        </div>
    </div>
    <!--header-->
    <form:form modelAttribute="customer" id="registerForm" cssClass="cmxform" method="post" action="regist.do">
        <div class="main-wrap">
            <div class="main-container center">
                <div class="register-help">
                    <span>会员注册:</span>
                </div>
                <div class="register-container-shadow1">
                    <div class="register-container-shadow2">
                        <div class="register-container">
                            <div class="register-form">
                                <ul>
                                    <li class="register-helper">带<span>*</span>表示必填项。
                                    </li>
                                </ul>
                                <ul class="get-register-form" style="display:;">
                                    <li>
                                        <span class="register-info"> 
                                            <span class="red-character ">*</span>用户名称:
                                        </span>
                                        <form:input id="userNameId" path="userName" /> 
                                        <span class="register-info-help">最多18个字符，请使用字母、数字、符号或者组合</span>
                                    </li>
                                    <li>
                                        <span class="register-info"> 
                                            <span class="red-character ">*</span>手机号码:
                                        </span>
                                        <form:input id="mobilePhoneNoId" path="mobilePhoneNumber" /> 
                                        <span class="register-info-help">注册完成后，可以以该号码登录</span>
                                    </li>
                                    <li>
                                        <span class="register-info">
                                            <span class="red-character ">*</span>设置密码:
                                        </span> 
                                        <form:input id="passwordId" path="password" type="password" /> 
                                        <span class="register-info-help">6-18个字符，请使用字母、数字、符号或者组合</span>
                                    </li>
                                    <li>
                                        <span class="register-info"> 
                                            <span class="red-character ">*</span>确认密码:
                                        </span> 
                                        <input id="repetPasswordId" name="repetPassword" type="password">
                                    </li>
                                    <li>
                                        <span class="register-info"> 
                                            <span class="red-character "></span>真实姓名:
                                        </span> 
                                        <form:input id="txtCellPhone" path="realName" tabindex="1" type="text"/> 
                                    <li>
                                        <span class="register-info"> 
                                            <span class="red-character ">*</span>常用邮箱:</span> 
                                            <form:input id="txtEmailAddress" maxlength="50" path="email"/>
                                            <span class="register-info-help">供找回密码和登录使用</span>
                                    </li>
                                    <li>
                                        <span class="register-info"></span> 
                                        <input class="rgsub-btn-gray" type="submit" value="确 定" id="btnSubmit" tabindex="10"> 
                                        <span class="check-agreement"> 
                                            <input type="checkbox" value="" tabindex="11" id="chkSelected" name="chkSelected" checked="checked">同意《租车会员注册协议》
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
     </form:form>


    <!--foot-->
    <div class="footer center">
        <div class="footer-left">
            <div class="comm-footer">
                <a href="http://www.1hai.cn/aboutus.aspx" target="_blank">关于我们</a> <a href="http://www.1hai.cn/1hai_union.aspx" target="_blank">
                    加盟合作</a> <a href="http://www.1hai.cn/job/index.aspx" target="_blank">招募英才</a> <a
                    href="http://www.1hai.cn/help/index_help.aspx?from=foot" target="_blank"> 帮助中心</a> <a href="http://www.1hai.cn/help/ysbh.aspx"
                    target="_blank">隐私保护</a> <a href="http://www.1hai.cn/advice.aspx" target="_blank">建议专区</a> <a class="footer-noline"
                    href="http://www.1hai.cn/contactus.aspx" target="_blank"> 联系我们</a>
            </div>
            <div class="comm-footer">Copyright 2013 - 2014 上海智能信息技术服务有限公司 沪ICP备1111号</div>
        </div>

    </div>


</body>
</html>