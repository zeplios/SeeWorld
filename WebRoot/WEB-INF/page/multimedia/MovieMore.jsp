<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>电影-近期更新 SeeWorld-TJU</title>
    <base href="<s:property value="basePath"/>"/>
	<link href="style/init.css" rel="stylesheet" type="text/css" />
	<link href="style/detail_page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript" src="script/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" language="javascript" src="script/seeworld_js.js" ></script>
	<script type="text/javascript" language="javascript" src="script/load_more.js" ></script>
	<script type="text/javascript" language="javascript" src="script/toggle_text.js" ></script>
	<script type="text/javascript" language="javascript" >
		$(document).ready(function(){
			menuControl();
			bindclick();
			initToggle("v_introduce", 150, true);
		});
		function bindclick(){
			$("#prev").bind("click",function(){
				var i = parseInt($(".currentPage").html());
				if(i == 1){
					return;
				}else{
					i = i - 1;
				}
				getAjaxResult(i);
			});
			$("#next").bind("click",function(){
				var i = parseInt($(".currentPage").html());
				if(i == <s:property value="page.pagenum"/>){
					return;
				}else{
					i = i + 1;
				}
				getAjaxResult(i);
			});
			$(".page_nav_li_a").bind("click",function(){
				getAjaxResult($(this).text());
			});
		};
		function getAjaxResult(newNum){
			<s:if test="type==0">
				getMovieContentList({orderBy : 2,/*ORDER_BY_ADDTIME*/
					areaAndCountryId : 1,
					len : '<s:property value="page.len" />',
					currentPage : newNum});
			</s:if>
			<s:elseif test="type==1">
				getMovieContentList({orderBy : 2,/*ORDER_BY_ADDTIME*/
					areaAndCountryId : 2,
					len : '<s:property value="page.len" />',
					currentPage : newNum});
			</s:elseif>
			<s:elseif test="type==2">
				getMovieContentList({orderBy : 2,/*ORDER_BY_ADDTIME*/
					areaAndCountryId : 3,
					len : '<s:property value="page.len" />',
					currentPage : newNum});
			</s:elseif>
			<s:elseif test="type==3">
				getMovieContentList({orderBy : 2,/*ORDER_BY_ADDTIME*/
					areaAndCountryId : 4,
					len : '<s:property value="page.len" />',
					currentPage : newNum});
			</s:elseif>
			<s:elseif test="type==4">
				getMovieContentList({orderBy : 3,/*ORDER_BY_CLICKCOUNT*/
					len:'<s:property value="page.len" />',
					currentPage:newNum});
			</s:elseif>
		}
	</script>
  </head>
  
  <body>
  	<s:include value="../common/header.jsp"></s:include>
    <div class="main">
	  <div class="main_col_l">
	    <div class="details">
	      <s:url id="mainpage" action="beforeLogin" namespace="/svc/user/User"/>
	      <s:url id="moviepage" action="overview" namespace="/svc/multimedia/Movie" />
	      <h3 class="details_h3"><div class="trace_mark">
                <s:a cssClass="details_h3_a" title="" href="%{mainpage}">SeeWorld</s:a> &gt; <s:a cssClass="details_h3_a" title="" href="%{moviepage}">电影</s:a> &gt; <span class="details_h3_span">
				<s:if test="type==0">欧美最新</s:if>
				<s:elseif test="type==1">港台最新</s:elseif>
				<s:elseif test="type==2">大陆最新</s:elseif>
				<s:elseif test="type==3">日韩最新</s:elseif>
				<s:elseif test="type==4">周榜单</s:elseif>
				</span> </div></h3>
	      <div class="details_container">
	        <ul class="details_list">
	          <s:iterator value="contentList">
		          <li class="details_li">
		            <s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
		            	<s:param name="id" value="id"></s:param>
		            	<s:param name="resourceId" value="resourceId"></s:param>
		            </s:url>
		            <div class="cover_vertical_box"> <s:a cssClass="v_img_a" title="视频封面" href="%{playTarget}"><img width="114px" height="152px" alt="视频封面" title="视频封面" src="/posters/<s:property value="image"/>"/></s:a> </div>
		            <div class="v_content_vertical_box">
		              <p class="v_name"><s:a cssClass="v_name_a"  title="视频标题" href="%{playTarget}"><s:property value="title"/></s:a></p>
		              <p class="v_info"><span class="info_play">播放：<s:property value="clickCount"/></span><span class="info_collect"> 收藏：0</span><span class="info_comment">评论：0</span><span class="info_date">日期：<s:property value="addTime"/></span></p>
		              <p class="v_introduce"><s:property value="introduction"/></p>
		            </div>
		          </li>
	          </s:iterator>
	        </ul>
	        <div class="page_nav">
	          <p class="page_info">共<s:property value="page.pagenum"/>页 / <s:property value="page.len"/>条记录</p>
	          <ul class="page_nav_list">
	            <s:iterator value="page.pagelist" id="pageNumber" status="status">
				  <s:if test="page.currentpage==#status.count">
					<li class="page_nav_li currentPage"><s:property /></li>
				  </s:if>
				  <s:else>
					<li class="page_nav_li"><s:a cssClass="page_nav_li_a" href="javascript:void(0)"><s:property /></s:a> </li>
				  </s:else>
				</s:iterator>
	          </ul>
	          <div class="page_btn">
		          <s:if test="page.currentpage==1">
					<span class="btn_prev_span">上一页</span>
				  </s:if>
				  <s:else>
					<a class="btn_next_a" id="prev" href="javascript:void(0)">上一页</a>
				  </s:else>
		          <s:if test="page.currentpage==page.pagenum">
					<span class="btn_prev_span">下一页</span>
				  </s:if>
				  <s:else>
					<a class="btn_next_a" id="next" href="javascript:void(0)">下一页</a>
				  </s:else>
	           </div>
	        </div>
	      </div>
	    </div>
	  </div>
	  <div class="main_col_r">
	    <div class="random">
	      <h3 class="random_h3"></h3>
	      <div class="sidebar_list">
	        <ol class="sidebar_ol">
		      <s:iterator value="RandomList">
		          <li class="sidebar_li_vital">
		            <s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
		            	<s:param name="id" value="id"></s:param>
		            	<s:param name="resourceId" value="resourceId"></s:param>
		            </s:url>
		            <div class="cover_small_box"> <s:a cssClass="v_img_a" title="视频封面" href="%{playTarget}"><img width="112px" height="63px" alt="视频封面" title="视频封面" src="/posters/<s:property value="image"/>"/></s:a> </div>
		            <div class="v_info_box">
		              <p class="v_name"><s:a cssClass="v_name_a"  title="视频标题" href="%{playTarget}"><s:property value="title"/></s:a></p>
		              <p class="v_info"><span class="click_count"><s:property value="clickCount"/></span><span class="comment_count"><s:property value="recommandCount"/></span></p>
		            </div>
		          </li>
	          </s:iterator>
	        </ol>
	      </div>
	    </div>
	  </div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
  </body>
</html>
