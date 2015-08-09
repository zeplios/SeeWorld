// JavaScript Document
/**
 * args是一个字典型参数，其中必须包括targetId和resourceId这两个属性，来添加收藏的点击事件
 * @param args
 * @returns
 */
function checkCollected(args) {
	$.ajax( {
		cache : false,
		url : '/SeeWorld/svc_ajax/user/Collection/checkAlreadyCollected_json.action',
		type : 'post',
		dataType : 'json',
		data : args,
		beforeSend : function() {
			$(".self_collect").html("");
		},
		success : function(data) {
			if (data.alreadyCollected == "true") {
				$(".self_collect").attr("onclick","").html("&nbsp;&nbsp;&nbsp;&nbsp;已收藏");
			} else {
				var func = "addCollection({targetId:" + args.targetId + ", resourceId:" + args.resourceId + "})";
				$(".self_collect").html("<a href='javascript:void(0);' onclick='" + func 
						+ "'>&nbsp;&nbsp;&nbsp;&nbsp;收藏</a>");
			}
		},
		error : function() {
			$(".self_collect").attr("onclick","").html("&nbsp;&nbsp;&nbsp;&nbsp;收藏暂不可用");
		}
	});
}

function addCollection(args) {
	$.ajax( {
		cache : false,
		url : '/SeeWorld/svc_ajax/user/Collection/addCollection_json.action',
		type : 'post',
		dataType : 'json',
		data : args,
		beforeSend : function() {
			$(".self_collect").html("&nbsp;&nbsp;&nbsp;&nbsp;稍候...");
		},
		success : function(data) {
			$(".self_collect").html("&nbsp;&nbsp;&nbsp;&nbsp;已收藏").attr("onclick","");
			alertDiv("收藏成功!");
		},
		error : function() {
			alertDiv("收藏失败!");
		}
	});
}