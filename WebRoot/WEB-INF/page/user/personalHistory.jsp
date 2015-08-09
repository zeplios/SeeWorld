<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript">
	$(function() {
		$.ajax( {
			cache : false,
			url : '/SeeWorld/svc_ajax/user/NewThings/viewWatchHistory_json.action',
			type : 'post',
			dataType : 'json',
			success : showHistory,
			error : function() {
				$(".v_list").append("加载失败:(~");
			}
		});
		function showHistory(data) {
			var html = '';
			if (data.resultList == null) {
				html = "暂时没有观看记录呢~";
			} else {
				var l = data.resultList.length;
				for (i = 0; i < l; i++) {
					html += "<li class='v_item'>"
							+	"<div class='v_cover'>"
	                		+		"<div class='v_cover_box_vertical'>"<%--剧集为<div class="v_cover_box">--%>
	                        +			"<a class='v_cover_a' href='#0' title='封面'>"
	                        				<%--剧集为<img width="90" height="50" src="images/temp/cover_large.jpg" alt="封面" title="封面"/>--%>
	                        +    			"<img width='64' height='86' src='/posters/" + data.resultList[i].targetImage + "' alt='封面' title='封面'/>"                        
	                        +			"</a>"
	                    	+		"</div>"
	                		+	"</div>"
	                		+	"<div class='v_content'>"
	                		+		"<div class='v_name'>"
	                    	+			"<p class='v_name_detail'>"
	                        +				"<span class='sort_note'>（视频）</span><a class='v_name_detail_a'"
	                        +					" href='/SeeWorld/svc/multimedia/Multimedia/play.action?id=" + data.resultList[i].targetID 
	                        + 					"&resourceId=" + data.resultList[i].targetTypeID + "'>" + data.resultList[i].targetContent + "</a>"
	                        +			"</p>"
	                    	+		"</div>"
	                    	+		"<div class='v_info clearfix'>"
	                    	//+			"<p class='v_info_detail'><span class='info_play'>播放：<em class='info_data'>27141</em></span><span class='info_collect'>收藏：<em class='info_data'>774</em></span><span class='info_comment'>评论：<em class='info_data'>669</em></span></p>"
	                        +			"<p class='v_time_mark'>上次播放时间：" + data.resultList[i].modifiedTime + "</p>"
	                    	+		"</div>"
	                    	+	"</div>"
				            +"</li>"
				}
			}
			$(".v_list").html(html);
		}
	});
</script>
<div class="main_container">
	<h4 class="page_title">历史播放列表</h4>
    <p class="title_explain">这里为您保存最近的六次观看记录。</p>
    <div class="v_list_container">
    	<ul class="v_list">
        </ul>
    </div>
</div>