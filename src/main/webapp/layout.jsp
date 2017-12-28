<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>layout.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<script type="text/javascript" src="utils/jquery.min.js"></script>
	<script type="text/javascript" src="utils/jquery.easyui.min.js"></script>
	<style type="text/css">
		a{
			text-decoration: none;
			color: blue;
		}
		#box{
			position:relative; 
			width:100%; 
			height:100%;
			border: 1px;
		}
		
		.pic{	
				position:absolute;
				top:0;
				left:0; 
				width:100%;
				height:100%;
				z-index:99;
				display:none;
				z-index:1;
			}
	</style>
	
	<script type="text/javascript">
	
			$(function(){
				$("#box .pic").eq(0).show();
				var i=0;
				function fad(){
					var m = i%4;
					var n = (i+1)%4;
					$("#box .pic").eq(m).fadeOut(2000);
					$("#box .pic").eq(n).fadeIn(2000);
					i++;
				};
				setInterval(fad,3000);
			});
		
	
		function urlClick(myTitle,myUrl){
			//判断title='学生管理'tabs是否存在
			var ifExist = $("#mytabs").tabs("exists",myTitle);
			if(!ifExist) {
				$("#mytabs").tabs("add",{
					title:myTitle,
					closable:true,
					content:'<iframe frameborder = 0 width="100%" height="100%" scrolling="no" src="'+myUrl+'"></iframe>',
				});
			} else {
				$("#mytabs").tabs("select",myTitle);
			}
		}
	</script>
  </head>
  
  <body style="margin: 1px;">
	<div class="easyui-layout" style="width:100%;height:100%;">
		<!-- 上北,只设置高度,不会设置宽度 -->
		<div data-options="region:'north'" style="height:15%">
			<img src="image/1.jpg" style="width: 100%;height: 100%">
		</div>
			<div data-options="region:'west',split:true" title="导航菜单" style="width:15%;">
					<div class="easyui-accordion" style="width:500px;height:300px;">
				<div title="权限管理" style="overflow:auto;padding:10px;">
					<c:forEach var="menu" items="${requestScope.queryMenuByUser }">
						<a href="javascript:urlClick('${menu.menuName }','${pageContext.request.contextPath}${menu.menuUrl }')"><img src="themes/icons/man.png" style="margin-top: 10px;">${menu.menuName }</a><br/>
					</c:forEach>
				</div>
				<div title="系统设置" style="padding:10px;">
				</div>
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div id="mytabs" class="easyui-tabs" style="width:100%;height:100%">
		<div title="欢迎使用" style="padding:10px">
			<div id="box">
				<div class="pic"><img src="image/1.jpg" width="100%" height="100%" /></div>
				<div class="pic"><img src="image/2.jpg" width="100%" height="100%" /></div>
				<div class="pic"><img src="image/3.jpg" width="100%" height="100%" /></div>
				<div class="pic"><img src="image/4.jpg" width="100%" height="100%" /></div>
			</div>
		</div>
	</div>
		</div>
	</div>
  </body>
</html>