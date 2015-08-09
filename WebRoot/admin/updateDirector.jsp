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


		<title>编辑导演</title>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>/admin/scripts/jquery-1.4.2-min.js"></script>
		<link href="<%=basePath%>/admin/styles/common.css" rel="stylesheet"
			type="text/css">
	</head>
	<body>
		<div>
			<form action="/SeeWorld/svc/multimedia/Director/update.action" method="post">
			<input type="hidden" name="Director.id" value="<s:property value="targetId"/>"/>
			姓名：     <input type="text" name="Director.name"  value="<s:property value="Director.name" />" /><br/>
			<!--  英文名：<input type="text"/ name=""><br/>-->
			地区：     <s:select name="Director.areaAndCountry.id"
						list="areaAndCountryList" headerKey="0"
						headerValue="-----请选择-----" listKey="id" key="0" listValue="name"></s:select>
						<br/>
			<input value="提交" type="submit" />
			</form>
		</div>
		<s:debug />
	</body>
</html>