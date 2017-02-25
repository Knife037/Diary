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
</style>

<title>Diary · Write</title>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs">
		<li><a href="index">我的</a></li>
		<li><a href="article">已写</a></li>
		<li class="active"><a href="write">新建</a></li>
		<li><a href="quit">退出</a></li>
	</ul>
</div>
<form action="write" method="post">
<div class="form-element">
	<input type="text" name="title" class="form-control" placeholder="请输入标题"/>
</div>
<div class="form-element">
	<textarea type="textarea" name="cont" class="form-control" rows="30" placeholder="请输入内容"></textarea>
</div>
<div class="form-element">
<button class="btn btn-primary">发布</button>
</div>
</form>


<script src="dist/js/jquery.min.js"></script>
<script src="dist/js/bootstrap.min.js"></script>
<script>

</script>

</body>
</html>