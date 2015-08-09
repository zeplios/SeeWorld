<%--已完成导演和演员部分的修改，上传电影部分全部完成，@zhfch，2012-12-6 --%>
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
		<title>SeeWorldAdmin上传电影</title>
		<base href="<%=basePath%>"/>
		<link href="admin/styles/jquery.autocomplete.css" rel="stylesheet" type="text/css">
		<link href="admin/styles/upload.css" rel="stylesheet" type="text/css" />
		<link href="admin/styles/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="admin/scripts/jquery.autocomplete.js"></script>
		<script type="text/javascript" src="admin/scripts/seeworld_bg.js"></script>
		<script type="text/javascript" src="admin/scripts/dal_autocomplete.js"></script>
	</head>

	<body>
		<!-- Page Head -->
		<h2>上传新电影</h2>

		<form action="/SeeWorld/svc/multimedia/Movie/add.action" method="post">
			<fieldset>
				<p>
					<label style="display: inline;">电影名称</label>
					<input class="text-input medium-input" type="text" name="title" />
					<br />
					<br />
					<label style="display: inline;">电影别名</label>
					<input class="text-input medium-input" type="text" name="alias" />
					<br />
					<br />
					<label style="display: inline;">所属年代</label>
					<input class="text-input small-input" type="text" name="publishedYear" />
				</p>

				<p>
					<input type="hidden" name="resource.id" value="1"/>

					<label style="display: inline;">分类</label>
					<s:select name="categoryId" list="categoryList" listKey="id" listValue="name" />
					<br />
					<br />

					<label style="display: inline;">所属地区</label>
					<s:select name="areaAndCountry.id" list="areaAndCountryList" listKey="id" listValue="name" />
				</p>

				<p>
					<label>导演（多个导演时以空格分开）</label>
					<input class="text-input small-input" type="text" id="director"
						name="directorList[0].name" />

					<br /><br />
					
					<label>主演（多个主演时以空格分开）</label>
					<input class="text-input small-input" type="text" id="actor"
						name="actorList[0].name" />
				</p>

				<p>
					<label>影片简介</label>
					<s:textarea class="text-input textarea wysiwyg" id="textarea"
						name="introduction" cols="79" rows="11" />
				</p>
				
				<p>
					<iframe id="ifr" name="ajaxUpload" width="100%" height="50"
						frameborder=0 src="admin/addImage.jsp"></iframe>
					新上传的封面名：<input type="text" id="imagePath" style="display: none"
						name="image" />
				</p>

				<label style="display: inline;">影片路径</label>
				<input class="text-input medium-input" type="text" name="path" value="rtmp://" />

				<p>
					<input class="button" value="提交" type="submit" />
				</p>
			</fieldset>

			<div class="clear"></div>
		</form>
	</body>
</html>
