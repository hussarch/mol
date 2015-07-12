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
var mytree;
$(function () {
	mytree = $('#jstree_members').jstree({ 
		'core' : {
			    'data' : [
			       ${selectMemberData}
			    ]
		},
		"types" : {
			'org' : {'icon' : '<%=request.getContextPath()%>/css/img/org.png'},
			'manager' : {'icon' : '<%=request.getContextPath()%>/css/img/leader.png'},
			'employee' : {'icon' : '<%=request.getContextPath()%>/css/img/customer.png'}
		},	
		"plugins" : [ "wholerow", "checkbox",  "themes", "data","search" ,"ui", "types"]
	}
	).on('changed.jstree', function (e, data) {
		if(data && data.selected && data.selected.length) {
			//data.instance.get_node("user:132").original
		}else {
			$('#data .content').hide();
			$('#data .default').html('Select a file from the tree.').show();
		}
	})
	;
});

	
</script>

</head>
<body style="width: 100%; height: 100%;">
	<jsp:include page="../common/menu.jsp" flush="true">
		<jsp:param value="1" name="index"/>
		<jsp:param value="1110" name="width"/>
	</jsp:include>
	<section>
		 <div id="jstree_members" style="width:80%; height:480px; overflow:auto;"></div>
		 <div></div>
	</section>
	<jsp:include page="../common/footer.jsp" flush="true"/>
</body>
</html>