<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户列表</title>
		
		<meta name="description" content="SeeWorld 天津大学视频中心" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="scripts/jquery.dataTables.js"></script>
		<script type="text/javascript" src="scripts/FixedHeader.js"></script>
		<!-- <script type="text/javascript" src="scripts/facebox.js"></script> -->
		<script type="text/javascript" src="scripts/list_user.js"></script>
		<link href="styles/table_main.css" rel="stylesheet" type="text/css">
		<link href="styles/common.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<div id="containertable">
			<h2>全部用户列表</h2>

			<form method="post">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
					<thead>
						<tr>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>学院</th>
							<th>专业</th>
							<th>角色</th>
							<th>发言权限</th>
							<th>删除用户</th>
						</tr>
					</thead>
					<tbody>
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
						</tr>
					</tfoot>
				</table>
			</form>

			<div id="roles" style="display: none">
				<form method="post">
					<input name="role" type="radio" value="0">
					普通用户
					<input name="role" type="radio" value="1">
					高级用户
					<input name="role" type="radio" value="2">
					管理用户
					<input name="role" type="radio" value="3">
					封锁用户
					<br />
					<br />
					<input type="submit" class="button" value="确定" />
					<input type="button" class="button" value="取消" />
				</form>
			</div>
		</div>
	</body>
</html>
