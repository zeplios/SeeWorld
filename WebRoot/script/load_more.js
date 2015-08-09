var base = "/SeeWorld/svc_ajax/multimedia";

var movieURL = base + "/Multimedia/findMovieContentByPage_json.action";
var serialURL = base + "/Multimedia/findSerialContentByPage_json.action";
var videoURL = base + "/Multimedia/findVideoContentByPage_json.action";

var movieSearchURL = base + "/Search/findMovieByNameDisplayByPage_json.action";
var serialSearchURL = base + "/Search/findSerialByNameDisplayByPage_json.action";
var videoSearchURL = base + "/Search/findVideoByNameDisplayByPage_json.action";

/**
 * detail页面中使用，目前设计只针对Movie、Video和Serial，没有SingleSerial的细节页（More页）
 * @param url
 * @param args
 * @param func
 */
function getMultimediaContentList(url, args, func) {
	$.ajax( {
		cache : false,
		url : url,
		type : 'post',
		dataType : 'json',
		data : args,
		beforeSend : function() {
			$(".details_list").slideUp("slow").empty();
		},
		success : func,
		complete : function() {
			initToggle("v_introduce", 150, true);
			$(".details_list").slideDown();
		}
	});
}

function changePage(data) {
	$(".page_nav").empty().append('<p class="page_info">共' + data.pageJSON.pagenum + '页 / ' + data.pageJSON.len + '条记录</p>');
	var html = "";
	html += '<ul class="page_nav_list">';
	for ( var i = 0, len = data.pageJSON.pagelist.length; i < len; i++) {
		if (i == data.pageJSON.currentpage - data.pageJSON.pagelist[0]) {
			html += '<li class="page_nav_li currentPage">' + (i+1) + '</li>';
		} else {
			html += '<li class="page_nav_li"><a class="page_nav_li_a" href="javascript:void(0)">' + (i+1) + '</a></li>';
		}
	}
	html += '</ul>';
	html += '<div class="page_btn">';
	if(data.pageJSON.currentpage == data.pageJSON.pagenum)
		html += '<span class="btn_prev_span">下一页</span>';
	else
		html += '<a class="btn_next_a" id="next" href="javascript:void(0)">下一页</a>';
	if(data.pageJSON.currentpage == 0)
		html += '<span class="btn_prev_span">上一页</span>';
	else
		html += '<a class="btn_next_a" id="prev" href="javascript:void(0)">上一页</a>';
	html += '</div>'
	$(".page_nav").append(html);
	
	bindclick();
}

function loadContent(data) {
	changePage(data);

	for ( var i = 0, len = data.resultList.length; i < len; i++) {
		var m = data.resultList[i];
		var playUrl = "";
		var imgDivClass = "", imgWidth = "", imgHeight = "", infoDivClass = "";
		if(m.resourceId == 3){	/*Serial*/
			if(m.singleSerials.length > 0)
				playUrl = "/SeeWorld/svc/multimedia/play.action?id="
						+ m.singleSerials[0].id + "&amp;resourceId="
						+ m.singleSerials[0].resourceId;
			else
				playUrl = "";
			imgDivClass = "cover_large_box";
			imgWidth = "160px";
			imgHeight = "90px";
			infoDivClass = "v_content_box";
		} else {
			playUrl = "/SeeWorld/svc/multimedia/play.action?id="
					+ m.id + "&amp;resourceId=" + m.resourceId;
			imgDivClass = "cover_vertical_box";
			imgWidth = "114px";
			imgHeight = "152px";
			infoDivClass = "v_content_vertical_box";
		}
		
		$(".details_list").append(
				'<li class="details_li">'
				+ '<div class="' + imgDivClass + '"><a class="v_img_a" href="' + playUrl 
				+	 '"><img width="' + imgWidth + '" height="' + imgHeight + '" alt="视频封面" src="/posters/' + m.image + '"/></a></div>'
				+ '<div class="' + infoDivClass + '">'
				+	 '<p class="v_name"><a class="v_name_a" title="视频标题" href="' + playUrl + '">' + m.title + '</a></p>'
				+	 '<p class="v_info"><span class="info_play">播放：' + m.clickCount + '</span>'
				+			'<span class="info_collect"> 收藏：0</span><span class="info_comment">评论：' + m.commentsCount + '</span>'
				+			'<span class="info_date">日期：' + m.addTime + '</span></p>'
				+	 '<p class="v_introduce">' + m.introduction + '</p>'
				+ '</div>' + 
				'</li>');
	}
}

function getMovieContentList(args) {
	getMultimediaContentList(movieURL, args, loadContent);
}

function getSerialContentList(args) {
	getMultimediaContentList(serialURL, args, loadContent);
}

function getVideoContentList(args) {
	getMultimediaContentList(videoURL, args, loadContent);
}

function findMovieByNameDisplayByPage(args) {
	getMultimediaContentList(movieSearchURL, args, loadContent);
}

function findSerialByNameDisplayByPage(args) {
	getMultimediaContentList(serialSearchURL, args, loadContent);
}

function findVideoByNameDisplayByPage(args) {
	getMultimediaContentList(videoSearchURL, args, loadContent);
}