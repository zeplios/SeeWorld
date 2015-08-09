<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>SeeWorld电影——天津大学视频中心</title>
		<base href="<s:property value="basePath"/>"/>
		<meta name="description" content="SeeWorld 天津大学视频中心" />

		<link href="style/content_list_page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="script/jquery-1.7.2.min.js" ></script>
		<script type="text/javascript" src="script/seeworld_js.js" ></script>
		<script type="text/javascript">
			$(document).ready(function(){
				menuControl();
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
		  	<div class="region_western">
				<s:url id="getEa" action="eaNewest" namespace="/svc/multimedia/Serial"/>
	        	<h3 class="region_western_h3">
	        		<s:a cssClass="region_western_h3_a" title="欧美最新" href="%{getEa}">欧美最新</s:a>
	        		<s:a cssClass="updates_more" title="更多" href="%{getEa}">更多</s:a></h3>
	           	<div class="video_container">
		            <ul class="video_list">
		            	<s:iterator value="EA_NewestList" status="status">
		            		<s:if test="singleSerials.isEmpty()">
		            		<s:url id="playTarget"/>
		            		</s:if>
		            		<s:else>
		            		<s:url id="playTarget" action="play"
								namespace="/svc/multimedia/Multimedia">
								<s:param name="id" value="singleSerials[0].id"></s:param>
								<s:param name="resourceId" value="singleSerials[0].resourceId"></s:param>
							</s:url>
		            		</s:else>
			            	<li class="video_li">
		           				<div class="cover_large_box">
		                    		<s:a cssClass="v_img_a" href="%{playTarget}">
		                    			<img width="160px" height="90px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
		                   	 		</s:a>
		                        	<!-- <div class="v_time">22:24</div>  -->
		                    	</div>
		                    	<p class="v_name">
		                    		<s:a cssClass="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a>
		                    	</p>
		                    	<p class="v_info">
		                    		<span class="click_count"><s:property value="clickCount" /></span>
		                    		<span class="comment_count"><s:property value="commentsCount" /></span>
		                    	</p>
		           			</li>
		           			<s:if test="#status.count % 3 == 0">
		           				<div class="clear"></div>
		           			</s:if>
		                </s:iterator>
		            </ul>
	            </div>
	        </div>
		  	<div class="region_hk">
				<s:url id="getHt" action="htNewest" namespace="/svc/multimedia/Serial"/>
	        	<h3 class="region_hk_h3">
	        		<s:a cssClass="region_hk_h3_a" title="港台最新" href="%{getHt}">港台最新</s:a>
	        		<s:a cssClass="updates_more" title="更多" href="%{getHt}">更多</s:a></h3>
	           	<div class="video_container">
		            <ul class="video_list">
		            	<s:iterator value="HT_NewestList" status="status">
		            		<s:url id="playTarget" action="play"
								namespace="/svc/multimedia/Multimedia">
								<s:param name="id" value="id"></s:param>
								<s:param name="resourceId" value="resourceId"></s:param>
							</s:url>
			            	<li class="video_li">
		           				<div class="cover_large_box">
		                    		<s:a cssClass="v_img_a" href="%{playTarget}">
		                    			<img width="160px" height="90px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
		                   	 		</s:a>
		                        	<!-- <div class="v_time">22:24</div>  -->
		                    	</div>
		                    	<p class="v_name">
		                    		<s:a cssClass="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a>
		                    	</p>
		                    	<p class="v_info">
		                    		<span class="click_count"><s:property value="clickCount" /></span>
		                    		<span class="comment_count"><s:property value="commentsCount" /></span>
		                    	</p>
		           			</li>
		           			<s:if test="#status.count % 3 == 0">
		           				<div class="clear"></div>
		           			</s:if>
		                </s:iterator>
		            </ul>
	            </div>
	        </div>
		  	<div class="region_native">
				<s:url id="getMl" action="mlNewest" namespace="/svc/multimedia/Serial"/>
	        	<h3 class="region_native_h3">
	        		<s:a cssClass="region_native_h3_a" title="大陆最新" href="%{getMl}">大陆最新</s:a>
	        		<s:a cssClass="updates_more" title="更多" href="%{getMl}">更多</s:a></h3>
	           	<div class="video_container">
		            <ul class="video_list">
		            	<s:iterator value="ML_NewestList" status="status">
		            		<s:url id="playTarget" action="play"
								namespace="/svc/multimedia/Multimedia">
								<s:param name="id" value="id"></s:param>
								<s:param name="resourceId" value="resourceId"></s:param>
							</s:url>
			            	<li class="video_li">
		           				<div class="cover_large_box">
		                    		<s:a cssClass="v_img_a" href="%{playTarget}">
		                    			<img width="160px" height="90px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
		                   	 		</s:a>
		                        	<!-- <div class="v_time">22:24</div>  -->
		                    	</div>
		                    	<p class="v_name">
		                    		<s:a cssClass="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a>
		                    	</p>
		                    	<p class="v_info">
		                    		<span class="click_count"><s:property value="clickCount" /></span>
		                    		<span class="comment_count"><s:property value="commentsCount" /></span>
		                    	</p>
		           			</li>
		           			<s:if test="#status.count % 3 == 0">
		           				<div class="clear"></div>
		           			</s:if>
		                </s:iterator>
		            </ul>
	            </div>
	        </div>
		  	<div class="region_jp">
				<s:url id="getJk" action="jkNewest" namespace="/svc/multimedia/Serial"/>
	        	<h3 class="region_jp_h3">
	        		<s:a cssClass="region_jp_h3_a" title="欧美最新" href="%{getJk}">日韩最新</s:a>
	        		<s:a cssClass="updates_more" title="更多" href="%{getJk}">更多</s:a></h3>
	           	<div class="video_container">
		            <ul class="video_list">
		            	<s:iterator value="JP_NewestList" status="status">
		            		<s:url id="playTarget" action="play"
								namespace="/svc/multimedia/Multimedia">
								<s:param name="id" value="id"></s:param>
								<s:param name="resourceId" value="resourceId"></s:param>
							</s:url>
			            	<li class="video_li">
		           				<div class="cover_large_box">
		                    		<s:a cssClass="v_img_a" href="%{playTarget}">
		                    			<img width="160px" height="90px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
		                   	 		</s:a>
		                        	<!-- <div class="v_time">22:24</div>  -->
		                    	</div>
		                    	<p class="v_name">
		                    		<s:a cssClass="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a>
		                    	</p>
		                    	<p class="v_info">
		                    		<span class="click_count"><s:property value="clickCount" /></span>
		                    		<span class="comment_count"><s:property value="commentsCount" /></span>
		                    	</p>
		           			</li>
		           			<s:if test="#status.count % 3 == 0">
		           				<div class="clear"></div>
		           			</s:if>
		                </s:iterator>
		            </ul>
	            </div>
	        </div>
		  </div>
		  <div class="main_col_r">
		    <div class="popular">
		    	<s:url id="getRank" action="rank" namespace="/svc/multimedia/Serial"/>
	        	<h3 class="popular_h3">
	        	  <s:a cssClass="popular_h3_a" title="点击排行榜" href="%{getRank}"></s:a>
	        	  <s:a cssClass="updates_more" title="更多" href="%{getRank}">更多</s:a>
	        	</h3>
	            <div class="sidebar_list">
		            <ol class="sidebar_ol">
		            	<s:iterator value="rankList" status="status">
		            		<s:url id="playTarget" action="play"
								namespace="/svc/multimedia/Multimedia">
								<s:param name="id" value="id"></s:param>
								<s:param name="resourceId" value="resourceId"></s:param>
							</s:url>
		           			<s:if test="#status.index < 3">
		           				<li class="sidebar_li_vital">
		                			<div class="cover_small_box">
		                    			<s:a cssClass="v_img_a" href="%{playTarget }">
		                    				<img width="112px" height="63px" alt="视频封面" src="/posters/<s:property value="image"/>"/>
		                    			</s:a>
		                    		</div>
		                    		<div class="v_info_box">
			                    		<p class="v_name">
			                    			<s:a cssClass="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a>
			                    		</p>
			                        	<p class="v_info">
			                        		<span class="click_count"><s:property value="clickCount" /></span>
			                        		<span class="comment_count"><s:property value="commentsCount" /></span>
			                        	</p>
			                        </div>
		                        </li>
		                   	</s:if>
		                   	<s:else>
		                   		<li class="sidebar_li">
		                   			<p class="v_name">
		                    			<s:a cssClass="v_name_a" title="%{title}" href="%{playTarget}"><s:property value="title" /></s:a>
		                    		</p>
		                        	<p class="v_info">
		                        		<span class="click_count"><s:property value="clickCount" /></span>
		                        		<span class="comment_count"><s:property value="commentsCount" /></span>
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