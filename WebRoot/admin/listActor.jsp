<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />


		<title>演员列表</title>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>/admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>/admin/scripts/jquery.dataTables.js"></script>
		<script type="text/javascript" language="javascript" charset="utf-8"
			src="<%=basePath%>/admin/scripts/FixedHeader.js"></script>
		<script type="text/javascript" language="javascript" charset="utf-8"
			src="<%=basePath%>/admin/scripts/seeworld_bg.js"></script>
		<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		//getMultimediaList("Movie");
		});
</script>

		<link href="<%=basePath%>/admin/styles/table_main.css"
			rel="stylesheet" type="text/css">
		<link href="<%=basePath%>/admin/styles/common.css" rel="stylesheet"
			type="text/css">
	</head>
	<body>
		<div id="containertable">
			<h2>
				全部演员列表
			</h2>


			<form method="post">

				<div>
					<a class="add"
						href="<%=basePath%>svc/multimedia/Actor/preAdd.action">添加新演员</a>
				</div>
				<br />
				<div id="quicksearch">
					快速搜索:
					<input type=text />
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="display"
					id="example">
					<thead>
						<tr>
							<th></th>
							<th>
								演员名称
							</th>
							<th>
								英语名
							</th>
							<th>
								地区
							</th>
							<th>
								添加时间
							</th>
							<th>
								编辑
							</th>
							<th>
								删除
							</th>
						</tr>
					</thead>
					<tbody id="content_list">
						<s:iterator value="actorList" status="st">
							<tr <s:if test="#st.odd">class="tb_bg_odd"</s:if>
								<s:else>class="tb_bg_even"</s:else>>
								<td>
									<input type="checkbox" />
								</td>
								<td>
									<s:property value="name" />
								</td>
								<td>
									<s:property value="alias" />
								</td>
								<td>
									<s:property value="areaAndCountry.name" />
								</td>
								<td>
									<s:date name="addTime" format="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<a
										href="/SeeWorld/svc/multimedia/Actor/preUpdate.action?targetId=<s:property value="id" />">编辑</a>
								</td>
								<td>
									<a class="add"
										href="<%=basePath%>svc/multimedia/Actor/delete.action?targetId=<s:property value="id" />"><img
											src="<%=basePath%>/admin/images/icons/cross_circle.png" /> </a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					<tfoot>
						<tr>
							<tr>
								<th></th>
								<th>
									演员名称
								</th>
								<th>
								英语名
								</th>
								<th>
									地区
								</th>
								<th>
									添加时间
								</th>
								<th>
									编辑
								</th>
								<th>
									删除
								</th>
							</tr>
						</tr>
					</tfoot>
					<!--  	<tr>
						<td></td>
						<td colspan="5">
							<a>上一页</a>
							<a>下一页</a>
						</td>
					</tr>   -->
				</table>
				<div id="count_info">
					共
					<s:property value="page.len" />
					条记录,共
					<s:property value="page.pagenum" />
					页，第
					<s:property value="page.currentpage" />
					页
				</div>
			</form>

		</div>
		<br />

		<div id="page_span">
			<span id="page_first"><a class="page_button"
				href="list.action?page.currentpage=1">首页</a> </span>
			<span id="page_previous"><a class="page_button"
				href="list.action?page.currentpage=<s:if test="page.currentpage!=1"><s:property value="%{page.currentpage-1}"/></s:if><s:else>1</s:else>">前一页</a>
			</span>
			<span id="pagenum"> <s:iterator value="page.pagelist"
					id="pageNumber" status="status">
					<s:if test="page.currentpage==#status.count">
						<span> <a class="page_num page_button current_page"
							href="list.action?page.currentpage=<s:property />"><s:property />
						</a> </span>
					</s:if>
					<s:else>
						<span> <a class="page_num page_button"
							href="list.action?page.currentpage=<s:property />"><s:property />
						</a> </span>
					</s:else>
				</s:iterator> </span>
			<span id="page_next"><a class="page_button"
				href="list.action?page.currentpage=<s:if test="page.currentpage!=page.pagenum"><s:property value="%{page.currentpage+1}"/></s:if><s:else><s:property value="page.pagenum"/></s:else>">后一页</a>
			</span>
			<span id="page_last"><a class="page_button page_last"
				href="list.action?page.currentpage=<s:property value="page.pagenum"/>">末页</a>
			</span>
		</div>
		
		<s:debug />


	</body>
</html>