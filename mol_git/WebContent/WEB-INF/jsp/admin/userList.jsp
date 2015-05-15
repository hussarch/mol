<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="hassar" uri="/WEB-INF/tlds/hussar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
<script type="text/javascript">
	function intoRegist(){
		window.location ='<spring:url value="/user/intoRegist.do"/>';
	}
	
	
	function startOperation(itemId, isUpdate){
		$('#id_' + itemId).attr("checked", true);;
		$('#operationFlag').val(isUpdate);
		$('#operationForm').submit();
	}
	
	
	function startDelete(){
		if(validateSelect() && confirm('确定要删除该员工信息吗？')){
			$("#operationForm").attr("action", "deleteUser.do");
			$('#operationForm').submit();
		}
	}
	
	function intoSetPosition(){
		if(validateSelect()){
			$('#operationFlag').val("position");
			$('#operationForm').submit();
		}
	}
	
	function validateSelect(){
		var itemId = $("input:radio:checked").val();
		if(itemId == null){
			showMsg("请先选择一位员工");
			return false;
		}else{
			return true;
		}
	}
	
	
	function showMsg(msg){
		$("#msg").html(msg);
	}
	
	
</script>
</head>
<body>
	<div>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="6" name="index"/>
		<jsp:param value="1110" name="width"/>
	</jsp:include>
    <section style="width: 1110px;">
        <form action="<spring:url value='/admin/userList.do'/>" method="post">
        <span id="msg" style="color: blue; display: block;">${msg }&nbsp; </span>
        <div>
        	<div style="float: left;">
        		搜索：<input name="condition" placeholder="中英文名、邮箱或者skype ID模糊查询" value="${condition }" style="width: 260px; height:18px; margin-right: 10px;"><button type="submit">确定</button>
        	</div>
        	<div style="float: right;">
        		<input type="button" onclick="intoRegist()" value="注册员工">
        		<input type="button" onclick="startDelete()" value="删除">
        		<input type="button" onclick="intoSetPosition()" value="设置职务">
        	</div>
        </div>
        </form>
        <form:form id="operationForm" commandName="listPageInfo" name="operationForm" action="operation.do" method="post">
        <form:hidden path="condition"/>
        <form:hidden path="page"/>
        <form:hidden id="operationFlag" path="flag"/>
        <table border="1" style="width: 100%">
            <thead>
                <tr style="text-align: center;font-weight: bold;">
                	<td width="40px">选择</td>
                    <td width="70px">姓名</td>
                    <td width="140px">英文名</td>
                    <td width="240px">邮箱</td>
                    <td width="240px">Skype ID</td>
                    <td width="110px">手机号码</td>
                    <td>部门</td>
                    <td width="100px">操作</td>
                </tr>
            </thead>
            <c:forEach items="${userList}" var="item" varStatus="status"> 
            <tr>
            	<td align="center"><form:radiobutton path="id" value="${item.id}" id="id_${status.count}"/> </td>
                <td>${item.name}</td>
                <td>${item.englishName}</td>
                <td>${item.email}</td>
                <td>${item.skypeId}</td>
                <td>${item.mobileNumber}</td>
                <td>${item.organization.name}</td>
                <td align="center">
                	<span><a href="#" onclick="startOperation(${status.count}, 'view')">查看</a></span>
                	<span><a href="#" onclick="startOperation(${status.count}, 'update')">修改</a></span>
                </td>
            </tr>    
            </c:forEach>
        </table>
        </form:form>
        <hassar:pagingCtl url="/mol/admin/userList.do" pagingCountBean="${pageInfo }" condition="${condition }"/>
    </section>
    <jsp:include page="../common/footer.jsp" flush="false"/>
	</div>
</body>
</html>