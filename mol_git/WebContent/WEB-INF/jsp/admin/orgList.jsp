<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="hassar" uri="/WEB-INF/tlds/hussar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门列表</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
<script type="text/javascript">
	function intoCreate(){
		window.location ='<spring:url value="/admin/orgOperation.do?operationType=create"/>';
	}
	
	function startOperation(itemId, isUpdate){
		$('#itemId').val(itemId);
		$('#operationType').val(isUpdate);
		$('#operationForm').submit();
	}
	
</script>
</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="1110" name="width"/>
	</jsp:include>
    <section style="width: 1100px;">
        <span id="msg" style="color: blue; display: block;">${msg }&nbsp; </span>
        <div>
        	<div style="float: right;">
        		<input type="button" onclick="intoCreate()" value="新建部门">
        	</div>
        </div>
        <form:form id="operationForm" commandName="listPageInfo" action="orgOperation.do" method="post">
        <input type="hidden" id="itemId" name="id"/>
        <form:hidden path="page"/>
        <input type="hidden" id="operationType" name="operationType"/>
        <table border="1" style="width: 100%">
            <thead>
                <tr style="text-align: center;font-weight: bold;">
                    <td>部门名</td>
                    <td>Department Name</td>
                    <td>负责人</td>
                    <td>上级部门</td>
                    <td>操作</td>
                </tr>
            </thead>
            <c:forEach items="${orgList}" var="item" varStatus="status"> 
            <tr>
                <td>${item.zhName}</td>
                <td>${item.name}</td>
                <td>${item.managerName}</td>
                <td>${item.superOrganization.zhName}</td>
                <td align="center">
                	<span><a href="#" onclick="startOperation(${item.id}, 'view')">查看</a></span>
                	<span><a href="#" onclick="startOperation(${item.id}, 'update')">修改</a></span>
                </td>
            </tr>    
            </c:forEach>
        </table>
        </form:form>
        <hassar:pagingCtl url="/mol/admin/orgList.do" pagingCountBean="${pageInfo }"/>
    </section>
    <jsp:include page="../common/footer.jsp" flush="false"/>
</body>
</html>