<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="/SeeWorld/script/jquery.form.js" ></script>
<script type="text/javascript" src="/SeeWorld/script/personal/jquery.Jcrop.min.js" ></script>
<link type="text/css" href="/SeeWorld/style/jquery.Jcrop.css" rel="stylesheet" />
<script type="text/javascript">
	$(function(){
		var i = 0;
		//三个Tab页，默认显示第一个
		$(".sort_nav_item").each(function(){$(this).attr("index", i++)}).click(function(){$(this).addClass("item_current").siblings(".sort_nav_item").removeClass("item_current");$(".config_tab:eq(" + $(this).attr("index") + ")").show().siblings(".config_tab").hide();});
		$(".sort_nav_item:first").click();
		//默认显示的学院信息
		$("#academy").val("<s:property value="academy_id"/>")
		$("#academy").change(function(){
			$.ajax( {
				cache : false,
				url : '/SeeWorld/svc_ajax/user/Specialty/showSpecialtyList_json.action',
				type : 'post',
				dataType : 'json',
				data : { academy_id : $("#academy").val() },
				success : function(json){
					var list = json.resultList;
					var html = "";
					for(var i = 0 ; i < list.length ; i++){
						html += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
					}
					$("#specialty").html(html);
				}
			});
		})
		$("#academy").change();
		$("#academy").val("<s:property value="specialty_id"/>")
	})
	function check_email() {
		var email = $("#f_email").val();
		if (email == null || email == "") {
			return false;
		}
		// 开始验证
		var pattern = /^([a-zA-Z0-9_-.])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		chkFlag = pattern.test(email);
		if (chkFlag) {
			return true;
		} else {
			return false;
		}
	}
	
	function check_realname() {
		var realname = $("#f_realName").val();
		if (realname.length < 2 || realname.length > 8) {
			return false;
		} else {
			return true;
		}
	}
	/*修改个人信息部分*/
	function modifyInfo() {
		if(check_email() == false){
			alert("邮箱格式不正确！");
			return;
		}
		if(check_realname() == false){
			alert("请输入正确的真实姓名！");
			return;
		}
		$("#personal_info_form").ajaxSubmit({
			beforeSubmit : function() {
			},
			dataType : 'json',
			complete : function() {
			},
			success : function() {
				alert("修改成功！");
				location.reload();
			}
		});
	}
	function resetInfo(){
		$("#f_userName").val("<s:property escape='false' value="userName"/>");
		$("#f_email").val("<s:property escape='false' value="email"/>");
		$("#f_realName").val("<s:property escape='false' value="realName"/>");
		$("#form1_academyId").val("<s:property value="academy_id"/>")
	}
	/*修改密码部分*/
	function checkAndSubmit() {
		if($("#oldPassword").val() == $("#newPassword").val()){
			alert("新旧密码相同，请修改后提交！");
			return;
		}
		if($("#newPassword").val() != $("#newPassword2").val()){
			alert("两次输入的新密码不一致，请重新填写！");
			return;
		}
		var oldPassword = $("#oldPassword").val();
		var password = $("#newPassword").val();
		$.ajax( {
			cache : false,
			url : '/SeeWorld/svc_ajax/user/User/modifyPassword_json.action',
			type : 'post',
			dataType : 'json',
			data : {
				password : password,
				oldPwd : oldPassword
			},
			success : findPasswordResult,
			error : function() {
				alert("修改失败！");
			}
		});
	}
	function findPasswordResult(json) {
		var result = json.fieldErrors["User_UserPasswordInvalid"];
		if (result != null) {
			alert("原始密码错误，请重新填写!");
		} else {
			alert("修改成功！");
			location.reload();
		}
	}
	/*修改头像部分*/
	var x, y, w, h;
	var originPic = new Image();
	$(function() {
		$("#uploadForm").ajaxForm({
			beforeSubmit : function() {
				var fileName = $("#upload").val();
				var m = parseInt(fileName.toString().lastIndexOf(".")) + 1;
				var extVal = fileName.toString().substr(m).toLowerCase();
				if (extVal != "jpg" && extVal != "png" && extVal != "gif"
						&& extVal != "bmp") {
					alert("文件类型必须为指定类型！");
					return false;
				}
				$("#preview_image").attr("src", "");
				$("#uploadbutton").attr("disabled", "disabled");
				$(".upload_tips:first").text("正在上传...");
			},
			dataType : "json",
			iframe : true,
			complete: function(){
				$("#uploadbutton").attr("disabled", "");
			},
			success : function(data) {
				var msg = data.fieldErrors;
				if (msg != null) {
					$(".upload_tips:first").html(msg['upload'][0]);
				} else {
					var picPath=decodeURIComponent(data.tempPicPath);
					$("#origin_image").attr("src", picPath);
					$("#preview_image").attr("src", picPath);
					$("#upload_container").hide();
					$("#upload_confirm").show();
					//获得原始图片尺寸
					originPic.src = $("#origin_image").get(0).src;
					jQuery("#origin_image").Jcrop({
						handles : true,
						movable : true,
						allowMove : true,
						setSelect : [ 0, 0, 120, 168 ],
						onChange : showPreview,
						onSelect : showPreview,
						aspectRatio : 0.714
					});
					jQuery("#preview_image").css({
						width : originPic.width + "px",
						height : originPic.height + "px",
						marginLeft : "0px",
						marginTop : "0px"
					});
				}
			}
		});
		$("#saveAvatar").bind("click",
			function() {
				var name = $("#preview_image").attr("src");
				name = name.substring(name.lastIndexOf('/'));
				$.post("/SeeWorld/svc_ajax/user/User/saveAvatar_json.action", {
					x : x,
					y : y,
					w : w,
					h : h,
					tempAvatar : name
				}, function(data, status) {
					$("#upload_container").show();
					$("#upload_confirm").hide();
					$(".upload_tips:first").text("上传成功，可再次修改，请使用大小不超过1M的GIF,JPG或PNG格式图片。");
					$("#personal_avatar").attr("src", "/avatars/" + data.photoPath);/*修改左侧头像*/
				});
	
			});
	});
	
	function showPreview(coords) {
		if (parseInt(coords.w) > 0) {
			var rx = 120 / coords.w;
			var ry = 168 / coords.h;
			jQuery("#preview_image").css({
				width : Math.round(rx * originPic.width) + "px",
				height : Math.round(ry * originPic.height) + "px",
				marginLeft : '-' + Math.round(rx * coords.x) + "px",
				marginTop : '-' + Math.round(ry * coords.y) + "px"
			});
			x = coords.x;
			y = coords.y;
			w = coords.w;
			h = coords.h;
		}
	}
