<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:property value="title" /></title>
		<base href="<s:property value="basePath"/>"/>
		<link href="style/init.css" rel="stylesheet" type="text/css" />
		<link href="style/play_page.css" rel="stylesheet" type="text/css" />
		<link href="script/msgBox/jquery.msgbox.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="script/jquery-1.7.2.min.js" ></script>
		<script type="text/javascript" src="script/seeworld_js.js" ></script>
		<script type="text/javascript" src="script/load_comment.js" ></script>
		<script type="text/javascript" src="script/msgBox/jquery.msgbox.min.js" ></script>
		<script type="text/javascript" src="script/toggle_text.js" ></script>
		<script type="text/javascript">
			$(document).ready(function(){
				menuControl();
				$("#txtArea").focus(function(){
		 			 this.value="";
				});
				initToggle("introduce_content", 200, true);
			});
			var currentUserId = '<sec:authentication property="principal.id" />';
			var commentMultimediaId='<s:property value="id"/>';
			var commentResourceId='<s:property value="resourceId"/>';
			var reply=0;
			$(function(){
				//checkCollected({targetUserId:currentUserId,objectId:commentMultimediaId,resourceId:commentResourceId});
				findCommentByPage({currentPage:1,targetId:commentMultimediaId,resourceId:commentResourceId});
	    	});
		</script>
	</head>

	<body>
		<!-- -----------------------------------header--------------------------------------->
		<s:include value="../common/header.jsp"></s:include>
		<!----------------------------------------------------------------------------------->
		<!---------------------------------------main---------------------------------------->	
		<div class="play_section">
		  <div class="play_title_all">
		    <h2 class="play_title"><s:property value="title"/></h2>
		    <s:url id="mainpage" action="beforeLogin" namespace="/svc/user/User"/>
	      	<s:url id="videopage" action="overview" namespace="/svc/multimedia/Video" />
		    <p class="play_info"><s:a cssClass="play_info_a" href="%{mainpage}">SeeWorld</s:a> &gt; <s:a cssClass="play_info_a" href="%{videopage}">视频</s:a> &gt; <cite class="play_info_time"><s:property value="title"/></cite>
		    		<span class="play_count">播放：<em class="play_count_em"><s:property value="clickCount"/></em></span>
					<span class="play_collect">收藏：<em class="play_collect_em"><s:property value="collectedCount"/></em></span>
					<span class="play_comment">评论：<em class="play_comment_em"><s:property value="commentsCount"/></em></span> </p>
		  </div>
		  <div class="play_player"> 
		    <!--视频播放区--> 
		    <embed width="100%" height="100%" type="application/x-shockwave-flash" wmode="transparent"
		    	flashvars="&amp;src=<s:property value="path" />&amp;autoHideControlBar=true&amp;streamType=recorded&amp;autoPlay=true" 
		    	pluginspage="http://www.adobe.com/go/getflashplayer" allowfullscreen="true" name="StrobeMediaPlayback" 
		    	bgcolor="#000000" allowscriptaccess="always" quality="high" src="swfs/StrobeMediaPlayback.swf"/>
		  </div>
		</div>
		<div class="play_column">
		  <div class="play_column_l">
		    <div class="introduce">
		      <h3 class="introduce_h3"></h3>
		      <p class="introduce_content"><s:property value="introduction"/></p>
		    </div>
		    <div class="comments">
		      <h3 class="comments_h3"></h3>
		      <div class="comments_main">
		        <div class="to_comment clearfix">
		          <div class="user_img_box"><img alt="用户头像" width="50px" src="/avatars/<s:property value="userModel.photoPath"/>"/></div>
		          <div class="input_box">
		            <textarea id="txtArea" class="comment_textarea" maxlength="140">随便说一些什么吧\(≧▽≦)/</textarea>
		            <i class="graphic_arrow"></i> </div>
		          <a class="submit_btn" title="提交" href="javascript:void(0)" id="CommentButton" onclick="toSubmit()">提 交</a> </div>
		        <ul class="comment_list" id="comment_list">
		        </ul>
		        <div class="page_nav">
		        </div>
		      </div>
		    </div>
		  </div>
		  <div class="play_column_r">
		    <div class="taste">
		      <h3 class="taste_h3"></h3>
		      <div class="sidebar_list">
		        <ol class="sidebar_ol">
		          <s:iterator value="relativeVideos">
					<s:url id="playTarget" action="play"
							namespace="/svc/multimedia/Multimedia">
					  <s:param name="id" value="id"></s:param>
					  <s:param name="resourceId" value="resourceId"></s:param>
					</s:url>
		            <li class="sidebar_li_vital">
		              <div class="cover_small_box"><s:a class="v_img_a" href="%{playTarget}">
		              	<img width="112px" height="63px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
		              </s:a></div>
		              <div class="v_info_box">
		                <p class="v_name"><s:a class="v_name_a" href="%{playTarget}"><s:property value="title" /></s:a></p>
		                <p class="v_info"><span class="click_count"><s:property value="clickCount"/></span><span class="comment_count"><s:property value="commentsCount"/></span></p>
		              </div>
		            </li>
				  </s:iterator>
		        </ol>
		      </div>
		    </div>
		  </div>
		</div>
		<!----------------------------------------------------------------------------------->
		<!--------------------------------------footer--------------------------------------->
		<s:include value="../common/footer.jsp"></s:include>
		<!----------------------------------------------------------------------------------->	
	</body>
</html>
