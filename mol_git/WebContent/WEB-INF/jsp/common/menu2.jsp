<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">

nav {
	height: 50px;
	width: 1000px;
	margin: 0 auto;
}

.rightHeader {
	display: inline-block;
	float: left;
	width: 300px;
	height: inherit;
}

.rightHeader span {
	display: block;
	margin-top: 15px;
	margin-left: 15px;
}

.menu,.menu ul,.menu li,.menu a {
	margin: 0;
	padding: 0;
	border: none;
	outline: none;
}

.menu {
	height: 40px;
	background: #4c4e5a;
	background: -webkit-linear-gradient(top, #4c4e5a 0%, #2c2d33 100%);
	background: -moz-linear-gradient(top, #4c4e5a 0%, #2c2d33 100%);
	background: -o-linear-gradient(top, #4c4e5a 0%, #2c2d33 100%);
	background: -ms-linear-gradient(top, #4c4e5a 0%, #2c2d33 100%);
	background: linear-gradient(top, #4c4e5a 0%, #2c2d33 100%);
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

.menu li {
	position: relative;
	list-style: none;
	float: left;
	display: block;
	height: 40px;
}

.menu li a {
	display: block;
	padding: 0 14px;
	margin: 6px 0;
	line-height: 28px;
	text-decoration: none;
	border-left: 1px solid #393942;
	border-right: 1px solid #4f5058;
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 13px;
	color: #f3f3f3;
	text-shadow: 1px 1px 1px rgba(0, 0, 0, .6);
	-webkit-transition: color .2s ease-in-out;
	-moz-transition: color .2s ease-in-out;
	-o-transition: color .2s ease-in-out;
	-ms-transition: color .2s ease-in-out;
	transition: color .2s ease-in-out;
}

.menu li:first-child a {
	border-left: none;
}

.menu li:last-child a {
	border-right: none;
}

.menu li:hover>a {
	color: #8fde62;
}

.menu ul {
	position: absolute;
	top: 40px;
	left: 0;
	opacity: 0;
	background: #1f2024;
	-webkit-border-radius: 0 0 5px 5px;
	-moz-border-radius: 0 0 5px 5px;
	border-radius: 0 0 5px 5px;
	-webkit-transition: opacity .25s ease .1s;
	-moz-transition: opacity .25s ease .1s;
	-o-transition: opacity .25s ease .1s;
	-ms-transition: opacity .25s ease .1s;
	transition: opacity .25s ease .1s;
}

.menu li:hover>ul {
	opacity: 1;
}

.menu ul li {
	height: 0;
	overflow: hidden;
	padding: 0;
	-webkit-transition: height .25s ease .1s;
	-moz-transition: height .25s ease .1s;
	-o-transition: height .25s ease .1s;
	-ms-transition: height .25s ease .1s;
	transition: height .25s ease .1s;
}

.menu li:hover>ul li {
	height: 36px;
	overflow: visible;
	padding: 0;
}

.menu ul li a {
	width: 80px;
	margin: 0;
	border: none;
	border-bottom: 1px solid #353539;
	text-align: left;
}

.menu ul li:last-child a {
	border: none;
}
</style>

<%
	request.setAttribute("index", request.getParameter("index"));
%>
<nav>
	<section class="rightHeader">
		<span>欢迎！</span>
	</section>
	<section style="float: right; direction: rtl;">
		<ul class="menu">
			<li><a href="#">按摩预定</a></li>
			<li><a href="#">会议室预定</a></li>
			<li><a href="#">找人</a></li>
			<li><a href="#">请假调休</a></li>
			<li><a href="#">设置</a>
				<ul>
					<li><a href="#">个人信息</a></li>
					<li><a href="#">密码重置</a></li>
				</ul></li>
			<li><a href="#">退出</a></li>
		</ul>
	</section>
</nav>