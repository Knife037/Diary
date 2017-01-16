<%@page import="cn.knife037.bean.ArticleBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
</head>


<body">

<ul class="layui-nav layui-nav-tree layui-nav-side">
  
  <li class="layui-nav-item"><a href="javascript:;">已写</a></li>
  <li class="layui-nav-item"><a href="write">新建</a></li>
  <li class="layui-nav-item"><a href="index">我的</a></li>
  <li class="layui-nav-item"><a href="quit">退出</a></li>
  
</ul>
	<div style="margin-left:20%;width:75%;margin-top:1%">
		<table class="layui-table" lay-skin="nob">
			<colgroup>
				<col>
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>博文</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="article" items="${articles}">
				<tr>
					<td>
						<div>
							<fieldset class="layui-elem-field">
								<legend>${article.title}</legend>
								<div class="layui-field-box">${article.cont}</div>
							</fieldset>
						</div>
					</td>
					<td>
						<button id=view${article.id} class="layui-btn" onclick="viewArticle(this)">查看</button>
						<button id=delete${article.id} class="layui-btn layui-btn-danger" onclick="confirm(this)">删除</button>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
<form id="form" class="layui-form" action="" method="get">
	<input id="articleID" type="hidden" name="id" value="">
</form>
<script src="layui/layui.js" charset="utf-8"></script>   
<script>

function confirm(button) {
	layer.confirm('确认删除此贴<br>', {icon: 2, title: "提示"}, function(index) {
		deleteArticle(button);
  		layer.close(index);
	});
}

function deleteArticle(button) {
	var v = button.id.toString().substring(6);
	var c = document.getElementById("articleID");
	c.value = v;
	var form = document.getElementById("form");
	form.action = "deleteArticle";
	form.submit();
}

function viewArticle(button) {
	var v = button.id.toString().substring(4);
	var c = document.getElementById("articleID");
	c.value = v;
	var form = document.getElementById("form");
	form.action = "viewArticle";
	form.submit();
}
layui.use('form', function(){
  var form = layui.form();
  form.on('submit(formDemo)', function(data){
    return true;
  });
});
layui.use(['element'], function(){
		var element = layui.element();});
</script>
</body>
</html>
