<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="header">
	<div class="header_container">
		<h1 class="logo">SeeWorld</h1>
		<div class="login_search">
			<ul>
				<li>
					<s:if test="loggedIn">
					<a class="head_button" title="个人主页" href="svc/user/User/personalNew.action" ><s:property value="currentUsername"/></a>
					</s:if>
					<s:else>
					<a class="head_button" id="login" title="登录" href="javascript:void(0);" >登录</a>
					<div id="login_box" class="login_show">
						<div class="small_arrow"></div>
						<form action="/SeeWorld/svc/user/User/security.action" method="post">
							<div class="user_info">
								<p>
									<label class="label_style1" for="account">账号：</label>
									<input class="input_style1" type="text" name="userName" />
								</p>
								<p>
									<label class="label_style1" for="password">密码：</label>
									<input class="input_style1" type="password" name="password" />
								</p>
							</div>
							<div class="options">
								<div class="remember_info">
									<input class="checkbox_sytle1" type="checkbox" id="remember_info" name="remcheck"/>
									<label class="label_style2" for="remember_info" >记住</label>
								</div>
								<div class="about_user">
									<a class="forget_pass" title="忘记密码" href="codeBack.jsp">忘记密码</a>
									<a class="register" title="注册" href="svc/user/User/preRegister.action">注册</a> </div>
								</div>
								<div class="login_btn"> <input class="login_btn_a" type="submit" value="登录" />
							</div>
						</form>
					</div>
					</s:else>
				</li>
				<li><a class="head_button" id="search" href="javascript:void(0);">搜索</a>
				<div id="search_box" class="search_show">
					<div class="small_arrow"></div>
						<form action="svc/multimedia/Search/search.action" method="post" target="_blank">
							<p class="search_form">
								<select class="select_style1" id="category" name="queryCategory">
									<option value="movie">电影</option>
									<option value="serial">剧集</option>
									<option value="video">视频</option>
								</select>
								<input class="input_style2" id="search_input" name="queryKey"/>
							</p>
							<div class="search_btn"><input class="search_btn_a" title="搜索" type="submit" value="搜索" /></div>
						</form>
					</div>
				</li>
				<s:if test="loggedIn">
				<li>
					<a class="head_button" title="注销" href="security/j_spring_security_logout" >注销</a>
				</li>
				</s:if>
			</ul>
		</div>
		<div class="nav">
		<ul>
			<s:url id="indexpage" action="login" namespace="/svc/user/User" />
			<li id="index_li"><s:a title="首页" href="%{indexpage}" cssClass="a_style">首页</s:a></li>
			<s:url id="moviepage" action="overview" namespace="/svc/multimedia/Movie" />
			<li id="movie_li"><s:a title="电影" href="%{moviepage}" cssClass="a_style">电影</s:a></li>
			<s:url id="serialpage" action="overview" namespace="/svc/multimedia/Serial" />
			<li id="serial_li"><s:a title="剧集" href="%{serialpage}" cssClass="a_style">剧集</s:a></li>
			<s:url id="videopage" action="overview" namespace="/svc/multimedia/Video" />
			<li id="video_li"><s:a title="视频" href="%{videopage}" cssClass="a_style">视频</s:a></li>
		</ul>
		</div>
		<script type="text/javascript">
			$(document).ready(function(){
				var url = location.href;
				if(url.toLowerCase().indexOf("movie") >= 0)
					$("#movie_li").addClass("current");
				else if(url.toLowerCase().indexOf("serial") >= 0)
					$("#serial_li").addClass("current");
				else if(url.toLowerCase().indexOf("video") >= 0)
					$("#video_li").addClass("current");
				else if(url.toLowerCase().indexOf("multimedia") < 0)
					$("#index_li").addClass("current");
			});
		</script>
	</div>
</div>