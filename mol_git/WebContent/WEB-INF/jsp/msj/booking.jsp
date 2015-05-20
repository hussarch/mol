<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="date" content="2013-10-23">
<title>按摩预订</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">

<script type="text/javascript">

	function trOnMouseOverOut(index, flag){
		var button = $("#orderButton_" + index);
		$("#msg").html(event.srcElement.tagName + " - " + flag);
		if(!button){
			return;
		}
		var tagName = event.srcElement.tagName;
		if(flag || tagName == 'INPUT'){
			//button.show();
			button.removeAttr("disabled");
		}else{
			//button.hide();
			button.attr("disabled", "disabled");
		}
	}
	
	var refreshCurrent = function(){
	    $.post("<spring:url value='/massageBooking/current.do'/>",
	    {
			msg:"预订成功"
	    });
	};
	
	function submitOrder(index, flag){
		$.ajax({  
            type:"POST",   //http请求方式  
            url:"<spring:url value='/massageBooking/setOrder.do'/>", //发送给服务器的url  
            data:"orderNumber="+index + "&flag=" + flag, //发送给服务器的参数  
            dataType:"json",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)  
            complete:function(msg) {
                if (msg.responseText == 'true') {
                	if(flag){
                		submitForm("预定成功！");
                	}else{
                		submitForm("取消操作成功！");
                	}
                }else{
                	$("#msg").css("color", "red");
                	$("#msg").html("该时间段已被预订，请选择其它时间段！");
                	$("#orderButton_" + index).attr("disabled", "disabled");
                }
            }//定义交互完成,并且服务器正确返回数据时调用回调函数   
        });
	}
	
	function submitForm(value){
		$('#submitForm').attr("action", "<spring:url value='/massageBooking/current.do'/>");
		$('#param').val(value);
		$('#submitForm').submit();
		return false;
    }
	
</script>
</head>
<body>
	
<%-- 	<%@ include file="../common/menu.jsp" %>  --%>
	<c:if test="${sessionScope.current_user.name != null}">
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="1" name="index"/>
	</jsp:include>
	</c:if>
    <section>
    	<c:if test="${ sessionScope.current_user.name == null}">
    	<div style="width: 100%; height: 24px; margin: 10px 0;">
            <c:choose>
                <c:when test="${ sessionScope.current_user.name == null}">
                    <section style="width: 50%; float: left;">还没有用户？请先 <a href="<spring:url value='/user/intoRegist.do'/>">注册</a></section>
                    <section style="width: 50%; float: right; text-align: right;">预订/取消预订？请先 <a href="<spring:url value='/user/intoLogin.do'/>">登陆</a></section>
                </c:when>
            </c:choose>
<!--     		<div style="width: 100%; height: 1px; border-bottom: 1px #333 solid; display: marker;"></div> -->
    	</div>
    	</c:if>
        <div style="width: 100%; display: block; height: 22px;">
           <span style="color: maroon; display: inline-block; float: left;">本周按摩时间为：${orderDate }</span>
           <form:form modelAttribute="orderDay" id="submitForm" method="post" action="setOrderDay.do">
           <c:if test="${orderDateList != null}">
           <span style="display: inline-block; float: right;">重新选择按摩时间：
        	 	<form:select path="value" items="${orderDateList}" itemLabel="name" itemValue="value" ></form:select>
                <button type="submit" onsubmit="return confirm('您确定按摩时间修改吗?');">确定</button>
        	</span>
        	</c:if>
	        <input type="hidden" id="param" name="msg">
	        </form:form>
	         
        </div>
        <div style="width: 100%; display: block; height: 22px; margin: 0;">
        	  <span id="msg" style="color: blue; display: block;">${msg }</span>
        </div>
        <table border="1" style="width: 600px;">
            <thead>
                <tr style="text-align: center;font-weight: bold;">
                    <td>序号</td>
                    <td>开始时间</td>
                    <td width="50%">当前预订人</td>
                    <td style="width: 100px;">操作</td>
                </tr>
            </thead>
            <c:forEach items="${bookingList}" var="item" varStatus="status"> 
            <tr style="background-color: ${item.trColor }">
                <td>${item.bookingEntity.orderNumber}</td>
                <td <c:if test="${status.index%2==0}">align="right"</c:if>>${item.startTime}</td>
                <td align="center" <c:if test="${item.currentLoginUserFlag}">style="font-weight: bold;"</c:if>>
                    ${item.status}
                </td>
                <td align="center">
                	<input type="button" id="orderButton_${status.index}" value="${item.operationName }" 
                                        onclick="return submitOrder(${item.bookingEntity.orderNumber}, ${item.setFlag });"
                                        <c:if test="${item.disableFlag}">disabled="disabled"</c:if>
                                        onmouseover="this.style.color='red'" 
                                        onmouseout="this.style.color='black'">
                
                </td>
            </tr>    
            </c:forEach>
        </table>
    </section>
    <jsp:include page="../common/footer.jsp" flush="true"/>
</body>
</html>
