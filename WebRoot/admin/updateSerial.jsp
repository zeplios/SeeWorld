<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:debug />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>SeeWorldAdmin上传电影</title>
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
		<h2>添加新剧集</h2>

		<form action="/SeeWorld/svc/multimedia/Serial/update.action" method="post">
			<fieldset>
				<p>
					<input type="hidden" name="id" value="<s:property value="id"/>"/>
					<label style="display: inline;">剧集名称</label>
					<s:textfield cssClass="text-input medium-input" name="title" />
					<br />
					<br />
					
					<label style="display: inline;">剧集别名</label>
					<s:textfield cssClass="text-input medium-input" name="alias" />
					<br />
					<br />
					
					<label style="display: inline;">所属年代</label>
					<s:textfield cssClass="text-input small-input" name="publishedYear" />
				</p>
				<p>
					<s:hidden name="resource.id" />

					<label>分类</label>
					<s:select name="categoryId" list="categoryList" listKey="id" listValue="name" />
					<br />
					<br />

					<label style="display: inline;">所属地区</label>
					<s:select name="areaAndCountry.id" list="areaAndCountryList"
							listKey="id" listValue="name" />
					<br />
					<br />

					<label>共</label>
					<s:textfield cssClass="text-input small-input" size="3" name="seasons"  />
					<label>季，第</label>
					<s:textfield cssClass="text-input small-input" size="3" name="season"  />
					<label>季</label>
				</p>
				<p>
					导演和主演 根据下拉菜单中自动提示的内容选择<br />

					<label style="display: inline;">导演</label>
					<s:textfield cssClass="text-input small-input" id="director"
							name="directorList[0].name" />

					<br />
					<br />
					<label style="display: inline;">主演</label>
					<s:textfield cssClass="text-input small-input" id="actor"
							name="actorList[0].name" />
				</p>
				<p>
					<label>影片简介</label>
					<s:textarea cssClass="text-input textarea wysiwyg" id="textarea" name="introduction" 
						cols="80" rows="10"></s:textarea>
				</p>

				<p>
					<iframe id="ifr" name="ajaxUpload" width="100%" height="50"
						frameborder=0 src="<s:property value="basePath"/>admin/addImage.jsp"></iframe>
					新上传的封面名：<input type="text" id="imagePath" style="display: none"
						name="image" />
				</p>

				<p>
					<input class="button" value="提交" type="submit" />
				</p>
			</fieldset>
			<div class="clear"></div>
		</form>
	</body>
</html>
