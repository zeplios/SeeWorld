<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>视频 SeeWorld-TJU</title>
		<base href="<s:property value="basePath"/>"/>
		<link href="style/content_list_page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="script/jquery-1.7.2.min.js" ></script>
		<script type="text/javascript" src="script/seeworld_js.js" ></script>
		<script type="text/javascript" src="script/toggle_text.js" ></script>
		<script type="text/javascript">
			$(document).ready(function(){
				menuControl();
				initToggle("main_col_l .v_name_a", 8, false);
			});
		</script>
	</head>

	<body>
		<!-------------------------------------header---------------------------------------->
		<s:include value="../common/header.jsp"></s:include>
		<!----------------------------------------------------------------------------------->
		
		<!--------------------------------------main----------------------------------------->
		<div class="main">
		  <div class="main_col_l">
		    <div class="updates">
		      <h3 class="updates_h3"><a class="updates_h3_a" title="近期更新" href="detail_page.html"></a>近期更新</h3>
		      <div class="video_container">
		        <ul class="video_list">
		          <s:iterator value="contentList" status="status">
					<s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
						<s:param name="id" value="id"></s:param>
						<s:param name="resourceId" value="resourceId"></s:param>
					</s:url>
					<li class="video_li">
						<div class="cover_large_box"><s:a class="v_img_a" href="%{playTarget}">
							<img width="160px" height="90px" alt="视频封面" src="/posters/<s:property value="image"/>"/></s:a>
		              		<!-- <div class="v_time"><s:property value="size" /></div> -->
		            	</div>
		            	<p class="v_name"><s:a class="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a></p>
		            	<p class="v_info"><span class="click_count"><s:property value="clickCount"/></span>
		            					<span class="comment_count"><s:property value="recommendedCount"/></span></p>
					</li>
				  </s:iterator>
		        </ul>
		      </div>
		    </div>
		    <div class="recommend">
		      <h3 class="recommend_h3"><a class="recommend_h3_a" title="热门推荐" href="#"></a>热门推荐</h3>
		      <div class="video_container">
		        <ul class="video_list">
		          <s:iterator value="Today_NewestList" status="status">
					<s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
						<s:param name="id" value="id"></s:param>
						<s:param name="resourceId" value="resourceId"></s:param>
					</s:url>
					<li class="video_li">
						<div class="cover_large_box"><s:a class="v_img_a" href="%{playTarget}">
								<img width="160px" height="90px" alt="视频封面" src="/posters/<s:property value="image"/>"/></s:a>
		              		<div class="v_time"><s:property value="size" /></div>
		            	</div>
		            	<p class="v_name"><s:a class="v_name_a"  title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a></p>
		            	<p class="v_info"><span class="click_count"><s:property value="clickCount"/></span>
		            					<span class="comment_count"><s:property value="recommendedCount"/></span></p>
					</li>
				  </s:iterator>
		        </ul>
		      </div>
		    </div>
		  </div>
		  <div class="main_col_r">
		    <div class="popular">
		      <h3 class="popular_h3"><a class="popular_h3_a" title="周榜单" href="#"></a>周榜单</h3>
		      <div class="sidebar_list">
		        <ol class="sidebar_ol">
		          <s:iterator value="rankList" status="status">
					<s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
						<s:param name="id" value="id"></s:param>
						<s:param name="resourceId" value="resourceId"></s:param>
					</s:url>
					<s:if test="#status.index < 3">
						<li class="sidebar_li_vital">
			              <div class="cover_small_box"><s:a class="v_img_a" href="%{playTarget}">
			              		<img width="112px" height="63px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
			              </s:a></div>
			              <div class="v_info_box">
			                <p class="v_name"><s:a class="v_name_a" href="%{playTarget}"><s:property value="title" /></s:a></p>
			                <p class="v_info">
			                	<span class="click_count"><s:property value="clickCount" /></span>
			                	<span class="comment_count"><s:property value="commentsCount" /></span>
			                </p>
			              </div>
			            </li>
		            </s:if>
		            <s:else>
		            	<li class="sidebar_li">
			                <p class="v_name"><s:a class="v_name_a"  title="视频标题" href="%{playTarget}"><s:property value="title" /></s:a></p>
			                <p class="v_info">
			                	<span class="click_count"><s:property value="clickCount" /></span>
			                	<span class="comment_count"><s:property value="commentCount" /></span>
			                </p>
			            </li>
		            </s:else>
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