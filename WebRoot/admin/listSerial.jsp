<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

		<title>剧集列表</title>
		<link href="<%=basePath %>/admin/styles/table_main.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>/admin/styles/common.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath %>/admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/admin/scripts/seeworld_bg.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				getMultimediaList("Serial", {
					orderBy : 2,	/* ADD_TIME */
					currentPage : 1
				});
			});
		</script>
	</head>
	<body>
		<div id="containertable">
			<h2>全部剧集列表</h2>

			<form method="post">
				<div class="sort">
					排序方式:
					<select id="sort" name="sort" onchange="changeSort()">
						<option value="2">更新时间</option>
						<option value="1">电影名称</option>
						<option value="4">收藏次数</option>
						<option value="3">点击次数</option>
						<option value="5">推荐次数</option>
					</select>

				</div>
				<div id="quicksearch">
					快速搜索:
					<input type=text />（该搜索当前不可用！）
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="display"
					id="example">
					<thead>
						<tr>
							<th>剧集名称</th>
							<th>年代</th>
							<th>地区</th>
							<th>季</th>
							<th>总季数</th>
							<th>导演</th>
							<th>上传时间</th>
							<th>添加子集</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody id="content_list">
					</tbody>
					<tfoot>
						<tr>
							<th>剧集名称</th>
							<th>年代</th>
							<th>地区</th>
							<th>季</th>
							<th>总季数</th>
							<th>导演</th>
							<th>上传时间</th>
							<th>添加子集</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</tfoot>
				</table>
				<div id="count_info">
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