<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception,java.io.*" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>
    <h1>出错了...</h1>
    <%
    	Exception e = (Exception) request.getAttribute("exception");
    
        ByteArrayOutputStream ostr = new ByteArrayOutputStream();
        //把错误堆栈储存到流中
        e.printStackTrace(new PrintStream(ostr));
        //打印....
        out.println(ostr.toString());
    %>
</body>
</html>
