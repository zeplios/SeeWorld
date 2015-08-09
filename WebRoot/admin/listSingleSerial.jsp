<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String targetId = null;
	if (request.getParameter("targetId") != null)
		targetId = request.getParameter("targetId");
	else
		targetId = request.getAttribute("targetId").toString();
	if(targetId == null)
		targetId = "-1";
%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

		<title>Single剧集列表</title>
		<link href="<%=basePath%>admin/styles/table_main.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath%>admin/styles/common.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath%>/admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/admin/scripts/seeworld_bg.js"></script>
		<script type="text/javascript">
			$(function() {
				getMultimediaList("SingleSerial", {
					currentPage : 1,
					targetId : <%=targetId %>
				});
			});
		</script>
	</head>
	<body>
		<div id="containertable">
			<h2>Single剧集列表</h2>
			<label id="title"></label>
			<form method="post">
				<div>
					<a class="add" href="<%=basePath %>admin/addSingleSerial.jsp?id=<%=targetId %>"><img
							src="images/icons/add.png" />添加</a>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="display"
					id="example">
					<thead>
						<tr>
							<th>子集标题</th>
							<th>集</th>
							<th>地区</th>
							<th>导演</th>
							<th>上传时间</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody id="content_list">
					</tbody>
					<tfoot>
						<tr>
							<th>子集标题</th>
							<th>集</th>
							<th>地区</th>
							<th>导演</th>
							<th>上传时间</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</tfoot>
				</table>
				<div id="count_info" style="display:inline">
					共多少条记录呢
				</div>
			</form>
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