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
		
		<title>视频列表</title>
		<script type="text/javascript" src="<%=basePath %>admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="<%=basePath %>admin/scripts/jquery.dataTables.js"></script>
		<script type="text/javascript" src="<%=basePath %>admin/scripts/FixedHeader.js"></script>
		<script type="text/javascript" src="<%=basePath %>admin/scripts/jquery.form.js"></script>
		<script type="text/javascript" src="<%=basePath %>admin/scripts/seeworld_bg.js"></script>
		<link href="<%=basePath %>admin/styles/table_main.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>admin/styles/common.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" charset="utf-8">
			$(function() {
				//categoryId : 1, publishedYear : 1, areaAndCountryId : 1,
				getMultimediaList("Movie", {
					orderBy : 2,
					currentPage : 1
				});
				
				$("#search").keyup(function(){
					$.ajax({
						cache : false,
						dataType : "json",
						type : "POST",
						url : "/SeeWorld/svc_ajax/multimedia/Multimedia/searchMovie_json.action",
						data : {
							q: $("#search").val()
						},
						success : loadContent
					});
				});
			});
		</script>
	</head>
	<body>
		<div id="containertable">
			<h2>全部视频列表	</h2>

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
					<input id="search" type="text" />
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="display"
					id="example">
					<thead>
						<tr>
							<th>视频名称</th>
							<th>类别</th>
							<th>地区</th>
							<th>上传者	</th>
							<th>上传时间</th>
							<th>编辑</th>
							<th>删除</th>
							<th>设为首页显示</th>
						</tr>
					</thead>
					<tbody id="content_list">
					</tbody>
					<tfoot>
						<tr>
							<th>视频名称</th>
							<th>类别</th>
							<th>地区</th>
							<th>上传者	</th>
							<th>上传时间</th>
							<th>编辑</th>
							<th>删除</th>
							<th>设为首页显示</th>
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