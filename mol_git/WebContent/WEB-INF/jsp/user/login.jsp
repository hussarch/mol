<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="date" content="2013-10-23">
<title>登录</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css">
<style type="text/css">

.wrap {
    margin: 150px auto;
    width: 380px;
    overflow: hidden;
    float: none;
    background-color: #000000;
}

fieldset input[type=submit] {
    text-align: center;
    padding: 2px 20px;
    line-height: 2em;
    border: 1px solid #113DEE;
    border-radius: 3px;
    background: -webkit-gradient(linear, left top, left 24, from(#113DEE),
        color-stop(0%, #0938F7), to(#113DEE) );
    background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
    background: -o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800',
        endColorstr='#FF6900' );
    -ms-filter:
        "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')";
    height: 30px;
    cursor: pointer;
    letter-spacing: 4px;
    margin-left: 60px;
    color: #FFF;
    font-weight: bold;
}
</style>


<!--为了让IE支持HTML5元素，做如下操作：-->
<!--[if IE]> 

<script type="text/javascript"> 

document.createElement("section"); 

document.createElement("header"); 

document.createElement("footer"); 

</script> 

<![endif]-->
</head>
<body>
    <section class="wrap">
        <form action="login.do" method="post">
            <section class="loginForm">
                <h1>登录</h1>
                <div class="loginForm_content">
                    <fieldset>
                        <span style="margin: 10px 10px 0;color: red">${msg}</span>
                        <div class="inputWrap">
                            <input type="text" name="name" placeholder="姓名/邮箱/Skype ID/手机号/员工ID" autofocus required/>
                        </div>
                        <div class="inputWrap">
                            <input type="password" name="password" placeholder="请输入密码" required/>
                        </div>
                    </fieldset>
                    <fieldset>
                        <input type="checkbox" name="isRemember"/> <span>记住用户名</span> <a href="#">忘记密码？</a> <a href="intoRegist.do">注册</a> 
                        <input type="submit" value="登录"/>
                    </fieldset>
                </div>
            </section>
        </form>
    </section>
    <footer>
    <div class="footer-left">
            <div class="comm-footer">
		        <section>
		            Mavenir ShangHai Office Service on Line © hussar HTML5
		        </section>
		        <section>
		                    请使用Cherome登录
		        </section>
        	</div>
         </div>
    </footer>
</body>
</html>
