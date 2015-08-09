var mtype;
var initArgs;
/**
 * 适用于非剧集资源列表的获取
 * @param type 资源类型，如"Movie"、"Video"、"Serial"、"SingleSerial"
 * @param args ajax的data部分
 */
function getMultimediaList(type, args) {
	mtype = type;
	initArgs = args;
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		async : false,
		url : "/SeeWorld/svc_ajax/multimedia/Multimedia/find" + mtype + "ContentByPage_json.action",
		data : args,
		success : loadContent
	});
}

function loadContent(data){
	if(mtype == "Serial")
		loadSerialContent(data);
	else if(mtype == "SingleSerial")
		loadSingleSerialContent(data);
	else
		loadMultimediaContent(data);
}

function loadSerialContent(data) {
	$("#content_list").empty();
	for ( var i = 0, len = data.resultList.length; i < len; i++) {
		var targetId = data.resultList[i].id;
		var trClass = "";
		if (i % 2 == 0)
			trClass = "tb_bg_odd";
		else
			trClass = "tb_bg_even";
		$("#content_list").append(
			'<tr class="' + trClass + '">'
			+ '<td><a href="/SeeWorld/admin/listSingleSerial.jsp?targetId='
			+ targetId
			+ '">'
			+ data.resultList[i].title
			+ '</a></td><td>'
			+ data.resultList[i].year
			+ '</td><td>'
			+ data.resultList[i].areaAndCountry
			+ '</td><td>'
			+ data.resultList[i].season
			+ '</td><td>'
			+ data.resultList[i].seasons
			+ '</td><td>'
			+ data.resultList[i].directors
			+ '</td><td>'
			+ data.resultList[i].addTime
			+ '</td><td>'
			+ '<a href="/SeeWorld/admin/addSingleSerial.jsp?id='
			+ targetId
			+ '"><img src="images/icons/add.png" />添加</a>'
			+ '</td><td class="center"><a href="/SeeWorld/svc/multimedia/'
			+ mtype
			+ '/preUpdate.action?targetId=' + targetId + '">编辑</a></td>'
			+ '<td class="center"><a href="javascript:void(0);" onclick="deleteMultimedia('
			+ targetId
			+ ')"><img src="images/icons/cross_circle.png"/></td></tr>'
		);
	}
	
	if(data.pageJSON){
		handlePage(data);
		changeMultimediaPage();
	}
}

function loadSingleSerialContent(data) {
	if(data.resultList.length > 0)
		$("#title").empty().append("<b>" + data.resultList[0].serial.title + "</b> 的单集列表")
	$("#content_list").empty();
	for ( var i = 0, len = data.resultList.length; i < len; i++) {
		var ss = data.resultList[i];
		var playUrl = "/SeeWorld/svc/multimedia/Multimedia/play.action?id="
				+ ss.id + "&amp;resourceId="
				+ ss.resourceId;
		var updateUrl = "/SeeWorld/svc/multimedia/SingleSerial/preUpdate.action?id="
				+ ss.id;
		var trClass = "";
		if (i % 2 == 0)
			trClass = "tb_bg_odd";
		else
			trClass = "tb_bg_even";
		$("#content_list").append(
			'<tr class="' + trClass + '">'
			+ '<td><a href="' + playUrl + '" target="_blank">'
			+ ss.title
			+ '</a></td><td>'
			+ ss.episode
			+ '</td><td>'
			+ ss.areaAndCountry
			+ '</td><td>'
			+ ss.serial.directors
			+ '</td><td>'
			+ ss.addTime
			+ '</td>'
			+ '<td class="center"><a href="' + updateUrl + '">编辑</a></td>'
			+ '<td class="center"><a href="javascript:void(0);" onclick="deleteMultimedia('
			+ ss.id
			+ ')"><img src=\"images/icons/cross_circle.png\" /></td></tr>'
		);
	}

	if(data.pageJSON){
		handlePage(data);
		if(data.resultList.length > 0)
			changeSingleSerialPage(data.resultList[0].serial.id);
	}
}

/**
 * 适用于显示非剧集资源列表
 * @param data ajax返回的数据对象
 */
