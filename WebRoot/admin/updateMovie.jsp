<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- modified by zhfch 2012-11-2 --%>
<s:debug />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>SeeWorldAdmin编辑电影</title>
		<base href="<s:property value="basePath"/>"/>
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
		<h2>编辑电影</h2>

		<form action="/SeeWorld/svc/multimedia/Movie/update.action" method="post">
			<fieldset>
				<p>
					<s:hidden name="id"></s:hidden>
					
					<label style="display: inline;">电影名称</label>
					<s:textfield cssClass="text-input medium-input" name="title" />
					
					<br /><br />
					
					<label style="display: inline;">电影别名</label>
					<s:textfield cssClass="text-input medium-input" name="alias" />
					
					<br /><br />
					
					<label style="display: inline;">所属年代</label>
					<s:textfield class="text-input small-input" name="publishedYear" />
				</p>

				<p>
					<s:hidden name="resource.id"></s:hidden>

					<label style="display: inline;">分类</label>
					<s:select name="categoryId" list="categoryList" listKey="id" listValue="name" />
					<br />
					<br />

					<label style="display: inline;">所属地区</label>
					<s:select name="areaAndCountry.id"
						list="areaAndCountryList" listKey="id" listValue="name"></s:select>
				</p>

				<p>
					<label>导演（多个导演时以空格分开）</label>
					<s:textfield cssClass="text-input small-input" type="text" id="director"
						name="directorList[0].name" />

					<br />
					<br />
					<label>主演（多个主演时以空格分开）</label>
					<s:textfield cssClass="text-input small-input" type="text" id="actor"
						name="actorList[0].name" />
				</p>

				<p>
					<label>影片简介</label>
					<s:textarea cssClass="text-input textarea wysiwyg" id="textarea" name="introduction" 
						cols="80" rows="10"></s:textarea>
				</p>

				<p id="thumb">
					<label >
						缩略图<s:property value="image" />
					</label>
					<br />
					<img src="/posters/<s:property value="image" />" />
				</p>

				<p>
					<iframe id="ifr" name="ajaxUpload" width="100%" height="50"
						frameborder=0 src="admin/addImage.jsp"></iframe>
					新上传的封面名：<input type="text" id="imagePath" style="display: none"
						name="image" />
				</p>

				<label style="display: inline;">影片路径</label>
				<s:textfield cssClass="text-input medium-input" name="path" />

				<p>
					<input class="button" value="提交" type="submit" />
				</p>
			</fieldset>

			<div class="clear"></div>
		</form>
	</body>
</html>
