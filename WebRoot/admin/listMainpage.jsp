<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页滚动资源管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="<s:property value="basePath"/>admin/styles/table_main.css" rel="stylesheet" type="text/css">
	<link href="<s:property value="basePath"/>admin/styles/common.css" rel="stylesheet" type="text/css">
	<link href="<s:property value="basePath"/>admin/styles/upload.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<s:property value="basePath"/>admin/scripts/jquery-1.4.2-min.js"></script>
	<script type="text/javascript" src="<s:property value="basePath"/>admin/scripts/jquery.form.js"></script>
	<script type="text/javascript" src="<s:property value="basePath"/>admin/scripts/list_mainpage.js"></script>
  </head>
  
  <body>
    <div id="containertable">
		<h2>首页显示列表</h2>
		<s:iterator value="mainpageList" status="status">
		<form style="border-bottom: solid 1px black">

			<label style="display:inline;">显示项id</label>
			<span><s:property value="id"/></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<input type="hidden" name="mainpageId" value="<s:property value="id"/>"/>
			
			<label style="display:inline;">资源显示名称</label>
			<span><s:property value="multimedia.title"/></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<label style="display:inline;">资源显示类型</label>
			<s:if test="isScroll">
			<span>滚动显示</span>
			</s:if>
			<s:else>
			<span>静态显示</span>
			</s:else>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<label style="display:inline;">添加为首页显示时间</label>
			<span><s:property value="addTime"/></span>
			<br/><br/>
			
			<label style="display:inline;">资源描述</label>
			<input type="text" class="text-input medium-input" name="desc" value="<s:property value="briefDescription"/>">
			<br/><br/>
			
			<div style="display:inline;float:left">
				<label>显示图像</label>
				<img src="/posters/<s:property value="image"/>" width="200px" height="100px"/>
			</div>
			
			<div style="display:inline;float:left;margin-left:50px;">
				<label>选择新图像</label>
				<input type="file" name="image"/>
				<br/><br/><br/>
				<input type="submit" value="提交描述或图片更改"/>
			</div>
			
			<div style="display:inline;float:left;margin-left:50px;">
				<a href="javascript:void(0)" class="cancleMp" id="<s:property value="id"/>">取消该资源的首页显示</a><br/><br/>
				<s:if test="#status.first == false">
				<a href="javascript:void(0)" class="moveUp" id="<s:property value="id"/>">上移（移动显示顺序）</a><br/><br/>
				</s:if>
				<s:if test="#status.last == false">
				<a href="javascript:void(0)" class="moveDown" id="<s:property value="id"/>">下移（移动显示顺序）</a><br/><br/>
				</s:if>
			</div>
			
			<div class="clear"></div>
			<br/>
		</form>
		</s:iterator>
	</div>
  </body>
</html>
