<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:property value="userName"/>&nbsp;的个人中心-SeeWorld-TJU</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<link href="/SeeWorld/style/personal.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" type="text/javascript" src="/SeeWorld/script/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" type="text/javascript" src="/SeeWorld/script/seeworld_js.js" ></script>
	<script type="text/javascript" type="text/javascript" >
		$(document).ready(function(){
			menuControl();
			<s:if test="personalCenterType == 'personalNew'">
            $(".item_new").addClass("item_current");
            </s:if>
            <s:if test="personalCenterType == 'personalFavority'">
            $(".item_favority").addClass("item_current");
            </s:if>
            <s:if test="personalCenterType == 'personalComment'">
            $(".item_comment").addClass("item_current");
            </s:if>
            <s:if test="personalCenterType == 'personalConfig'">
            $(".item_config").addClass("item_current");
            </s:if>
            <s:if test="personalCenterType == 'personalHistory'">
            $(".item_history").addClass("item_current");
            </s:if>
		});
	</script>
  </head>
  
  <body>
    <div class="header">
	    <div class="header_container">
	        <h1 class="logo">SeeWorld</h1>
	        <div class="login_search">
	            <ul>
	                <li class="status_open"><a class="head_button" id="user_name" title="登录" href="javascript:void(0);" ><s:property value="currentUsername"/></a>
	                    <!--<div id="personal_box" class="personal_show">
	                        <div class="small_arrow"></div>
	                        <div class="user_mini_avatar"> <a class="mini_avatar_a" href="#0" title=""><img class="mini_avatar_a_img" width="25" height="25" src="/posters/<s:property value="photoPath"/>" alt="头像" title="" /></a> </div>
	                        <ul class="personal_mini_nav">
	                            <li class="mini_nav_item"> <a class="mini_nav_link" href="#0" title="最新动态">最新动态</a> </li>
	                            <li class="mini_nav_item"> <a class="mini_nav_link" href="#0" title="我的收藏">我的收藏</a> </li>
	                            <li class="mini_nav_item"> <a class="mini_nav_link" href="#0" title="我的评论">我的评论</a> </li>
	                            <li class="mini_nav_item"> <a class="mini_nav_link" href="#0" title="最新动态">个人设置</a> </li>
	                            <li class="mini_nav_item"> <a class="mini_nav_link" href="#0" title="最新动态">历史观看</a> </li>
	                        </ul> 
	                    </div>-->
	                </li>
	                <li> <a class="head_button" id="search" title="搜索" href="javascript:void(0);" >搜索</a>
	                    <div id="search_box" class="search_show">
	                        <div class="small_arrow"></div>
	                        <p class="search_form">
	                            <select class="select_style1" id="category">
	                                <option value="movie">电影</option>
	                                <option value="serial">剧集</option>
	                                <option value="video">视频</option>
	                            </select>
	                            <input class="input_style2" id="search" />
	                        </p>
	                        <div class="search_btn"> <a class="search_btn_a" title="搜索" href="#" >搜索</a> </div>
	                    </div>
	                </li>
	                <li>
			        	<a class="head_button" title="注销" href="/SeeWorld/security/j_spring_security_logout" >注销</a>
			        </li>
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
	    </div>
	</div>
	<div class="main">
	    <div class="personal">
	        <div class="personal_sidebar">
	            <div class="sidebar_container">
	                <div class="top_info">
	                    <div class="user_avatar">
	                        <div class="user_avatar_box"><img width="50" height="50" id="personal_avatar" src="/avatars/<s:property value="photoPath"/>" alt="用户头像"/></div>
	                    </div>
	                    <div class="user_data clearfix">
	                        <h2 class="user_name"><s:property value="userName"/></h2>
	                        <!-- <div class="user_other_data"> <cite class="comment_data"><span class="data_number">9999</span>评论</cite> <cite class="favorite_data"><span class="data_number">9999</span>收藏</cite> </div> -->
	                    </div>
	                </div>
	                <div class="detail_info">
	                    <table class="info_table" cellspacing="0" cellpadding="0" border="0">
	                        <tr>
	                            <td class="info_title" scope="col"> 学院： </td>
	                            <td class="info_content" scope="col"> <s:property value="academy_name"/> </td>
	                        </tr>
	                        <tr>
	                            <td class="info_title" scope="col"> 专业： </td>
	                            <td class="info_content" scope="col"> <s:property value="specialty_name"/> </td>
	                        </tr>
	                        <tr>
	                            <td class="info_title" scope="col"> 年级： </td>
	                            <td class="info_content" scope="col"> ---- </td>
	                        </tr>
	                        <tr>
	                            <td class="info_title" scope="col"> 真实姓名： </td>
	                            <td class="info_content" scope="col"> <s:property value="realName"/> </td>
	                        </tr>
	                        <tr>
	                            <td class="info_title" scope="col"> 邮箱： </td>
	                            <td class="info_content" scope="col"> <s:property value="email"/> </td>
	                        </tr>
	                        <tr>
	                            <td class="info_title" scope="col"> 上次登录： </td>
	                            <td class="info_content" scope="col"> <s:property value="lastLoginTime"/> </td>
	                        </tr>
	                    </table>
	                </div>
	                <div class="personal_nav">
	                    <ul class="nav_list">
	                    	<s:url id="new" action="personalNew" namespace="/svc/user/User" />
	                    	<s:url id="favorite" action="personalFavorite" namespace="/svc/user/User" />
	                    	<s:url id="comment" action="personalComment" namespace="/svc/user/User" />
	                    	<s:url id="config" action="personalConfig" namespace="/svc/user/User" />
	                    	<s:url id="history" action="personalHistory" namespace="/svc/user/User" />
	                        <li class="nav_item item_new"> <s:a cssClass="nav_item_a" href="%{new}" title="最新动态"><span class="nav_item_a_span">最新动态</span></s:a> </li>
	                      <%-- <li class="nav_item item_favorite"> <s:a cssClass="nav_item_a" href="%{favorite}" title="我的收藏"><span class="nav_item_a_span">我的收藏</span></s:a> </li> --%>
	                        <li class="nav_item item_comment"> <s:a cssClass="nav_item_a" href="%{comment}" title="我的评论"><span class="nav_item_a_span">我的评论</span></s:a> </li>
	                        <li class="nav_item item_config"> <s:a cssClass="nav_item_a" href="%{config}" title="个人设置"><span class="nav_item_a_span">个人设置</span></s:a> </li>
	                        <li class="nav_item item_history"> <s:a cssClass="nav_item_a" href="%{history}" title="历史观看"><span class="nav_item_a_span">历史观看</span></s:a> </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	        <div class="personal_main">
	            <s:if test="personalCenterType == 'personalNew'">
	            <s:include value="personalNew.jsp"></s:include>
	            </s:if>
	            <s:if test="personalCenterType == 'personalFavority'">
	            <s:include value="personalFavorite.jsp"></s:include>
	            </s:if>
	            <s:if test="personalCenterType == 'personalComment'">
	            <s:include value="personalComment.jsp"></s:include>
	            </s:if>
	            <s:if test="personalCenterType == 'personalConfig'">
	            <s:include value="personalConfig.jsp"></s:include>
	            </s:if>
	            <s:if test="personalCenterType == 'personalHistory'">
	            <s:include value="personalHistory.jsp"></s:include>
	            </s:if>
	        </div>
	    </div>
	</div>
	<div class="footer">
	    <div class="footer_container">
	        <ul class="link">
	            <li><a title="网站简介" href="#">网站简介</a></li>
	            <li><a title="网站招聘" href="#">网站招聘</a></li>
	            <li><a title="联系方式" href="#">联系方式</a></li>
	        </ul>
	        <p class="copyright">Copyright &copy; 2009-2012 INA All rights reserved. 网络中心工作室&nbsp;&nbsp;版权所有</p>
	    </div>
	</div>
  </body>
</html>
