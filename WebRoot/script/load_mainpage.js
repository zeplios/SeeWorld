/**
 * load the static or scroll resources in the mainpage
 */
$(function(){
	$.ajax({
		cache : false,
		url : '/SeeWorld/svc_ajax/multimedia/Mainpage/findMainpageMultimedia_json.action',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			listMultimedias(data);
			initToggle("movie_intro", 100, false);
			$("#poster_list").slides({
				container: 'poster_container',
				generatePagination: false,	//不自动生成点列表
				paginationClass: 'dots',	//点列表的class名
				generateNextPrev: false,	//不自动生成前后按钮
				play: 5000,
				pause: 1000
	  		});
		}
	})
})

function listMultimedias(data){
	var list = data.resultList;
	var scrollhtml = "";
	var statichtml = "";
	var dotshtml = "";
	for ( var i = 0; i < list.length; i++) {
		var playUrl = "/SeeWorld/svc/multimedia/Multimedia/play.action?id="
			+ list[i].multimedia.id + "&resourceId=" + list[i].multimedia.resourceId;
		if(list[i].isScroll){
			scrollhtml += '<li class="poster_element">'
				+ '<a href="' + playUrl + '"><img width="960px" height="320px" src="/posters/' + list[i].image + '" /></a>'
				+ '<div class="intro_mask"></div>'
				+ '<div class="intro">'
				+ '<a class="intro_a" title="" href="' + playUrl + '" >' + list[i].briefDescription + '</s:a>'
				+ '</div>'
				+ '</li>';
			if(i == 0)
				dotshtml += '<li class="current"><a class="dots_control" href="#">1</a></li>';
			else
				dotshtml += '<li><a class="dots_control" href="#">' + (i+1) + '</a></li>';
		}
		else if(list[i].isStatic){
			statichtml += '<li><a class="recommend_a_img" href="' + playUrl + '">'
						+ '<img width="280px" height="140px" src="/posters/' + list[i].image + '" alt="recommend1" /></a>'
						+ '<h2><a class="recommend_h2_a" href="' + playUrl + '">' + list[i].multimedia.title + '</a></h2>'
						+ '<p class="movie_intro">' + list[i].multimedia.introduction + '</p>'
						+ '</li>';
		}
	}
	$(".poster_container:first").html(scrollhtml);
	$(".recommend ul:first").html(statichtml);
	$(".dots:first").html(dotshtml);
}