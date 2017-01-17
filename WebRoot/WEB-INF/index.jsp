<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link href="dist/css/bootstrap.min.css" rel="stylesheet">
<style>
</style>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);">我的</a></li>
		<li><a href="article">已写</a></li>
		<li><a href="write">新建</a></li>
		<li><a href="quit">退出</a></li>
	</ul>
</div>

<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$("li").click(function() {
		$("li")
	});
});
</script>

</body>
</html>