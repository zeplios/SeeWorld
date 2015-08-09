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
		<h2>上传新视频</h2>

		<form action="/SeeWorld/svc/multimedia/Video/add.action" method="post">
			<fieldset>
				<p>
					<label style="display: inline;">视频名称</label>
					<input class="text-input medium-input" type="text" name="title" />
					<br /><br />
					<label style="display: inline;">视频别名</label>
					<input class="text-input medium-input" type="text" name="alias" />
					<br /><br />
					<label style="display: inline;">所属年代</label>
					<input class="text-input small-input" type="text" name="publishedYear" />
				</p>
				<p>
					<label style="display: inline;">所属类型</label>
					<input type="hidden" name="resource.id" value="2"/>
					<br /><br />

					<label>分类</label>
					<s:select name="categoryId" list="categoryList" headerKey="0"
						listKey="id" listValue="name"></s:select>
					<br /><br />

					<label style="display: inline;">所属地区</label>
					<s:select name="areaAndCountry.id" list="areaAndCountryList"
						headerKey="0" listKey="id" listValue="name"></s:select>
					<br /><br />
				</p>
				<p>
					<label style="display: inline;">主讲人</label>
					<input class="text-input small-input" type="text" id="lecturer"
						name="lecturerList[0].name" value="" />
					<br />
					<br />
				</p>
				<p>
					<label>影片简介</label>
					<br />
					<textarea class="text-input textarea wysiwyg" id="textarea"
						name="introduction" cols="79" rows="11"></textarea>
				</p>

				<iframe id="ifr" name="ajaxUpload" width="100%" height="50"
					frameborder=0 src="admin/addImage.jsp"></iframe>
				<input type="text" id="imagePath" style="display: none"
					name="image" />

				<label style="display: inline;">影片路径</label>
				<input class="text-input medium-input" type="text"
					name="path" value="rtmp://" />
				<p>
					<input class="button" value="提交" type="submit" />
					<input class="button" type="reset" value="清空" />
				</p>
			</fieldset>
			<div class="clear"></div>
		</form>
	</body>
</html>
