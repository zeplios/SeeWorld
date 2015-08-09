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


		<title>编辑主讲人</title>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>/admin/scripts/jquery-1.4.2-min.js"></script>
		<link href="<%=basePath%>/admin/styles/common.css" rel="stylesheet"
			type="text/css">
	</head>
	<body>
		<div>
			<form action="/SeeWorld/svc/multimedia/Lecturer/update.action" method="post">
			<input type="hidden" name="lecturer.id" value="<s:property value="targetId"/>"/>
			姓名：     <input type="text" name="lecturer.name"  value="<s:property value="lecturer.name" />" /><br/>
			<!--  英文名：<input type="text"/ name=""><br/>-->
			组织：     <s:select name="lecturer.organization.id"
						list="organizationList" headerKey="0"
						headerValue="-----请选择-----" listKey="id" key="0" listValue="name"></s:select>
						<br/>
			<input value="提交" type="submit" />
			</form>
		</div>
		<s:debug />
	</body>
</html>