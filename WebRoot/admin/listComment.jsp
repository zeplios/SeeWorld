<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>评论列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<script type="text/javascript" src="scripts/jquery-1.4.2-min.js"></script>
	<script type="text/javascript" src="scripts/jquery.form.js"></script>
	<script type="text/javascript" src="scripts/comment.js"></script>
	<link href="styles/table_main.css" rel="stylesheet" type="text/css">
	<link href="styles/common.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
    <div id="containertable">
		<h2>全部评论列表</h2>
		<form id="modifySetting">
			<label style="display:inline;">永久修改每页显示的评论数：</label>
			<input class="text-input small-input" type="text" name="value" />
			<input type="submit" value="修改"/>
		</form>
		<table cellpadding="0" cellspacing="0" border="0" class="display">
			<thead>
				<tr>
					<th>用户名</th>
					<th>评论标题</th>
					<th>评论内容</th>
					<th>评论目标</th>
					<th>评论时间</th>
					<th>资源类型</th>
					<th>删除</th>
					<th>彻底删除</th>
				</tr>
			</thead>
			<tbody id="content_list">
			</tbody>
			<tfoot>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
		</table>
		<div id="page_span">
			<span id="page_first" class="page_button">首页</span>
			<span id="page_previous" class="page_button">前一页</span>
			<span id="pagenum"> </span>
			<span id="page_next" class="page_button">后一页</span>
			<span id="page_last" class="page_button page_last">末页</span>
		</div>
	</div>
  </body>
</html>
