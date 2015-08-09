<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>SeeWorld电影——天津大学视频中心</title>
		<meta name="keywords" content="" />
		<meta name="description" content="SeeWorld 天津大学视频中心" />
		<link href="<s:property value="basePath"/>styles/layout.css"
			type="text/css" rel="stylesheet" />
		<link href="<s:property value="basePath"/>styles/style.css"
			type="text/css" rel="stylesheet" />
		<link href="<s:property value="basePath"/>styles/film.css"
			type="text/css" rel="stylesheet" />
		<link href="<s:property value="basePath"/>styles/collect.css"
			type="text/css" rel="stylesheet" />
		<link href="<s:property value="basePath"/>styles/introduce.css"
			type="text/css" rel="stylesheet" />
		<script src="<s:property value="basePath"/>js/introduce.js"
			type="text/javascript"></script>
		<script src="<s:property value="basePath"/>js/collect.js"
			type="text/javascript"></script>
		<script src="<s:property value="basePath"/>js/jquery.min.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="<s:property value="basePath"/>js/seeworld_multimedia.js"></script>
		<script type="text/javascript"
			src="<s:property value="basePath"/>js/alert.js"></script>
					<script type="text/javascript"
			src="<s:property value="basePath"/>js/jquery.blockUI.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<s:property value="basePath"/>js/msgBox/jquery.msgbox.css" />
		<script type="text/javascript"
			src="<s:property value="basePath"/>js/msgBox/jquery.dragndrop.js"></script>
		<script type="text/javascript"
			src="<s:property value="basePath"/>js/msgBox/jquery.msgbox.js"></script>
		<script type="text/javascript">
		$(function(){
			bindclick();
			});
		function bindclick(){
			$("#prev").bind("click",function(){
				var i = parseInt($(".thisclass").html());
				if(i==1){
					return;
				}else{
					i=i-1;
				}
				getMovieContentList({categoryId:'<s:property value="category.id"/>',
									publishedYear:'<s:property value="publishedYear" />',
									areaAndCountryId:'<s:property value="areaAndCountry.id" />',
									orderBy:$('#sort').val(),
									//totalPages:'<s:property value="page.pagenum" />',
									len:'<s:property value="page.len" />',
									currentPage:i});
			});
			$("#next").bind("click",function(){
				var i = parseInt($(".thisclass").html());
				if(i==<s:property value="page.pagenum" />){
					return;
				}else{
					i=i+1;
				}
				getMovieContentList({categoryId:'<s:property value="category.id"/>',
									publishedYear:'<s:property value="publishedYear" />',
									areaAndCountryId:'<s:property value="areaAndCountry.id" />',
									orderBy:$('#sort').val(),
									//totalPages:'<s:property value="page.pagenum" />',
									len:'<s:property value="page.len" />',
									currentPage:i});
			});
			$(".pageNum").bind("click",function(){
				getMovieContentList({categoryId:'<s:property value="category.id"/>',publishedYear:'<s:property value="publishedYear" />',areaAndCountryId:'<s:property value="areaAndCountry.id" />',orderBy:$('#sort').val(),len:'<s:property value="page.len" />',currentPage:$(this).text()});
			});
			$('#sort').bind("change",function(){
				getMovieContentList({categoryId:'<s:property value="category.id"/>',publishedYear:'<s:property value="publishedYear" />',areaAndCountryId:'<s:property value="areaAndCountry.id" />',orderBy:$(this).val(),len:'<s:property value="page.len" />',currentPage:1});
			});
		};
		