</script>
<div class="main_container">
    <div class="sort_nav">
        <ul class="sort_nav_list">
            <li class="sort_nav_item item_current"> <a class="sort_nav_item_a" href="#0" title="个人资料">个人资料</a> </li>
            <li class="sort_nav_item"> <a class="sort_nav_item_a" href="#0" title="密码修改">密码修改</a> </li>
            <li class="sort_nav_item"> <a class="sort_nav_item_a" href="#0" title="头像设置">头像设置</a> </li>
        </ul>
    </div>
    <div class="config_table_container config_tab">
    	<form name="personal_config" id="personal_info_form" method="post" action="/SeeWorld/svc_ajax/user/User/updateInfo_json.action">
        	<table class="config_table">
            	<tr id="noticeRow" style="display: none;">
					<td>
						<div id="noticeZone"></div>
						<input type="hidden" id="userId" name="id" value="<s:property value="id"/>" />
					</td>
				</tr>
            	<tr>
                	<td class="item_title" scope="col">用户名：</td>
                    <td class="item_content" scope="col">
                    	<input class="input_style3" type="text" id="f_userName" readonly="readonly" name="userName" value="<s:property value="userName"/>" />
                    </td>
                </tr>
                <tr>
                	<td class="item_title">邮箱：</td>
                    <td class="item_content">
                    	<input class="input_style3" type="text" id="f_email" name="email" value="<s:property value="email"/>"/><!-- <span class="item_wrong_tips">邮箱信息有误，请重新填写</span> -->
                    </td>
                </tr>
                <tr>
                	<td class="item_title">真实姓名：</td>
                    <td class="item_content">
                    	<input class="input_style3" type="text" id="f_realName" name="realName" value="<s:property value="realName"/>"/>
                    </td>
                </tr>
                <tr>
                	<td class="item_title">学院：</td>
                    <td class="item_content">
						<s:select name="academy_id" id="academy"
							list="academyList" listKey="id" listValue="name"
							cssClass="select_style2">
						</s:select>
                       </td>
                   </tr>
                   <tr>
                   	<td class="item_title">专业：</td>
                       <td class="item_content">
                   		<select name="specialty_id" id="specialty"
							class="select_style2">
							<option value="<s:property value="specialty_id"/>"><s:property value="specialty_name"/></option>
						</select>
                       </td>
                   </tr>
                   <tr>
                   <!-- 	<td class="item_title">年级：</td>
                       <td class="item_content">
                       	<select class="select_style2">
                       		<option value="2008">2008级</option>
                   		</select>
                       </td>
                   </tr> -->
               </table>
               <div class="submit_buttons_container submit_revise_data">
               	<a class="red_btn submit_first" id="user_modify" href="javascript:modifyInfo();" title="更新">更新</a>
                <a class="gray_btn submit_second" href="javascript:resetInfo();" title="重设">重设</a>
            </div>
        </form>
    </div>
    <div class="config_table_container config_tab">
    	<form name="personal_config" id="personal_info_form">
        	<table class="config_table">
            	<tr>
                	<td class="item_title" scope="col">原密码：</td>
                    <td class="item_content" scope="col">
                    	<input class="input_style3" type="password" name="oldPassword" id="oldPassword"/>
                    </td>
                </tr>
                <tr>
                	<td class="item_title">新密码：</td>
                    <td class="item_content">
                    	<input class="input_style3" type="password" name="password" id="newPassword" />
                    </td>
                </tr>
                <tr>
                	<td class="item_title">确认密码：</td>
                    <td class="item_content">
                    	<input class="input_style3" type="password" name="password2" id="newPassword2" />
                    </td>
                </tr>
            </table>
            <div class="submit_buttons_container submit_revise submit_revise_password">
            	<a class="red_btn submit_first" href="javascript:checkAndSubmit();" title="更新">修改</a>                      
            </div>
        </form>
    </div>
    <div class="avatar_upload config_tab">
    	<div class="upload_container" id="upload_container">
    		<form id="uploadForm" action="/SeeWorld/util/upload/uploadPhotoToTemp_json.action"
					method="post" enctype="multipart/form-data">
				<input type="hidden" name="MAX_FILE_SIZE" value="200000" />
				请选择新照片：
				<input type="file" name="upload" id="upload" />
				<br/>&nbsp;<br/>
				<div class="upload_btn">
					<input type="submit" class="gray_btn" value="点此上传本地图片" id="uploadbutton" />
				</div>
			</form>
        	<!-- <div class="upload_btn">
            	<a class="gray_btn" href="#0" title="点此上传本地图片">点此上传本地图片</a>
            </div> -->
            <p class="upload_tips">
            	请使用大小不超过2M的GIF,JPG或PNG格式图片。
            </p>
        </div>
        <div class="upload_confirm" id="upload_confirm" style="display:none">
        	<div class="avatar_demo_container">
            	<div class="avatar_demo">
                	<img class="avatar_demo_img" id="origin_image" src="" alt="原图" title="原图" />
                </div>
            </div>
        	<div class="avatar_demo_container" style="width:120px;height:208px;">
            	<div class="avatar_demo" style="width:120px;height:168px;">
                	<img class="avatar_demo_img" id="preview_image" src="" alt="预览" title="预览" />
                </div>
            </div>
            <p class="confirm_note">
            	已上传，确定使用本图片作为头像吗？
            </p>
            <div class="submit_buttons_container">
            	<a class="red_btn submit_first" id="saveAvatar" href="javascript:void(0);" title="确定">确定</a>
                <a class="gray_btn submit_second" href="#0" title="取消">取消</a>                         
            </div>
        </div>
    </div>
</div>
