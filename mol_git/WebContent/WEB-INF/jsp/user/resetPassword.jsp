<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="date" content="2013-10-23">
<title>密码重置</title>
<link rel="stylesheet" href="css/user.css">
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
        <form action="#" method="post">
            <section class="loginForm">
                <h1>密码重置</h1>
                <div class="loginForm_content">
                    <fieldset>
                        <div class="inputWrap">
                            <input type="password" name="password" placeholder="请输入新密码" autofocus required>
                        </div>
                        <div class="inputWrap">
                            <input type="password" name="repetPassword" placeholder="请重新输入密码" required>
                        </div>
                    </fieldset>
                    <fieldset>
                        <input type="submit" value="确定">
                    </fieldset>
                </div>
            </section>
        </form>
    </section>
    <footer>
        <section>
            Mavenir ShangHai Office Service On Line © hussar HTML5
        </section>
        <section>
                请使用Cherome登录
        </section>
    </footer>
</body>
</html>
