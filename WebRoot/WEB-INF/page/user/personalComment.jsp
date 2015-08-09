<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix='sec'	uri='http://www.springframework.org/security/tags'%>
<script type="text/javascript">
	var currentPage;
	var currentPage2;
	var content1;
	var title1;
	var targetUserId = '<sec:authentication property="principal.id" />';
	var resourceId = '<s:property value="resourceId" />';
	var len;
	$(function() {
		initPersonalComment();
	});
	function initPersonalComment() {
		$.ajax({
			cache : false,
			url : '/SeeWorld/svc_ajax/user/Comment/findPersonalCommentsByPage_json.action',
			type : 'post',
			dataType : 'json',
			data : {
				userId : targetUserId,
				currentPage : currentPage
			},
			success : getInitPersonalCommentResult
		});
	}
	function getInitPersonalCommentResult(json) {
		currentPage2 = 1;
		getPersonalCommentPageChangedResult(json);
		len=json.pageJSON.len;
	}
	
	function pageChange() {
		$.ajax({
			cache : false,
			url : '/SeeWorld/svc_ajax/user/Comment/findPersonalCommentsByPage_json.action',
			type : 'post',
			dataType : 'json',
			data : {
				userId : targetUserId,
				currentPage : currentPage2,
				len : len
			},
			success : getPersonalCommentPageChangedResult
		});
	}
	
	function deleteComment(id){
		$.ajax( {
			cache : false,
			url : '/SeeWorld/svc_ajax/user/Comment/deleteComment_json.action',
			type : 'post',
			dataType : 'json',
			data : {
				commentID : id
			},
			success : function(data){
				var id = "#comment"+data.commentID;
				$(id).hide();
			},
			error:function(){
				alert("删除失败！");
			}
		});
	}
	function getPersonalCommentPageChangedResult(json) {
		currentPage = currentPage2;
		var list = json.resultList;
	
		var html = "";
		for ( var i = 0; i < list.length; i++) {
			var items = list[i];
			var playurl = "/SeeWorld/svc/multimedia/Multimedia/play.action?id="
					+ items.targetId + "&resourceId=" + items.resourceId;
			html += "<li class='v_item' id='comment" + items.commentID + "'>"
					+	"<div class='v_comment'>"
                	+		"<div class='v_name'>"
                	+			"<p class='v_name_detail'>"
                    +				"<span class='sort_note'>（视频）</span><a class='v_name_detail_a' href='" + playurl + "'>" + items.targetName + "</a>"
                    +			"</p>"
                    +		"</div>"
                    +		"<div class='comment_info'>"
                    +			"<p class='comment_time'>" + items.addTime + "</p>"
                    +			"<p class='comment_detail'>" + items.content + "</p>"
                    +		"</div>"
                    +	"</div>"
                	+	"<a class='item_del_btn' href='javascript:deleteComment(" + items.commentID + ")' title='删除'>[删除]</a>"
            		+"</li>";
		}
		var page = json.pageJSON;
		var pagehtml = ""
		
		pagehtml += "<p class='page_info'>共" + page.pagenum + "页/ " + page.len + "条记录</p>";
		pagehtml += "<ul class='page_nav_list'>";
		var pagelist = page.pagelist;
		for ( var j = 0; j < pagelist.length; j++) {
			var items = pagelist[j];
			if (currentPage != items) {
				pagehtml += "<li class='page_nav_li'><a href='javascript:gotoPage("	+ items + ")' class='page_nav_li_a'>" + items + " </a></li>";
			} else {
				pagehtml += "<li class='page_nav_li current'>" + items + " </li>";
			}
		}
		pagehtml += "</ul>";
		
		pagehtml += "<div class='page_btn'>";
		if (page.currentpage != 1) {
			pagehtml += " <a href='javascript:pre()' class='btn_next_a'>上一页</a>";
		} else {
			pagehtml += " <span class='btn_prev_span'>上一页</span>";
		}
		if (page.currentpage != page.lastpage) {
			pagehtml += "<a href='javascript:next()' class='btn_next_a'>下一页</a>";
		} else {
			pagehtml += "<span class='btn_prev_span'>上一页</span>";
		}
		$(".page_nav:first").html(pagehtml);
		$(".v_list:first").html(html);
	}
	function next() {
		currentPage2 = currentPage;
		currentPage2++;
		pageChange();
	}
	function pre() {
		currentPage2 = currentPage;
		currentPage2--;
		pageChange();
	}
	function gotoPage(page) {
		currentPage2 = page;
		pageChange();
	}
</script>
<div class="main_container">
	<h4 class="page_title">我的评论</h4>
    <div class="v_list_container">
    	<ul class="v_list">
        </ul>
    </div>
    <div class="page_nav">
    </div>
</div>