<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Write</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>


<body>

<ul class="layui-nav layui-nav-tree layui-nav-side">
  
  <li class="layui-nav-item"><a href="article">已写</a></li>
  <li class="layui-nav-item"><a href="javascript:;">新建</a></li>
  <li class="layui-nav-item"><a href="index">我的</a></li>
  <li class="layui-nav-item"><a href="quit">退出</a></li>
  
</ul>



<form class="layui-form" action="" method="post">
<div class="layui-show" style="width:80%;margin-left:10%;margin-top:1%">


    <div class="layui-input-block" style="width:85%">
      <input type="text" name="title" required lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
	<div style="margin-top:20px">
		<hr/>
	</div>
	<div class="layui-input-block" style="margin-top:20px;width:85%">
      <textarea name="cont" placeholder="请输入内容" class="layui-textarea" rows="35"></textarea>
	</div>
	
    <div class="layui-form-item" style="margin-top:20px;">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">发布</button>
    </div>
	</div>

</div>
</form>

<script src="/Diary/layui/layui.js" charset="utf-8"></script>   
<script>

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
