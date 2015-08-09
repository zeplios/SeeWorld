
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
     String path = request.getContextPath();
     String basePath = request.getScheme()+"://"
     +request.getServerName()
     +":"+request.getServerPort()
     +path+"/";
%>


<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />


		<title>编辑地区及国家</title>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>/admin/scripts/jquery-1.4.2-min.js"></script>
		<link href="<%=basePath%>/admin/styles/common.css" rel="stylesheet"
			type="text/css">
	</head>
	<body>
		<div>
			<form action="/SeeWorld/svc/multimedia/AreaAndCountry/update.action" method="post">
			<input type="hidden" name="areaAndCountry.id" value="<s:property value="targetId"/>"/>
			地名：     <input type="text" name="areaAndCountry.name"  value="<s:property value="areaAndCountry.name" />" /><br/>
			别名：     <input type="text" name="areaAndCountry.alias"   value="<s:property value="areaAndCountry.alias" />" /><br/>
			
			<input value="提交" type="submit" />
			</form>
		</div>
		<s:debug />
	</body>
</html>