<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><s:property value="queryKey"/>-搜索结果 SeeWorld-TJU</title>
	<link href="<s:property value="basePath"/>style/init.css" rel="stylesheet" type="text/css" />
	<link href="<s:property value="basePath"/>style/detail_page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript" src="<s:property value="basePath"/>script/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" language="javascript" src="<s:property value="basePath"/>script/seeworld_js.js" ></script>
	<script type="text/javascript" language="javascript" src="<s:property value="basePath"/>script/get_multi_detail_ajax.js" ></script>
	<script type="text/javascript" language="javascript" src="<s:property value="basePath"/>script/toggle_text.js" ></script>
	<script type="text/javascript" language="javascript" >
		$(document).ready(function(){
			menuControl();
			bindclick();
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
			
			initToggle("v_introduce", 100, true);
		};
		function getAjaxResult(newNum){
			if('<s:property value="queryCategory"/>' == "movie"){
				findMovieByNameDisplayByPage({queryKey:'<s:property value="queryKey"/>',
							len:'<s:property value="page.len" />',
							currentPage:newNum});
			}
			else if('<s:property value="queryCategory"/>' == "serial"){
				findSerialByNameDisplayByPage({queryKey:'<s:property value="queryKey"/>',
							len:'<s:property value="page.len" />',
							currentPage:newNum});
			}
			else if('<s:property value="queryCategory"/>' == "video"){
				findVideoByNameDisplayByPage({queryKey:'<s:property value="queryKey"/>',
							len:'<s:property value="page.len" />',
							currentPage:newNum});
			}
		}
	</script>
  </head>
  
  <body>
  	<s:include value="../common/header.jsp"></s:include>
    <div class="main">
	  <div class="main_col_l">
	    <div class="details search_result">
	      <s:url id="mainpage" action="beforeLogin" namespace="/svc/user/User"/>
	      <s:url id="moviepage" action="overview" namespace="/svc/multimedia/Movie" />
	      <h3 class="details_h3"><div class="trace_mark">
	      	<s:a cssClass="details_h3_a" href="%{mainpage}">SeeWorld</s:a> &gt; 
	      	<s:a cssClass="details_h3_a" href="%{moviepage}">电影</s:a> &gt; 
	      	<span class="details_h3_span">搜索结果</span></div>
	      </h3>
	      <div class="details_container">
	      	<s:if test="movieListForView.isEmpty()">
			<div class="results_none_reply">
				<div class="search_tips"> 搜索 <strong class="search_highlight"><s:property value="queryKey"/></strong> 找到 <em class="info_data">0</em> 个视频。 </div>
                <p class="reply_note">找不到和您的查询 <strong class="search_highlight">双星物语2</strong> 相符的内容或信息。</p>
                <dl class="search_advice">
                    <dt class="search_advice_title">建议：</dt>
                    <dd class="search_advice_item">请检查输入字词有无错误。</dd>
                    <dd class="search_advice_item">请尝试其他的查询词。</dd>
                    <dd class="search_advice_item">请改用较常见的字词。</dd>
                    <dd class="search_advice_item">请减少查询字词的数量。</dd>
                </dl>
            </div>
	      	</s:if>
	      	<s:else>
	      	<div class="search_tips"> 搜索 <strong class="search_highlight"><s:property value="queryKey"/></strong>
	      				 找到 <em class="info_data"><s:property value="page.len"/></em> 个视频。 </div>
	        <ul class="details_list">
	          <s:if test='queryCategory == "movie"'>
	            <s:iterator value="movieListForView">
		          <li class="details_li">
		            <s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
		            	<s:param name="id" value="id"></s:param>
		            	<s:param name="resourceId" value="resourceId"></s:param>
		            </s:url>
		            <div class="cover_vertical_box"> <s:a cssClass="v_img_a" title="视频封面" href="%{playTarget}"><img width="114px" height="152px" alt="视频封面" title="视频封面" src="<s:property value="basePath"/>posters/<s:property value="image"/>"/></s:a> </div>
		            <div class="v_content_vertical_box">
		              <p class="v_name"><s:a cssClass="v_name_a"  title="视频标题" href="%{playTarget}"><s:property value="title"/></s:a></p>
		              <p class="v_info"><span class="info_play">播放：<s:property value="clickCount"/></span><span class="info_collect"> 收藏：0</span><span class="info_comment">评论：0</span><span class="info_date">日期：<s:property value="addTime"/></span></p>
		              <p class="v_introduce"><s:property value="introduction"/></p>
		            </div>
		          </li>
	            </s:iterator>
	          </s:if>
	          <s:if test='queryCategory == "serial"'>
	            <s:iterator value="serialListForView">
		          <li class="details_li">
		            <s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
		            	<s:param name="id" value="id"></s:param>
		            	<s:param name="resourceId" value="resourceId"></s:param>
		            </s:url>
		            <div class="cover_vertical_box"> <s:a cssClass="v_img_a" title="视频封面" href="%{playTarget}"><img width="114px" height="152px" alt="视频封面" title="视频封面" src="<s:property value="basePath"/>posters/<s:property value="image"/>"/></s:a> </div>
		            <div class="v_content_vertical_box">
		              <p class="v_name"><s:a cssClass="v_name_a"  title="视频标题" href="%{playTarget}"><s:property value="title"/></s:a></p>
		              <p class="v_info"><span class="info_play">播放：<s:property value="clickCount"/></span><span class="info_collect"> 收藏：0</span><span class="info_comment">评论：0</span><span class="info_date">日期：<s:property value="addTime"/></span></p>
		              <p class="v_introduce"><s:property value="introduction"/></p>
		            </div>
		          </li>
	            </s:iterator>
	          </s:if>
	          <s:if test='queryCategory == "video"'>
	            <s:iterator value="videoListForView">
		          <li class="details_li">
		            <s:url id="playTarget" action="play" namespace="/svc/multimedia/Multimedia">
		            	<s:param name="id" value="id"></s:param>
		            	<s:param name="resourceId" value="resourceId"></s:param>
		            </s:url>
		            <div class="cover_vertical_box"> <s:a cssClass="v_img_a" title="视频封面" href="%{playTarget}"><img width="114px" height="152px" alt="视频封面" title="视频封面" src="<s:property value="basePath"/>posters/<s:property value="image"/>"/></s:a> </div>
		            <div class="v_content_vertical_box">
		              <p class="v_name"><s:a cssClass="v_name_a"  title="视频标题" href="%{playTarget}"><s:property value="title"/></s:a></p>
		              <p class="v_info"><span class="info_play">播放：<s:property value="clickCount"/></span><span class="info_collect"> 收藏：0</span><span class="info_comment">评论：0</span><span class="info_date">日期：<s:property value="addTime"/></span></p>
		              <p class="v_introduce"><s:property value="introduction"/></p>
		            </div>
		          </li>
	            </s:iterator>
	          </s:if>
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
	        </s:else>
	      </div>
	    </div>
	  </div>
	  <div class="main_col_r">
	    <div class="random">
	      <h3 class="random_h3">随机推荐</h3>
	      <div class="sidebar_list">
	        <ol class="sidebar_ol">
		      <s:iterator value="RandomList">
		          <li class="sidebar_li_vital">
		            <s:url id="playTarget" action="play" namespace="/multimedia/Multimedia">
		            	<s:param name="id" value="id"></s:param>
		            	<s:param name="resourceId" value="resourceId"></s:param>
		            </s:url>
		            <div class="cover_small_box"> <s:a cssClass="v_img_a" title="视频封面" href="%{playTarget}"><img width="112px" height="63px" alt="视频封面" title="视频封面" src="<s:property value="basePath"/>posters/<s:property value="image"/>"/></s:a> </div>
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
