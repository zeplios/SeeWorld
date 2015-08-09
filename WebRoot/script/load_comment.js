var args;
var defaultHtml = "暂时还没有评论...";
function findCommentByPage(args) {
	$.ajax({
		cache : false,
		url : '/SeeWorld/svc_ajax/user/Comment/findCommentsByPage_json.action',
		type : 'post',
		dataType : 'json',
		data : args,
		beforeSend : function() {
			$(".comment_list").slideUp("slow").empty();
		},
		success : function(data) {
			listComent(data);
		},
		complete : function() {
			$(".comment_list").slideDown();
		}
	});
}

function listComent(data) {
	var list = data.resultList;
	var html = "";
	for ( var i = 0; i < list.length; i++) {
		var items = list[i];
		html += "<li class='comment_li'>"
				+ "<div class='user_img_box'><a class='user_img_a' href='#'><img alt='用户头像' width='50px' title='用户头像' src='/avatars/"
				+ items.user.photoPath + "'/></a></div>"
				+ "<p class='user_name'><a class='user_name_a' title='用户姓名' href='javascript:void(0)'>" 
				+ items.user.userName + "</a><!--<strong class='floor_num'>1楼</strong>--></p>"
				+ "<p class='comment_content'>" + items.replycontext + "</p>"
				+ "<p class='comment_time'>" + items.addTime + "</p></li>";
	}
	if ($.trim(html) == "") {
		$("#comment_list").html(defaultHtml);
	} else {
		var page = data.pageJSON;
		args = "{targetId:" + commentMultimediaId + ",resourceId:\'"
				+ commentResourceId + "\',len:" + data.pageJSON.len
				+ ",currentPage:";
		$("#comment_list").html(html);
		changePage(page, "findCommentByPage", args);
	}
}

function support(obj, id) {
	$.ajax({
		cache : false,
		url : '/SeeWorld/svc_ajax/user/Comment/support_json.action',
		type : 'post',
		dataType : 'json',
		beforeSend : function() {

		},
		data : {
			commentID : id
		},
		success : function(data) {
			$(obj).html("支持(" + data.support + ")");
		}
	});
}
function object(obj, id) {
	$.ajax({
		cache : false,
		url : '/SeeWorld/svc_ajax/user/Comment/object_json.action',
		type : 'post',
		dataType : 'json',
		beforeSend : function() {

		},
		data : {
			commentID : id
		},
		success : function(data) {
			$(obj).html("反对(" + data.object + ")");
		}
	});
}

function addReply(id) {
	reply = id;
	$("#commentTitle")[0].focus();
	$("#CommentButton")
			.html(
					"<input type=\"submit\" value=\"取消回复\" class=\"button_bg\" onclick='cancelReply();' /><input type=\"submit\" value=\"发表\" class=\"button_bg\" onClick=\"toSubmit()\"/>");
}
function cancelReply() {
	reply = 0;
	$("#CommentButton")
			.html(
					("CommentButton").innerHTML = "<input type=\"submit\" value=\"发表\" class=\"button_bg\" onClick=\"toSubmit()\"/>");
}

var submitEnable = true;	//防止按钮重复点击
var submitAccess = true;	//10s内禁止重复评论
function toSubmit() {
	if(!submitEnable)
		return;
	if(!submitAccess){
		alertDiv("两次评论间隔太短！请10s后重试！");
		return;
	}
	var content = $.trim($("#txtArea").val());
	// 将回车替换成 <br/>
	content = content.replace(/\n/g, '<br/>');
	var title = "";
	var reply = 0;//这两个字段目前没用了
	$.ajax({
		cache : false,
		url : '/SeeWorld/svc_ajax/user/Comment/addComment_json.action',
		type : 'post',
		dataType : 'json',
		data : {
			title : title,
			content : content,
			targetId : commentMultimediaId,
			replyTo : reply,
			resourceId : commentResourceId
		},
		beforeSend : function() {
			submitEnable = false;
			submitAccess = false;
		},
		complete : function() {
			submitEnable = true;
			setTimeout(function(){submitAccess = true;}, 10000);
		},
		error : function() {
			submitEnable = true;
			submitAccess = true;
			alertDiv("评论失败");
		},
		success : getCommentResult
	});
}

function getCommentResult(json) {
	findCommentByPage({currentPage:1,targetId:commentMultimediaId,resourceId:commentResourceId});
	$("#txtArea").val("");
	/*var result = json.result;
	var html = "";
	html += "<li class='comment_li'>"
		+ "<div class='user_img_box'><a class='user_img_a' href='#'><img alt='用户头像' title='用户头像' src='"
		+ result.user.photoPath + "'/></a></div>"
		+ "<p class='user_name'><a class='user_name_a' title='用户姓名' href='#'>" 
		+ result.user.userName + "</a><!--<strong class='floor_num'>1楼</strong>--></p>"
		+ "<p class='comment_content'>" + result.replycontext + "</p>"
		+ "<p class='comment_time'>" + result.addTime + "</p></li>";
	if ($.trim($("#comment_list").html()) == defaultHtml) {
		$("#comment_list").html(html);
	} else {
		$("#comment_list").prepend(html);
	}*/
	setTimeout(function(){submitAccess = true;}, 10000);
	submitEnable = true;
	alertDiv("评论成功");
}

function changePage(page, funcName, arg) {
	var pagehtml = "";

	pagehtml += "<p class='page_info'>共" + page.pagenum + "页&nbsp;/&nbsp;" + page.len + "条评论</p>";
	pagehtml += "<ul class='page_nav_list'>";
	var pagelist = page.pagelist;
	$.each(pagelist, function(i, n) {
		if (page.currentpage != n) {
			pagehtml += "<li class='page_nav_li'><a class='page_nav_li_a' href='javascript:"
					+ funcName + "(" + arg + n + "})'>" + n
					+ "</a></li>";
		} else {
			pagehtml += "<li class='page_nav_li currentPage'>" + n + "</li>";
		}
	});
	pagehtml += "</ul>";
	pagehtml += "<div class='page_btn'>";
	
	if (page.currentpage != 1) {
		var i = parseInt(page.currentpage) - 1;
		pagehtml += " <a class='btn_next_a' href=\"javascript:"
				+ funcName + "(" + arg + 1 + "})\" >首页</a>";
		pagehtml += " <a class='btn_next_a' href=\"javascript:"
				+ funcName + "(" + arg + i + "})\" >上一页</a>";
	} else {
		pagehtml += " <span class='btn_prev_span'>首页</span>";
		pagehtml += " <span class='btn_prev_span'>上一页</span>";
	}
	
	if (page.currentpage != page.pagenum) {
		var i = parseInt(page.currentpage) + 1;
		pagehtml += " <a class=\"next\" href=\"javascript:"
				+ funcName + "(" + arg + i + "})\" >下一页</a>";
		pagehtml += " <a class=\"next\" href=\"javascript:"
				+ funcName + "(" + arg + page.pagenum
				+ "})\" >尾页</a>";
	} else {
		pagehtml += " <span class='btn_prev_span'>下一页</span>";
		pagehtml += " <span class='btn_prev_span'>尾页</span>";
	}
	pagehtml += "</div>";
	$(".page_nav").html(pagehtml);
}