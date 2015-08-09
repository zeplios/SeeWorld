$(document).ready(function(){
	getCommentList();
	setModifyCommentsPerPage();
});

function getCommentList(args){
	$.ajax({
		"cache" : false,
		"dataType" : 'json',
		"type" : "POST",
		"async" : false,
		"url" : "/SeeWorld/svc_ajax/user/Comment/findAllCommentsByPage_json.action",
		"data" : args,
		"error" : function(r){alert("错误：" + r.status);},
		"success" : loadContent
	});
}

function setModifyCommentsPerPage(){
	$("#modifySetting").ajaxForm({
		"cache" : false,
		"dataType" : 'json',
		"type" : "POST",
		"async" : false,
		"url" : "/SeeWorld/svc_ajax/common/Setting/modifyCommentsPerPage_json.action",
		"error" : function(r){alert("错误：" + r.status);},
		"success" : function(){getCommentList();}
	});
}

function loadContent(data) {
	pagelength = data.pageJSON.len;
	totalpage = (parseInt(data.pageJSON.len % data.pageJSON.pagesize) == 0 ? parseInt(data.pageJSON.len
			/ data.pageJSON.pagesize)
			: parseInt(data.pageJSON.len / data.pageJSON.pagesize) + 1);
	curpage = data.currentPage;
	prepage = data.pageJSON.pre;
	nextpage = data.pageJSON.next;

	$("#content_list").empty();
	for ( var i = 0, len = data.resultList.length; i < len; i++) {
		var trClass = (i % 2 == 0) ? "tb_bg_odd" : "tb_bg_even";
		$("#content_list").append('<tr class="' + trClass + '">'
				+ '<td><a href="javascript:void(0)">'
				+ data.resultList[i].user.userName
				+ '</a></td><td>'
				+ data.resultList[i].title
				+ '</td><td>'
				+ data.resultList[i].content
				+ '</td><td>'
				+ data.resultList[i].targetName
				+ '</td><td>'
				+ data.resultList[i].addTime
				+ '</td><td>'
				+ data.resultList[i].resourceName
				+ '</td>'
				+ '<td class="center"><a href="javascript:void(0);" onclick="disableComment('
				+ data.resultList[i].commentID
				+ ')"><img src=\"images/icons/cross_circle.png\" /></td>'
				+ '<td class="center"><a href="javascript:void(0);" onclick="deleteComment('
				+ data.resultList[i].commentID
				+ ')"><img src=\"images/icons/cross_circle.png\" /></td></tr>'
		);
	}

	$("#pagenum").empty();
	for ( var i = 0, len = data.pageJSON.pagelist.length; i < len; i++) {
		if (data.pageJSON.pagelist[i] == data.currentPage) {
			$("#pagenum").append(
				'<span class="page_num page_button current_page">' + data.pageJSON.pagelist[i] + '</span>');
		} else {
			$("#pagenum").append(
				'<span class="page_num page_button">' + data.pageJSON.pagelist[i] + '</span>');
		}
	}

	$("#count_info").empty().append(
			'共' + data.pageJSON.len + '条记录，当前第' + data.currentPage + '页/共'
			+ totalpage + '页');

	changePage();
}

function changePage() {
	$('.page_num').unbind('click').bind('click',function() {
		getCommentList({
			len : pagelength,
			currentPage : $(this).text(),
			targetId : "",
			resourceId : ""
		})
	});

	$('#page_first').unbind('click').bind('click',function() {
		getCommentList({
			len : pagelength,
			currentPage : 1,
			targetId : "",
			resourceId : ""
		})
	});

	$('#page_previous').unbind('click').bind('click',function() {
		getCommentList({
			len : pagelength,
			currentPage : prepage,
			targetId : "",
			resourceId : ""
		})
	});

	$('#page_next').unbind('click').bind('click',function() {
		getCommentList({
			len : pagelength,
			currentPage : nextpage,
			targetId : "",
			resourceId : ""
		})
	});

	$('#page_last').unbind('click').bind('click',function() {
		getCommentList({
			len : pagelength,
			currentPage : totalpage,
			targetId : "",
			resourceId : ""
		})
	});
}

function disableComment(commentID) {
	$.ajax( {
		"cache" : false,
		"dataType" : 'json',
		"type" : "POST",
		"url" : "/SeeWorld/svc_ajax/user/Comment/disableComment_json.action",
		"data" : {
			commentID : commentID
		},
		"error" : function(r){alert("错误：" + r.status);},
		"success" : function success() {
			alert("禁用评论成功");
			getCommentList();
		}
	});
}
function deleteComment(commentID) {
	$.ajax( {
		"cache" : false,
		"dataType" : 'json',
		"type" : "POST",
		"url" : "/SeeWorld/svc_ajax/user/Comment/deleteComment_json.action",
		"data" : {
			commentID : commentID
		},
		"error" : function(r){alert("错误：" + r.status);},
		"success" : function success() {
			alert("删除成功");
			getCommentList();
		}
	});
}