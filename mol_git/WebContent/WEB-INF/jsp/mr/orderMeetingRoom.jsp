<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>会议室预定</title>
<link type="text/css" rel="stylesheet" href="../css/conf.setting.css">
<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
<style type="text/css">

.timeTitle{
	-webkit-padding-start: 0;
	padding-left: 106px;
}

.timeTitle li{
	list-style:none; /* 去掉ul前面的符号 */
    margin: 0px; /* 与外界元素的距离为0 */
    padding: 0px; /* 与内部元素的距离为0 */
	float: left;
	width: 50px;
}

.t_a{
	border-left: 1px solid blue; 
	font-weight: bold;
	height: 40px;
}

.t_b{
	border-left: 1px dotted #FFFFFF; 
	line-height:65px;
	height: 40px;
}	

table{
	font-family: verdana,arial,sans-serif;
	font-size:10px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table td, th{
	border-width: 1px;
	padding: 0px;
	border-style: solid;
	border-color: #999999;
	background-color: #ffffff;
	width: 50px;
}


table td:HOVER{
	background: #CAFF70;
}

.selected{
	background-color: #EEEE00;
	border-radius: 3px;
}

.dayTitle{
	-webkit-padding-start: 0;
	margin: 0;
	width: 100%;
	-webkit-margin-before: 0;
	-webkit-margin-after: 0;
	-webkit-margin-start: 0px;
	-webkit-margin-end: 0px;
	-webkit-padding-start: 0px;
}

.dayTitle li{
	list-style:none; /* 去掉ul前面的符号 */
    margin: 0px; /* 与外界元素的距离为0 */
    padding: 0px; /* 与内部元素的距离为0 */
	float: left;
	width: 90px;
	height: 28px;
	line-height: 28px;
	text-align: center;
	border-top-left-radius:5px;
	border-top-right-radius:5px;
	border-left: 1px solid #6A5ACD;
	border-right: 1px solid #6A5ACD;
	border-top: 1px solid #6A5ACD;
	border-bottom: 1px solid #6A5ACD;
	margin-bottom:-1px;
	margin-left:-1px;
	background-color: #E5E5E5;
	color: #828282;
}

.dayTitle li a{
	color: #FFF;
	text-decoration: none;
	width: 100%; 
	display:inline-block; 
}

#leo {  
	width: 200px;
    position: absolute;  
    border: 1px solid grey;  
    background: #FFFFE0;  
    border-radius: 3px;
}

th{
	font-weight: normal;
	font-size: 12px;
}

</style>

<script type="text/javascript">


$(function() {  
    x = 5;  
    y = 15;  
    $("#txt").hover(function(e) {  
        this.title = "";  
        var ndiv = "<div id='leo'>this a test in the page, we can show it now</div>";  
        $("body").append(ndiv);  
        $("#leo").css({  
            "top" : (e.pageY + y) + "px",  
            "left" : (e.pageX + x) + "px"  
        }).show(2000);  
        $(this).mousemove(function(e) {  
            $("#leo").css({  
                "top" : (e.pageY + y) + "px",  
                "left" : (e.pageX + x) + "px"  
            }).show(1000);  
        });  
    }, function() {  
        $("#leo").remove();  
    });  
    
    var map = new Map();
    
    $("#meettingRoom tbody tr td").each(function(){
    	$(this).click(function(){
    		var key = getKey(this);
    		if(!map.get(key)){
    			
    		}else{
    			
    		}
    		map.put(getKey(this), true);
    		
    		$(this).css({"background-color" : "#5A5AAD"});
    	});
    	
    });
    
    function getKey(comp){
    	return comp.offsetLeft + "-" + comp.offsetTop;
    }
    
    
});



	
</script>

</head>
<body>
	<section style="width: 1080px; margin: 80px auto 0; padding: 0;">
		<ul class="dayTitle">
			<li>周一</li>
			<li>周二</li>
			<li style="border-bottom: 1px solid #FFFFFF; background-color: #FFFFFF; color: #000;">周三</li>
			<li style="background-color: #7fb80e; color: #FFF"><a href="#">周四</a></li>
			<li style="background-color: #7fb80e; color: #FFF"><a href="#">周五</a></li>
			<li style="background-color: #7fb80e; color: #FFF"><a href="#">周六</a></li>
			<li style="background-color: #7fb80e; color: #FFF"><a href="#">周日</a></li>
		</ul>
		<span style="float: right; height: 28px;">
		日期：<input type="date" style="height: 24px; width: 145px;">
		</span>
	</section>	
	<section style="height: 610px; width: 1080px; margin: 0 auto; border-top-left-radius: 0;" class="content">
		<ul class="timeTitle">
			<li class="t_a">9:00</li><li class="t_b"><span style="margin-left:-11px;">9:30</span></li>
			<li class="t_a">10:00</li><li class="t_b"><span style="margin-left:-18px;">10:30</span></li>
			<li class="t_a">11:00</li><li class="t_b"><span style="margin-left:-18px;">11:30</span></li>
			<li class="t_a">12:00</li><li class="t_b"><span style="margin-left:-18px;">12:30</span></li>
			<li class="t_a">13:00</li><li class="t_b"><span style="margin-left:-18px;">13:30</span></li>
			<li class="t_a">14:00</li><li class="t_b"><span style="margin-left:-18px;">14:30</span></li>
			<li class="t_a">15:00</li><li class="t_b"><span style="margin-left:-18px;">15:30</span></li>
			<li class="t_a">16:00</li><li class="t_b"><span style="margin-left:-18px;">16:30</span></li>
			<li class="t_a">17:00</li><li class="t_b"><span style="margin-left:-18px;">17:30</span></li>
			<li class="t_a" style="border-bottom: 0;">18:00</li>
		</ul>
		<table id="meettingRoom">
			<tr>
				<th style="width: 105px;"><span>会议室1</span></th>
				<td></td><td></td>
				<td></td><td></td>
				<td class="selected" id="txt"></td><td></td>
				<td></td><td></td>
				<td></td><td></td>
				<td></td><td></td>
				<td></td><td></td>
				<td></td><td></td>
				<td></td><td></td>
			</tr>
			<c:forEach items="${magagerList}" var="item" varStatus="status"> 
			<tr>
				<th style="width: 105px;" id="tr_${item.id }"><span>${item.name }</span></th>
				<c:out value=" ${item.tds } "  escapeXml="false" />
			</tr>
			</c:forEach>
		</table>
	</section>
	
</body>
</html>