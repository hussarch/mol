<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>选择与会人员</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tree/style.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
<script src="<%=request.getContextPath()%>/js/tree/jstree.js"></script>

<script type="text/javascript">
$(function () {
	$('#jstree_members').jstree({ 'core' : {
	    'data' : [
	       ${selectMemberData}
	    ]
	},
	"plugins" : [ "wholerow", "checkbox",  "themes", "data","search" ,"ui"]
	});
});

	
</script>

</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="1" name="index"/>
		<jsp:param value="1110" name="width"/>
	</jsp:include>
	<section>
		 <div id="jstree_members"></div>
		 <div></div>
	</section>
	<jsp:include page="../common/footer.jsp" flush="true"/>
</body>
</html>