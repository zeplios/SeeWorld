var newThingsStart = 0;
var newThingsSize = 10;

$(function() {
	findNewThings();
});

function findNewThings() {
	$("#moreNewThings").hide();
	$("#loadingNewThings").show();
	$.ajax( {
		cache : false,
		url : '/SeeWorld/svc_ajax/user/NewThings/moreNewThings_json.action',
		type : 'post',
		dataType : 'json',
		data : {
			start : newThingsStart,
			resultSize : newThingsSize
		},
		success : getNewThings,
		error : function() {
			$(".v_list").append("获取失败喛");
			$("#loadingNewThings").hide("slow");
		}
	});
}
function getNewThings(json) {
	var list = json.resultList;
	var html = $(".v_list:first").html();
	if (list != null && list.length != 0) {
		for ( var i = 0; i < list.length; i++) {
			var items = list[i];
			var target = items.target;
			var playurl = "/SeeWorld/svc/multimedia/Multimedia/play.action?id="
					+ target.id + "&resourceId=" + target.resourceId;

			var divClass = "";
			var imgWidth = "";
			var imgHeight = "";
			if (target.resourceId == 1){
				divClass = "v_cover_box_vertical";
				imgWidth = "64px";
				imgHeight = "86px";
			} else {
				divClass = "v_cover_box";
				imgWidth = "90px";
				imgHeight = "50px";
			}
			html += "<li class='v_item'>"
					+	"<div class='v_cover'>"
					+		"<div class='" + divClass + "'>"
					+			"<a class='v_cover_a' href='" + playurl + "' title='封面'>"
					+				"<img width='" + imgWidth + "' height='" + imgHeight + "' src='/posters/" + target.image + "' alt='封面'/>"
					+			"</a>"
					+		"</div>"
					+	"</div>"
					+	"<div class='v_content'>"
					+		"<p class='new_title'>"
					+			"<a class='user_name' href='" + items.user.id + "'>" + items.user.userName + "</a>"
					+			"<em class='new_time_mark space_left'>" + items.modifiedTime + "</em>&nbsp;&nbsp;" + items.operation + "<a class='v_name_detail_a space_left' href='" + playurl + "'>" + target.title + "</a>"
					+		"</p>"
					+		"<p class='new_content'>" + items.otherInfo + "</p>"
					+	"</div>"
					+"</li>";
		}
	} else {
		//html = "暂时没有新鲜的动态";
		$("#moreNewThings").text("没有更新动态了");
		$("#moreNewThings").attr("href", "javascript:void(0)");
	}
	$("#moreNewThings").show();
	newThingsStart += newThingsSize - 1;
	$(".v_list:first").html(html);
	$("#loadingNewThings").hide("slow");
	initToggle("new_content", 100, false);
}