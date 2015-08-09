<%--modified in 2012-11-4 zhfch --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title>SeeWorld后台管理</title>
	<script type="text/javascript" src="scripts/jquery-1.4.2-min.js"></script>
	<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
	<link href="styles/index_admin.css" rel="stylesheet" type="text/css" />
	<link href="styles/common.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="body-wrapper">
	<div id="sidebar">
		<div id="sidebar-wrapper">
			<h1 id="sidebar-title" style="display:none;"><a href="#">SeeWorld Admin</a></h1>
	        <div id="title">
				<img id="logo" src="images/logo_admin_rec.png" alt="SeeWorld Admin logo" /><span id="logotext"></span>
	        </div>
	
			<div id="profile-links">
				亲爱的&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="Edit your profile"><s:property value="currentUsername"/></a>,&nbsp;&nbsp;你好
		        <br /><br />
				<a href="/SeeWorld/svc/user/User/login.action" title="View the Site">浏览SeeWorld</a> | <a href="/SeeWorld/back/j_spring_security_logout" title="Sign Out">注销</a>
		        <br /><br />
			</div>        
			
			<ul id="main-nav">
				<li>
					<a href="javascript:void(0);" target="main" class="nav-top-item">用户管理</a>
		            <ul>
						<li><a href="listUser.jsp" target="main">用户列表</a></li>
						<li><a href="javascript:void(0)" target="main">角色管理</a></li>
					</ul>    
				</li>
				<li>
					<a href="javascript:void(0);" target="main" class="nav-top-item">首页管理</a>
		            <ul>
						<li><a href="/SeeWorld/svc/multimedia/Mainpage/listMpScroll.action" target="main">滚动资源</a></li>
						<li><a href="/SeeWorld/svc/multimedia/Mainpage/listMpStatic.action" target="main">静止资源</a></li>
					</ul>    
				</li>
				<li>
					<a href="user_list.jsp" target="main" class="nav-top-item">评论管理</a>
		            <ul>
						<li><a href="listComment.jsp" target="main">评论列表</a></li>
					</ul>
				</li>
				<li> 
					<a href="javascript:void(0);" target="main" class="nav-top-item">电影管理</a>
					<ul>
						<li><a href="listMovie.jsp" target="main">电影列表</a></li>
		                <li><a href="/SeeWorld/svc/multimedia/Movie/preAdd.action" target="main">上传电影</a></li>
					</ul>
				</li>
				<li> 
					<a href="javascript:void(0);" target="main" class="nav-top-item">视频管理</a>
					<ul>
						<li><a href="listVideo.jsp" target="main">视频列表</a></li>
		                <li><a href="/SeeWorld/svc/multimedia/Video/preAdd.action" target="main">上传视频</a></li>
					</ul>
				</li>
				<li> 
					<a href="javascript:void(0);" target="main" class="nav-top-item">剧集管理</a>
					<ul>
						<li><a href="listSerial.jsp" target="main">剧集列表</a></li>
						<li><a href="/SeeWorld/svc/multimedia/Serial/preAdd.action" target="main">添加新剧集</a></li>
					</ul>
				</li>
				<li> 
					<a href="javascript:void(0);" target="main" class="nav-top-item">其他管理</a>
					<ul>
						<li><a href="/SeeWorld/svc/multimedia/Director/list.action" target="main">导演管理</a></li>
						<li><a href="/SeeWorld/svc/multimedia/Actor/list.action" target="main">演员管理</a></li>
		       			<li><a href="/SeeWorld/svc/multimedia/AreaAndCountry/list.action" target="main">地区及国家管理</a></li>              
		                <li><a href="/SeeWorld/svc/multimedia/Lecturer/list.action" target="main">主讲人管理</a></li>
						<li><a href="/SeeWorld/svc/multimedia/Organization/list.action" target="main">组织管理</a></li>
						<li><a href="/SeeWorld/svc/multimedia/Category/list.action" target="main">分类管理</a></li>
						<li><a href="#" target="main">学院及专业管理</a></li>
					</ul>
				</li>
			</ul>
			
			<div id="messages" style="display: none">
				<!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
			</div>
		</div>
	</div>
    <div id="main-content">
    	<iframe src="listUser.jsp" frameborder="0" height="1500px" name="main" style="width:99%;min-width:600px;"></iframe>
    </div>
</div>
</body>
</html>
