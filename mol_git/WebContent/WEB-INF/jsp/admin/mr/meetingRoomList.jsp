<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="hassar" uri="/WEB-INF/tlds/hussar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会议室列表</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
<script type="text/javascript">
	function intoCreate(){
		window.location ='<spring:url value="/admin/mr/into.do"/>';
	}
	
	function startOperation(itemId, isUpdate){
		$('#itemId').val(itemId);
		$('#operationType').val(isUpdate);
		$('#operationForm').submit();
	}
	
</script>
</head>
<body>
	<jsp:include page="../../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="910" name="width"/>
	</jsp:include>
    <section style="width: 910px;">
        <span id="msg" style="color: blue; display: block;">${msg }&nbsp; </span>
        <div>
        	<div style="float: right;">
        		<input type="button" onclick="intoCreate()" value="新增加会议室">
        	</div>
        </div>
        <form:form id="operationForm" commandName="listPageInfo" action="into.do" method="post">
        <input type="hidden" id="itemId" name="id"/>
        <form:hidden path="page"/>
        <input type="hidden" id="operationType" name="type"/>
        <table border="1" style="width: 100%">
            <thead>
                <tr style="text-align: center;font-weight: bold;">
                	<td width="200px">名称</td>
                    <td width="200px">Name</td>
                    <td>位置</td>
                    <td>座位数量</td>
                    <td width="80px">操作</td>
                </tr>
            </thead>
            <c:forEach items="${list}" var="item" varStatus="status"> 
            <tr>
                <td>${item.name}</td>
                <td>${item.zhName}</td>
                <td>${item.location}</td>
                <td>${item.seatNumber}</td>
                <td align="center">
                	<span><a href="#" onclick="startOperation(${item.id}, 'view')">查看</a></span>
                	<span><a href="#" onclick="startOperation(${item.id}, 'update')">修改</a></span>
                </td>
            </tr>    
            </c:forEach>
        </table>
        </form:form>
        <hassar:pagingCtl url="/mol/admin/mr/list.do" pagingCountBean="${pageInfo }"/>
    </section>
    <jsp:include page="../../common/footer.jsp" flush="false"/>
</body>
</html>