<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<s:debug />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>添加新SingleSerial</title>
		<base href="<%=basePath%>" />
		<link href="admin/styles/common.css" rel="stylesheet" type="text/css" />
		<link href="admin/styles/upload.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="admin/scripts/seeworld_bg.js"></script>
	</head>

	<body>
		<h2>添加新单集</h2>

		<form action="/SeeWorld/svc/multimedia/SingleSerial/add.action" method="post">
			<fieldset>
				<p>
					<input type="hidden" name="serial.id" value="${param.id}"/>
					<label style="display: inline;">单集名	</label>
					<input class="text-input medium-input" type="text" name="title" />
					<br />
					<br />
					<label style="display: inline;">单集别名</label>
					<input class="text-input medium-input" type="text" name="alias" />
					<br />
					<br />
					<label style="display: inline;">第</label>
					<input class="text-input small-input" type="text" size="3" name="episode" />
					<label style="display: inline;">集</label>

					<br />
					<br />

					<label style="display: inline;">影片路径</label>
					<input class="text-input medium-input" type="text" name="path" value="rtmp://" />
				</p>
				<p>
					<input class="button" value="提交" type="submit" />
				</p>
			</fieldset>
			<div class="clear"></div>
		</form>
	</body>
</html>
