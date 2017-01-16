<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Home</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>


<body>

<ul class="layui-nav layui-nav-tree layui-nav-side">
  
  <li class="layui-nav-item"><a href="article">已写</a></li>
  <li class="layui-nav-item"><a href="write">新建</a></li>
  <li class="layui-nav-item"><a href="javascript:;">我的</a></li>
  <li class="layui-nav-item"><a href="quit">退出</a></li>
  
</ul>

<div style="margin-left:20%;margin-top:1%;width:70%">
	<blockquote class="layui-elem-quote layui-quote-nm" style="font-size:20px">${article.title}</blockquote>
	<hr>
	${article.cont}
</div>

<script src="layui/layui.js" charset="utf-8"></script>   
<script>
layui.use(['element'], function(){
		var element = layui.element();});
</script>
</body>
</html>