</script>
	</head>

	<body>
		<!-- pop win -->
		<div id="popDiv" class="mydiv">
			<div class="pop_title">
				是否确认收藏？
			</div>
			<form action="" method="post">
				<div class="result_list">
					<div class="V_photo">
						<a href="#"><img alt="" /> </a>
					</div>
					<div class="V_information">
						<div>
							<div class="list11">
								<a href="#">记住我[1集]</a>
							</div>
						</div>
						<div class="clear"></div>
						<div>
							<div class="list21">
								年代:
							</div>
							<div class="list22">
								2010
							</div>
							<div class="list21">
								地区:
							</div>
							<div class="list22">
								美国
							</div>
							<div class="list21">
								类型:
							</div>
							<div class="list22">
								喜剧
							</div>
						</div>
						<div class="clear"></div>
						<div>
							<div class="list21">
								主演:
							</div>
							<div class="list22">
								刘德华 谢霆锋 张曼玉
							</div>
						</div>
						<div class="clear"></div>
						<div>
							<div class="list31">
								介绍:
							</div>
							<div class="list32">
								由于今年北京站驻工大售票处工作人员不足，北京站售票人员不再来校面对学生逐一订票。为了解决今年寒假学生北京站订票问题，学工部与北京站驻工大售票处联系协商，协商，采用以下……(只显示100个汉字)
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="pop_button">
					<input type="submit" value="确定" class="pop_button_submit" />
					<input type="submit" value="取消" class="pop_button_submit" />
				</div>
			</form>
		</div>
		<div id="bg" class="bg" style="display: none;"></div>
		<iframe id='popIframe' class='popIframe' frameborder='0'></iframe>
		<!-- pop win finished-->
		<!-- pop1 win -->
		<div id="popDiv1" class="mydiv">
			<div class="pop_title">
				将此部电影推荐给谁？
			</div>
			<div class="intro_container" id="recommend">
			</div>
		</div>
		<div id="bg1" class="bg" style="display: none;"></div>
		<iframe id='popIframe1' class='popIframe' frameborder='0'></iframe>
		<!-- pop1 win finished-->
		<div id="main">
		<div id="header">
			<!-- navi -->
			<s:include value="../common/nav.jsp"></s:include>
			<!-- end of nav -->
			</div>
			<!-- content -->
			<div id="content">
				<div class="clear">
					<span>&nbsp;&nbsp;当前位置：<a href="#">SeeWorld</a>&nbsp;&gt;&gt;&nbsp;电影</span>
				</div>
				<!-- left -->
				<div class="film_left">
						<div class="film_left_block">
							<div class="film_category title_inner">
								电影索引
							</div>
							<div class="film_category_content block_content_inner">
								<div class="as_category">
									<div class="as_category_title">
										&bull;&nbsp;按类型分类
									</div>
									<div>
										<s:url id="viewAllCategory" action="viewByCategory"
											namespace="/multimedia/Movie"></s:url>
										<div class="as_category_content">
											<s:a href="%{viewAllCategory}">全部</s:a>
										</div>
										<s:iterator value="categoryList" id="categorytList">
											<s:url id="viewMovie" action="viewByCategory"
												namespace="/multimedia/Movie">
												<s:param name="category.id" value="id" />
											</s:url>
											<div class="as_category_content">
												<s:a href="%{viewMovie}">
													<s:property value="name" />
												</s:a>
											</div>
										</s:iterator>
									</div>
								</div>
								<br />
								<div class="as_category">
									<div class="as_category_title">
										&bull;&nbsp;按国家/地区分类
									</div>
									<div>
										<s:url id="viewAllArea" action="viewByArea"
											namespace="/multimedia/Movie"></s:url>
										<div class="as_category_content">
											<s:a href="%{viewAllArea}">全部</s:a>
										</div>
										<s:iterator value="areaAndCountryList">
											<div class="as_category_content">
												<s:url id="viewMovie" action="viewByArea"
													namespace="/multimedia/Movie">
													<s:param name="areaAndCountry.id" value="id" />
												</s:url>
												<s:a href="%{viewMovie}">
													<s:property value="name" />
												</s:a>
											</div>
										</s:iterator>
									</div>
								</div>

								<div class="as_category">
									<div class="as_category_title">
										&bull;&nbsp;按年份分类
									</div>
									<div>
										<s:url id="viewAllYear" action="viewByYear"
											namespace="/multimedia/Movie"></s:url>
										<div class="as_category_content">
											<s:a href="%{viewAllYear}">全部</s:a>
										</div>
										<s:iterator value="publishedYearList" id="pulishYearList">
											<s:url id="viewMovie" action="viewByYear"
												namespace="/multimedia/Movie">
												<s:param name="publishedYear" value="pulishYearList" />
											</s:url>
											<div class="as_category_content">
												<s:a href="%{viewMovie}">
													<s:property />
												</s:a>
											</div>
										</s:iterator>

									</div>
								</div>

								<!-- 高级搜索-->
								<div class="clear"></div>
								<div class="dashed_line"></div>
								<div class="advance_search">
									<a href="advancedSearch.html" target="_blank">高级搜索&nbsp;&gt;&gt;</a>
								</div>

							<div class="clear"></div>
						</div>
					</div>
					</div>
					<!-- right -->
					<div class="action_right">
						<div class="action_right_block">
							<div class="action_title title_inner">
								<span class="fleft">
								&nbsp;&gt;&gt;
								<s:if test="%{category==null && publishedYear==null && areaAndCountry==null}">全部</s:if><s:else>
								<s:property value="category.name" />
								<s:property value="publishedYear" />
								<s:property value="areaAndCountry.name" />
								</s:else></span>
								<span class="fright"><label>排序方式:</label>
								<s:select id="sort" name="sort" label="" 
										list="#{'更新时间':'2','电影名称':'1','收藏次数':'4','点击次数':'3','推荐次数':'5'}" 
										listKey="value" listValue="key"></s:select>
								</span>
							</div>

							<div class="action_content">
									<!--结果列表-->
									<div class="content_list">
											<!--content by page-->
												<s:iterator value="contentList_For_Page"
													id="updateDetailList">
													<s:url id="playTarget" action="play"
														namespace="/multimedia/Multimedia">
														<s:param name="id" value="id"></s:param>
														<s:param name="resourceId" value="resourceId"></s:param>
													</s:url>
													<div class="action_list">
														<div class="action_list_pic">
															<s:a href="%{playTarget}">
																<img
																	src="<s:property value="basePath"/>posters/<s:property value="image"/>" />
															</s:a>
														</div>
														<div class="action_list_title">
															<div class="action_view">
																<a href="#" onclick="javascript:showDiv1()">分享</a>
															</div>
															<div class="action_view">
																<a href="#" onclick="javascript:showDiv()">收藏</a>
															</div>
															<div class="action_view">
																<s:a href="%{playTarget}">观看</s:a>
															</div>
															<div class="movie_title">
																<s:a href="%{playTarget}">
																	<s:property value="title" />
																</s:a>
															</div>
														</div>
														<div class="introduct_title">
															&nbsp;年代：
														</div>
														<div class="introduct_info1">
															<s:property value="year" />
														</div>
														<div class="introduct_title">
															&nbsp;地区：
														</div>
														<div class="introduct_info1">
															<s:property value="areaAndCountry" />
														</div>
														<div class="introduct_title">
															&nbsp;主演:
														</div>
														<div class="introduct_info2">
															<s:property value="actors" />
														</div>
														<div class="introduct_title">
															&nbsp;介绍:
														</div>
														<div class="introduct_info">
															<s:property
																value="introduction.toString().substring(0,25)" />
														</div>
														<div class="clear"></div>
													</div>
												</s:iterator>
												</div>
												<div class="clear"></div>
										<!--down-->
										<div class="action_page">
											<span> <a id="prev">&lt;</a> </span>
											<s:iterator value="page.pagelist" id="pageNumber"
												status="status">
												<s:if test="page.currentpage==#status.count">
													<span> <a class="pageNum thisclass"><s:property /></a> </span>
												</s:if>
												<s:else>
													<span> <a class="pageNum"><s:property /></a> </span>
												</s:else>
											</s:iterator>
											<span> <a id="next">&gt;</a> </span>
										</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			<!-- footer -->
			<s:include value="../common/footer.jsp"></s:include>
			</div>
	</body>
</html>



