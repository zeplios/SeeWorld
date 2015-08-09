$(function(){
	$("form").ajaxForm({
		"cache" : false,
		"type" : "POST",
		"async" : false,
		"url" : "/SeeWorld/svc_ajax/multimedia/Mainpage/modifyMainpageDetail_json.action",
		"error" : function(r){alert("错误：" + r.status);},
		"success" : function(){alert("修改成功");location.reload();}
	});
	$(".cancleMp").click(function(){
		$.ajax({
			"cache" : false,
			"async" : false,
			"url" : "/SeeWorld/svc_ajax/multimedia/Mainpage/cancleMainpageMultimedia_json.action",
			"data" : {mainpageId : $(this).attr("id")},
			"error" : function(r){alert("错误：" + r.status);},
			"success" : function(){alert("修改成功");location.reload();}
		})
	})
	$(".moveUp").click(function(){
		$.ajax({
			"cache" : false,
			"async" : false,
			"url" : "/SeeWorld/svc_ajax/multimedia/Mainpage/swapMainpageMultimedia_json.action",
			"data" : {
				mainpageId : $(this).attr("id"),
				mainpageId2 : $(this).closest("form").prev().find(".cancleMp").attr("id")
			},
			"error" : function(r){alert("错误：" + r.status);},
			"success" : function(){alert("修改成功");location.reload();}
		})
	})
	$(".moveDown").click(function(){
		$.ajax({
			"cache" : false,
			"async" : false,
			"url" : "/SeeWorld/svc_ajax/multimedia/Mainpage/swapMainpageMultimedia_json.action",
			"data" : {
				mainpageId : $(this).attr("id"),
				mainpageId2 : $(this).closest("form").prev().find(".cancleMp").attr("id")
			},
			"error" : function(r){alert("错误：" + r.status);},
			"success" : function(){alert("修改成功");location.reload();}
		})
	})
})