function loadMultimediaContent(data) {
	$("#content_list").empty();
	for (var i = 0, len = data.resultList.length; i < len; i++) {
		var targetId = data.resultList[i].id;
		var playUrl = "/SeeWorld/svc/multimedia/Multimedia/play.action?id="
				+ targetId + "&amp;resourceId="
				+ data.resultList[i].resourceId;
		var trClass = "";
		if (i % 2 == 0)
			trClass = "tb_bg_odd";
		else
			trClass = "tb_bg_even";
		
		$("#content_list").append(
			'<tr class="' + trClass + '">'
			+ '<td><a href="' + playUrl + '">'
			+ data.resultList[i].title
			+ '</a></td><td>'
			+ data.resultList[i].categorys
			+ '</td><td>'
			+ data.resultList[i].areaAndCountry
			+ '</td><td>'
			+ data.resultList[i].user.realName
			+ '</td><td>'
			+ data.resultList[i].addTime
			+ '</td>'
			+ '<td class="center"><a href="/SeeWorld/svc/multimedia/'
			+ mtype
			+ '/preUpdate.action?targetId=' + targetId + '">编辑</a></td>'
			+ '<td class="center"><a href="javascript:void(0);" onclick="deleteMultimedia('
			+ targetId
			+ ')"><img src="images/icons/cross_circle.png"/></td>'
			+ '<td><a href="javascript:void(0);" onclick="setMainpage(' + targetId + ', 1)">设为滚动</a>&nbsp;&nbsp;&nbsp;'
			+ '<a href="javascript:void(0);" onclick="setMainpage(' + targetId + ', 0)">设为静态</a></td></tr>'
		);
	}
	if(data.pageJSON){
		handlePage(data);
		changeMultimediaPage();
	}
}

function handlePage(data){
	pagelength = data.pageJSON.len;
	totalpage = (parseInt(data.pageJSON.len % data.pageJSON.pagesize) == 0 ? parseInt(data.pageJSON.len
			/ data.pageJSON.pagesize)
			: parseInt(data.pageJSON.len / data.pageJSON.pagesize) + 1);
	curpage = data.currentPage;
	prepage = data.pageJSON.pre;
	nextpage = data.pageJSON.next;
	
	$("#pagenum").empty();
	for ( var i = 0, len = data.pageJSON.pagelist.length; i < len; i++) {
		if (data.pageJSON.pagelist[i] == data.currentPage) {
			$("#pagenum").append('<span class="page_num page_button current_page">' + data.pageJSON.pagelist[i] + '</span>');
		} else {
			$("#pagenum").append('<span class="page_num page_button">' + data.pageJSON.pagelist[i] + '</span>');
		}
	}
	$("#count_info").empty().append('共' + data.pageJSON.len + '条记录，当前第' + data.currentPage + '页/共' + totalpage + '页');
}

function changeSingleSerialPage(targetId) {
	var args = {
		len : pagelength,
		currentPage : $(this).text(),
		targetId : targetId
	}
	changePageForAllTypes();
}

function changeMultimediaPage() {
	args = {
		orderBy : $('#sort').val(),
		len : pagelength,
		currentPage : 0
	}
	changePageForAllTypes();
}

function changePageForAllTypes(){
	$('.page_num').unbind('click').bind('click',function() {
		args.currentPage = $(this).text();
		getMultimediaList(mtype, args);
	});
	
	$('#page_first').unbind('click').bind('click', function() {
		args.currentPage = 1;
		getMultimediaList(mtype, args);
	});
	
	$('#page_previous').unbind('click').bind('click', function() {
		args.currentPage = prepage;
		getMultimediaList(mtype, args);
	});
	
	$('#page_next').unbind('click').bind('click', function() {
		args.currentPage = nextpage;
		getMultimediaList(mtype, args);
	});
	
	$('#page_last').unbind('click').bind('click', function() {
		args.currentPage = totalpage;
		getMultimediaList(mtype, args);
	});
}

function changeSort() {
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "/SeeWorld/svc_ajax/multimedia/Multimedia/find" + mtype
				+ "ContentByPage_json.action",
		data : {
			orderBy : $('#sort').val(),
			len : pagelength,
			currentPage : 1
		},
		success : loadContent
	});
}

/**
 * 把某视频设置为首页显示项
 * @param targetId 要设置的视频id
 * @param isScroll 是否为滚动项
 * @author zhfch
 */
function setMainpage(targetId, isScroll){
	$.ajax({
		cache : false,
		type : "POST",
		async : false,
		data : {
			targetId : targetId,
			isScroll : isScroll
		},
		url : "/SeeWorld/svc_ajax/multimedia/Mainpage/setMainpageMultimedia_json.action",
		error : function(r){alert("错误：" + r.status);},
		success : function(r){alert("设置成功");}
	});
}

// 导演 这段 暂时没用。。z
function getDirectorList() {
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "/SeeWorld/svc_ajax/multimedia/Multimedia/find" + mtype
				+ "ContentByPage_json.action",
		data : {
			// categoryId :1,
			// publishedYear : 1,
			// areaAndCountryId : 1,
			orderBy : 2,
			currentPage : 1
		},
		success : loadContent
	});
}

function deleteMultimedia(targetid) {
	$.ajax( {
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "/SeeWorld/svc_ajax/multimedia/Multimedia/delete_json.action",
		data : {
			targetId : targetid,
			resourceId : getResourceID(mtype)
		},
		error : function(){alert("提示：有子集或引用时不能删除！");},
		success : function success() {
			alert("删除成功");
			getMultimediaList(mtype, initArgs);
		}
	});
}

function getResourceID(type) {
	if(type == "Movie")
		return "1";
	else if(type == "Video")
		return "2";
	else if(type == "Serial")
		return "3";
	else if(type == "SingleSerial")
		return "4";
}
