<%@page import="cn.knife037.bean.ArticleBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Diary · Article</title>
<link href="dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	.css-table{
		width:60%;
		margin-left:20%;
		margin-top:1%;
	}
	.form-element{
		margin-top:1%;
	}
</style>
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

<div class="css-table">
	<table class="table table-striped table-hover">
		<colgroup>
			<col width="80%">
			<col width="10"> 
			<col width="10%">
		</colgroup>
		<tr>
			<th>博文</th>
			<th>创建日期</th>
			<th>操作</th>
		</tr>
		
		<c:forEach var="article" items="${articles}">
		<tr>
			<td>${article.title}</td>
			<td>${article.date}</td>
			<td>
				<button id="viewBtn${article.id}" class="btn btn-primary btn-sm">查看</button>
				<button id="delBtn${article.id}" class="btn btn-danger btn-sm">删除</button>
			</td>
		</tr>
		</c:forEach>		
	</table>
</div>

<form id="form" action="" method="get">
	<input id="articleID" type="hidden" name="id" value="">
</form>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(button) {
		$("button[id^='viewBtn']").click(function() {
			var form = $("#form");
			var article = $("#articleID");
			article.attr("value", this.id.toString().substring(7));
			form.attr("action", "viewArticle");
			form.submit();
		});
		
		$("button[id^='delBtn']").click(function() {
			var form = $("#form");
			var article = $("#articleID");
			article.attr("value", this.id.toString().substring(6));
			form.attr("action", "deleteArticle");
			form.submit();
		});
	});
</script>
</body>
</html>
