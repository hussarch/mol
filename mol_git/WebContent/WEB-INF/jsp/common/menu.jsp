<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	request.setAttribute("index", request.getParameter("index"));
	String width = request.getParameter("width");
	if(width == null){
		width = "1000";
	}
	request.setAttribute("width", width);
%>
<style type="text/css">

.selected{
	opacity:1;
}

nav{
	height: 50px;
	width: ${width}px;
	margin: 0 auto;
}

.fancyNav{
	float: right;
	direction: ltr;
	list-style-type: decimal-leading-zero;
	-webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
	-webkit-margin-start: 0px;
	-webkit-margin-end: 0px;
	-webkit-padding-start: 0px;
}

.fancyNav li{
	/* Specifying a fallback color and we define CSS3 gradients for the major browsers: */
	
	background-color: #f0f0f0;
	background-image: -webkit-gradient(linear,left top, left bottom,from(#fefefe), color-stop(0.5,#f0f0f0), color-stop(0.51, #e6e6e6));
	background-image: -moz-linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
	background-image: -o-linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
	background-image: -ms-linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
	background-image: linear-gradient(#fefefe 0%, #f0f0f0 50%, #e6e6e6 51%);
	
	border-right: 1px solid rgba(9, 9, 9, 0.125);
	
	/* Adding a 1px inset highlight for a more polished efect: */
	
	box-shadow: 1px -1px 0 rgba(255, 255, 255, 0.6) inset;
	-moz-box-shadow: 1px -1px 0 rgba(255, 255, 255, 0.6) inset;
	-webkit-box-shadow: 1px -1px 0 rgba(255, 255, 255, 0.6) inset;
	
	position:relative;
	
	float: left;
	list-style: none;
}

.fancyNav li:after{

	/* This creates a pseudo element inslide each LI */	
	
	content:'.';
	text-indent:-9999px;
	overflow:hidden;
	position:absolute;
	width:100%;
	height:100%;
	top:0;
	left:0;
	z-index:1;
	opacity:0;
	
	/* Gradients! */
	
	background-image:-webkit-gradient(linear, left top, right top, from(rgba(168,168,168,0.5)),color-stop(0.5,rgba(168,168,168,0)), to(rgba(168,168,168,0.5)));
	background-image:-moz-linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
	background-image:-o-linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
	background-image:-ms-linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
	background-image:linear-gradient(left, rgba(168,168,168,0.5), rgba(168,168,168,0) 50%, rgba(168,168,168,0.5));
	
	/* Creating borders with box-shadow. Useful, as they don't affect the size of the element. */
	
	box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff,1px 0 0 #a3a3a3,2px 0 0 #fff;
	-moz-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff,1px 0 0 #a3a3a3,2px 0 0 #fff;
	-webkit-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff,1px 0 0 #a3a3a3,2px 0 0 #fff;
	
	/* This will create a smooth transition for the opacity property */
	
	-moz-transition:0.25s all;
	-webkit-transition:0.25s all;
	-o-transition:0.25s all;
	transition:0.25s all;
}

/* Treating the first LI and li:after elements separately */

.fancyNav li:first-child{
	border-radius: 4px 0 0 4px;
}

.fancyNav li:first-child:after,
.fancyNav li.selected:first-child:after{
	box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
	-moz-box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
	-webkit-box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
	
	border-radius:4px 0 0 4px;
}

.fancyNav li:last-child{
	border-radius: 0 4px 4px 0;
}

/* Treating the last LI and li:after elements separately */

.fancyNav li:last-child:after,
.fancyNav li.selected:last-child:after{
	box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
	-moz-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
	-webkit-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
	border-radius:0 4px 4px 0;
}

.fancyNav li:hover:after,
.fancyNav li.selected:after,
.fancyNav li:target:after,
.fancyNav li:hover>ul 
{
	/* This property triggers the CSS3 transition */
	opacity:1;
}


.fancyNav li.selected:hover:after,
.fancyNav li:target:hover:after{
	opacity:1 !important;
}

.fancyNav:hover li.selected:after,
.fancyNav:hover li:target:after{
	/* Hides the targeted li when we are hovering on the UL */
	opacity:0;
}



/* Styling the anchor elements */

.fancyNav li a{
	color: #5d5d5d;
	display: inline-block;
	font: 12px/1 Lobster,Arial,sans-serif;
	padding: 10px 20px 10px;
	position: relative;
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.6);
	z-index:2;
	text-decoration:none !important;
	white-space:nowrap;
}

a, a:visited {
	text-decoration:none;
	outline:none;
	color:#54a6de;
}

a:hover{
	text-decoration:underline;
}

.rightHeader{
	display: inline-block;
	float: left;
	width: 300px;
	height: 40px;
}

.rightHeader span{
	display: block;
	margin-top: 5px;
	margin-left: 0px;
}


.fancyNav ul {
	position: absolute;
	top: 32px;
	left: 0;
	background: #f0f0f0;
	z-index:0;
	-webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
	-webkit-margin-start: 0px;
	-webkit-margin-end: 0px;
	-webkit-padding-start: 0px;
	visibility: hidden;
}

.fancyNav ul li {
	height: 0;
	padding: 0;
	height: 32px;
	display：none;
}

.fancyNav li:hover>ul li {
	visibility: visible;
	padding: 0;
	width: 100%;
}

.fancyNav ul li a {
	border: none;
	margin: 0 auto;
	position: relative;
}

.fancyNav ul li:first-child{
	border-radius: 4px 4px 0px 0px;
}

.fancyNav ul li:first-child:after,
.fancyNav ul li.selected:first-child:after{
	box-shadow: 1px 0 0 #a3a3a3,2px 0 0 #fff;
	-moz-box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
	-webkit-box-shadow:1px 0 0 #a3a3a3,2px 0 0 #fff;
	border-radius: 4px 4px 0px 0px;
}

.fancyNav ul li:last-child{
	border-radius: 0 0 4px 4px;
}

/* Treating the last LI and li:after elements separately */

.fancyNav ul li:last-child:after,
.fancyNav ul li.selected:last-child:after{
	box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
	-moz-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
	-webkit-box-shadow:-1px 0 0 #a3a3a3,-2px 0 0 #fff;
	border-radius: 0 0 4px 4px;
}
	
</style>


<script type="text/javascript">
function startOpration(menu){
	var src = '';
	if('am' == menu){
		src = "<spring:url value='/massageBooking/current.do'/>";
	}else if('hys' == menu){
		alert("未实现");
	}else if('cx' == menu){
		src = "<spring:url value='/admin/userList.do'/>";
	}else if('qj' == menu){
		alert("未实现");
	}else if('gr' == menu){
		alert("未实现");
	}else if('ma' == menu){
		alert("未实现");
	}else if('ma' == menu){
		alert("未实现");
	}else if('exist' == menu){
		src = "<spring:url value='/user/logOut.do'/>";
	}else if('yg' == menu){
		src = "<spring:url value='/admin/userList.do'/>";
	}else if('manager' == menu){
		src = "<spring:url value='/admin/managerList.do'/>";
	}else if('org' == menu){
		src = "<spring:url value='/admin/orgList.do'/>";
	}else if('gjgl' == menu){
		alert("未实现");
	}
	if(src != ''){
		window.location = src;
	}
}

</script>

<nav>
	<section class="rightHeader">
		<span>欢迎！${ sessionScope.current_user.title}</span>
	</section>
    <ul class="fancyNav">
        <li id="order_msj" <c:if test="${index == 1}">class="selected"</c:if>><a href="#order_msj" onclick="startOpration('am')">按摩预定</a></li>
        <li id="order_meetingroom" <c:if test="${index == 2}">class="selected"</c:if>><a href="#order_meetingroom" onclick="startOpration('hys')">会议室预定</a></li>
        <li id="query" <c:if test="${index == 3}">class="selected"</c:if>><a href="#query" onclick="startOpration('cx')">查询</a></li>
        <li id="ask4leave" <c:if test="${index == 4}">class="selected"</c:if>><a href="#ask4leave" onclick="startOpration('qj')">请假</a></li>
         <li id="super_management" <c:if test="${index == 6}">class="selected"</c:if>><a href="#contact">管理员</a>
            <ul>
                <li><a href="#super_management" onclick="startOpration('yg')">员工管理</a></li>
                <li><a href="#super_management" onclick="startOpration('org')">组织部门管理</a></li>
                <li><a href="#super_management" onclick="startOpration('manager')">负责人管理</a></li>
                <li><a href="#super_management" onclick="startOpration('qjgl')">请假管理</a></li>
            </ul>
        </li>
        <li id="setting" <c:if test="${index == 5}">class="selected"</c:if>><a href="#contact">设置</a>
        	<ul>
        		<li><a href="#setting" onclick="startOpration('gr')">个人信息</a></li>
        		<li><a href="#setting" onclick="startOpration('ma')">密码重置</a></li>
        		<li><a href="#setting" onclick="startOpration('exist')">退出</a></li>
        	</ul>
        </li>
        <li id="other" <c:if test="${index == 7}">class="selected"</c:if>><a href="#services" onclick="startOpration('about')">关于</a></li>
    </ul>
</nav>