<%@page import="cn.knife037.bean.ArticleBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link href="dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	form{
		width:60%;
		margin-left:20%;
		margin-top:1%;
	}
	.form-element{
		margin-top:1%;
	}
	.content{
		width:60%;
		margin-left:20%;
		margin-top:1%;
	}
</style>

<title>Diary · Write</title>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="index">我的</a></li>
		<li class="active"><a href="article">已写</a></li>
		<li><a href="write">新建</a></li>
		<li><a href="quit">退出</a></li>
	</ul>
</div>
<div class="content">
<blockquote>
	<h1>${article.title}</h1>
</blockquote>
${article.cont}
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script>

</script>

</body>
</html>