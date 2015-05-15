<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>关于</title>
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
	<section style="height: 510px; width: 480px; margin-top: 80px;" class="content">
		<section style="height: 100%">
			<div>
				<fieldset>
                    <div>
                        <span>技术框架</span>
                        <ul>
                            <li>Java, Spring3, Spring MVC3, Hibernate3, Html5, CSS3, Jquery1.1</li>
                            <li>源代码地址： https://github.com/hussarch/mol.git</li>
                        </ul>
                    </div>
					<ul>
                        <li></li>
                    </ul>
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
