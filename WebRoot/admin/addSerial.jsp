<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- modified in 2012-11-5, settings about directors and actors are not completed --%>
<s:debug />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>SeeWorldAdmin上传电影</title>
		<base href="<s:property value="basePath"/>" />
		<link href="admin/styles/jquery.autocomplete.css" rel="stylesheet" type="text/css">
		<link href="admin/styles/upload.css" rel="stylesheet" type="text/css" />
		<link href="admin/styles/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="admin/scripts/jquery.autocomplete.js"></script>
		<script type="text/javascript" src="admin/scripts/seeworld_bg.js"></script>
		<script type="text/javascript" src="admin/scripts/dal_autocomplete.js"></script>
	</head>

	<body>
		<h2>添加新剧集</h2>

		<form action="/SeeWorld/svc/multimedia/Serial/add.action" method="post" >
			<fieldset>
				<p>
					<label style="display: inline;">剧集名称</label>
					<input class="text-input medium-input" type="text" name="title" />
					<br /><br />
					
					<label style="display: inline;">剧集别名</label>
					<input class="text-input medium-input" type="text" name="alias" />
					<br /><br />
					
					<label style="display: inline;">所属年代</label>
					<input class="text-input small-input" type="text" name="publishedYear" />
				</p>
				<p>
					<label>分类</label>
					<s:select name="categoryId" list="categoryList" headerKey="0"
						listKey="id" listValue="name"></s:select>
					<br />
					<br />

					<label style="display: inline;">所属地区</label>
					<s:select name="areaAndCountry.id" list="areaAndCountryList"
						headerKey="0" listKey="id" listValue="name"></s:select>
					<br />
					<br />

					<label>共</label>
					<input class="text-input small-input" type="text" size="3" name="seasons" />
					<label>季，第</label>
					<input class="text-input small-input" type="text" size="3" name="season" />
					<label>季</label>
				</p>

				<p>
					<label style="display: inline;">导演（多个导演时以空格分开）</label>
					<input class="text-input small-input" type="text" id="director"
						name="directorList[0].name" />

					<br />
					<br />
					<label style="display: inline;">主演（多个主演时以空格分开）</label>
					<input class="text-input small-input" type="text" id="actor"
						name="actorList[0].name" />
				</p>

				<p>
					<label>影片简介</label>
					<s:textarea cssClass="text-input textarea wysiwyg" id="textarea"
						name="introduction" cols="80" rows="10"></s:textarea>
				</p>

				<p>
					<iframe id="ifr" name="ajaxUpload" width="100%" height="50"
						frameborder=0 src="<s:property value="basePath"/>admin/addImage.jsp"></iframe>
					新上传的封面名：<input type="text" id="imagePath" style="display: none"
						name="image" />
				</p>

				<p>
					<input class="button" value="提交" type="submit" />
					<input class="button" type="reset" value="清空" />
				</p>
			</fieldset>
			<div class="clear"></div>
		</form>
	</body>
</html>
