<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<title>SeeWorld上传图片</title>

		<script type="text/javascript" src="<%=basePath %>admin/scripts/jquery-1.4.2-min.js"></script>
		<script type="text/javascript" src="<%=basePath %>admin/scripts/jquery.form.js"></script>
		<script type="text/javascript">
			var x, y, w, h;
			var originPic = new Image();
			$(function(){
				$("#uploadForm").ajaxForm({
					beforeSubmit : function() {
						var fileName = $("#upload").val();
						var m = parseInt(fileName.toString().lastIndexOf(".")) + 1;
						var extVal = fileName.toString().substr(m).toLowerCase();
						if (extVal != "jpg" && extVal != "png"
								&& extVal != "gif" && extVal != "bmp") {
							alert('文件类型必须为图片文件！');
							return false;
						}
						$("#uploadbutton").attr('disabled', true);
						$("#uploadOutput").html('正在上传...');
					},
					dataType : "json",
					iframe : true,
					complete : function() {
						$("#uploadbutton").attr("disabled", false);
					},
					success : function(data) {
						var msg = data.fieldErrors;
						if (msg != null) {
							$("#errorZone").html(msg['upload'][0]);
							$out.empty();
						} else {
							$("#uploadOutput").html("");
							var picPath = decodeURIComponent(data.tempPicPath);
							picPath = picPath.substring(picPath.lastIndexOf('/') + 1);
							var imagePath = parent.document.getElementById("imagePath");
							imagePath.style.display = '';
							imagePath.value = picPath;
							//用于编辑页，隐藏原缩略图
							if (parent.document.getElementById("thumb") != null){
								parent.document.getElementById("thumb").style.display="none";
							}
						}
					}
				});
			});
		</script>
	</head>

	<body>
		<div id="mid_register">
			<div class="register" id="contentZone">
				<div id="errorZone"></div>
				<div id="uploadZone">
					<form id="uploadForm" action="/SeeWorld/util/upload/uploadPoster_json.action"
						method="post" enctype="multipart/form-data">
						<input type="hidden" name="MAX_FILE_SIZE" value="100000" />
						上传封面：<input type="file" name="upload" id="upload" />
						<input type="submit" value="上传" id="uploadbutton"/>
					</form>
				</div>
				<div id="uploadOutput"></div>
			</div>
		</div>
	</body>
</html>
