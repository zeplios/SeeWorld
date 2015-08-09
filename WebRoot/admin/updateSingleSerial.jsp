<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>添加新SingleSerial</title>
		<base href="<s:property value="basePath"/>" />
		<link href="admin/styles/common.css" rel="stylesheet" type="text/css" />
		<link href="admin/styles/upload.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="admin/scripts/seeworld_bg.js"></script>
	</head>

	<body>
		<h2>添加新单集</h2>

		<form action="/SeeWorld/svc/multimedia/SingleSerial/update.action" method="post">
			<fieldset>
				<p>
					<s:hidden name="id" />
					<s:hidden name="serial.id" />
					
					<label style="display: inline;">单集名	</label>
					<s:textfield cssClass="text-input medium-input" name="title" />
					<br />
					<br />
					
					<label style="display: inline;">单集别名</label>
					<s:textfield cssClass="text-input medium-input" name="alias" />
					<br />
					<br />
					
					<label style="display: inline;">第</label>
					<s:textfield class="text-input small-input" size="3" name="episode" />
					<label style="display: inline;">集</label>
					<br />
					<br />

					<label style="display: inline;">影片路径</label>
					<s:textfield cssClass="text-input medium-input" name="path" />
				</p>
				<p>
					<input class="button" value="提交" type="submit" />
				</p>
			</fieldset>
			<div class="clear"></div>
		</form>
	</body>
</html>
