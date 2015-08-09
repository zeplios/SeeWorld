var flag_invitationcode = true;
var flag_username = false;
var flag_realname = false;
var flag_password1 = false;
var flag_password2 = false;
var flag_email = false;
var pwdreg = /^[A-Za-z0-9]+$/;
function check_username_isValid() {
	var username = $("#userName").val();
	if (username == null || username == "") {
		$("#username_error").removeClass().addClass("form_note").text("用户名不能为空");
		flag_username = false;
		return false;
	} else if (username.length < 3) {
		$("#username_error").removeClass().addClass("form_note").text("用户名长度不能小于3");
		flag_username = false;
		return false;
	} else {
		$("#username_error").removeClass().addClass("form_note").html("&nbsp;");
		flag_username = true;
		return true;
	}
}

function check_repeat_username() {
	if (check_username_isValid() == true) {
		var userName = $("#userName").val();
		$.ajax( {
			cache : false,
			url : '/SeeWorld/svc_ajax/user/User/checkUserName_json.action',
			type : 'post',
			dataType : 'json',
			data : {
				userName : userName
			},
			success : getUserResult
		});
	}
}
function getUserResult(json) {
	var result = json.fieldErrors["User_UserNameAlreadyExisted"];
	var usernameError = $("#username_error");
	if (result == "" || result == null) {
		$(usernameError).removeClass().addClass("form_note").text("用户名可以使用");
		flag_username = true;
		return true;
	} else {
		$(usernameError).removeClass().addClass("form_note_error").text("用户名已存在！");
		flag_username = false;
		return false;
	}
}

function check_password() {
	var password = $("#password1").val();
	if (password == null || password == "") {
		$("#password_error").removeClass().addClass("form_note_error").text("请设置密码");
		flag_password1 = false;
		return false;
	}
	if (password.length < 6 || password.length > 16 || !pwdreg.test(password)) {
		$("#password_error").removeClass().addClass("form_note_error").text("密码必须由6~16位数字或字母组成");
		flag_password1 = false;
		return false;
	} else {
		$("#password_error").removeClass().addClass("form_note").html("正确");
		flag_password1 = true;
		return true;
	}
}
function check_password2() {
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	if (flag_password1) {
		if (password1 != password2) {
			$("#password2_error").removeClass().addClass("form_note_error").text("两次输入的密码不一致");
			flag_password2 = false;
			return false;
		} else {
			$("#password2_error").removeClass().addClass("form_note").text("正确");
			flag_password2 = true;
			return true;
		}
	} else {
		$("#password2_error").removeClass().addClass("form_note").html("&nbsp;");
	}
}

function check_email() {
	var email = $("#email").val();
	if (email == null || email == "") {
		$("#email_error").removeClass().addClass("form_note_error").text("邮箱不能为空");
		flag_email = false;
		return false;
	}
	// 开始验证
	var pattern = /^([a-zA-Z0-9_-.])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	chkFlag = pattern.test(email);
	if (chkFlag) {
		$("#email_error").removeClass().addClass("form_note").html("&nbsp;");
		flag_email = true;
		return true;
	} else {
		$("#email_error").removeClass().addClass("form_note_error").text("邮箱格式不正确");
		flag_email = false;
		return false;
	}
}

function check_realname() {
	var realname = $("#realName").val();
	if (realname.length < 2 || realname.length > 4) {
		$("#realname_error").removeClass().addClass("form_note_error").text("请输入真实姓名");
		flag_realname = false;
		return false;
	} else {
		$("#realname_error").removeClass().addClass("form_note").html("&nbsp;");
		flag_realname = true;
		return true;
	}
}

function search_specialty() {
	var academy_id = $("#academyId").val();
	if(academy_id == 0){
		$("#specialty").empty().append("<option value='0'>未选择</option>");
	}
	if(academy_id > 0) {
		$("#specialty").empty().append("<option value='0'>-正在加载数据，请稍后-</option>");
		$.ajax({
			cache : false,
			url : '/SeeWorld/svc_ajax/user/Specialty/showSpecialtyList_json.action',
			type : 'post',
			dataType : 'json',
			data : {
				academy_id : academy_id
			},
			success : getSpecialtyResult
		});
	}
}
function getSpecialtyResult(json) {
	var list = json.resultList;
	if (list != null) {
		$("#specialty").empty();
		$.each(list, function(i, n) {
			$("#specialty").append("<option value='" + n.id + "'>" + n.name + "</option>");
		});
	}
}

/*
 * function checkPic(obj){//现在检查图片大小有错 picPath = obj.value;
 * document.getElementById("showPhoto").innerHTML=""; if(picPath!=""){ image =
 * new Image(); image.src =getPath(obj);
 * document.getElementById("photo_error").innerHTML="正在检查图片，请稍后"; PicIsValid();
 * }else{ document.getElementById("photo_error").innerHTML="&nbsp;"; return
 * false; } }
 * 
 * function PicIsValid(){ var error=document.getElementById("photo_error"); var
 * point = picPath.lastIndexOf("."); var type = picPath.substr(point);
 * if(type==".jpg"||type==".gif"||type==".JPG"||type==".GIF"){
 * alert(image.fileSize); if(image.fileSize>1024*100){
 * 
 * error.innerHTML="图片尺寸请不要大于100KB"; return false; }else{
 * error.innerHTML="&nbsp;"; document.getElementById("showPhoto").innerHTML= "<img
 * width='60' height='60' src='"+image.src+"'/>"; flag=true;return true; } }else{
 * error.innerHTML="只能选择jpg或者gif格式的图片"; return false; } }
 * 
 * function getPath(obj) {
 * 
 * //ie if (window.navigator.userAgent.indexOf("MSIE")>=1) { obj.select(); //
 * IE下取得图片的本地路径 return document.selection.createRange().text; } //firefox else
 * if(window.navigator.userAgent.indexOf("Firefox")>=1) { if(obj.files) { //
 * Firefox下取得的是图片的数据 return obj.files[0].getAsDataURL(); } } }
 */

UpLoadFileCheck = function() {
	this.AllowExt = ".jpg,.gif";// 允许上传的文件类型 0为无限制 每个扩展名后边要加一个"," 小写字母表示
	this.AllowImgFileSize = 0;// 允许上传文件的大小 0为无限制 单位：KB
	this.AllowImgWidth = 0;// 允许上传的图片的宽度 0为无限制 单位：px(像素)
	this.AllowImgHeight = 0;// 允许上传的图片的高度 0为无限制 单位：px(像素)
	this.ImgObj = new Image();
	this.ImgFileSize = 0;
	this.ImgWidth = 0;
	this.ImgHeight = 0;
	this.FileExt = "";
	this.ErrMsg = "";
	this.IsImg = false;// 全局变量

}

UpLoadFileCheck.prototype.CheckExt = function(obj) {
	this.ErrMsg = "";
	this.ImgObj.src = obj.value;
	// this.HasChecked=false;
	if (obj.value == "") {
		this.ErrMsg = "\n请选择一个文件";
	} else {
		this.FileExt = obj.value.substr(obj.value.lastIndexOf("."))
				.toLowerCase();
		if (this.AllowExt != 0 && this.AllowExt.indexOf(this.FileExt) == -1)// 判断文件类型是否允许上传
		{
			this.ErrMsg = "\n该文件类型不允许上传。请上传 " + this.AllowExt
					+ " 类型的文件，当前文件类型为" + this.FileExt;
		}
	}
	if (this.ErrMsg != "") {
		document.getElementById("photo_error").innerHTML = ErrMsg;
		this.ShowMsg(this.ErrMsg, false);
		return false;
	} else
		return this.CheckProperty(obj);
}

UpLoadFileCheck.prototype.CheckProperty = function(obj) {
	if (this.ImgObj.readyState != "complete")//
	{
		sleep(2000);// 一秒使用图能完全加载
	}

	if (this.IsImg == true) {
		this.ImgWidth = this.ImgObj.width;// 取得图片的宽度
		this.ImgHeight = this.ImgObj.height;// 取得图片的高度
		if (this.AllowImgWidth != 0 && this.AllowImgWidth < this.ImgWidth)
			this.ErrMsg = this.ErrMsg + "\n图片宽度超过限制。请上传宽度小于"
					+ this.AllowImgWidth + "px的文件，当前图片宽度为" + this.ImgWidth
					+ "px";

		if (this.AllowImgHeight != 0 && this.AllowImgHeight < this.ImgHeight)
			this.ErrMsg = this.ErrMsg + "\n图片高度超过限制。请上传高度小于"
					+ this.AllowImgHeight + "px的文件，当前图片高度为" + this.ImgHeight
					+ "px";
	}

	this.ImgFileSize = Math.round(this.ImgObj.fileSize / 1024 * 100) / 100;// 取得图片文件的大小
	alert(this.ImgFileSize);
	if (this.AllowImgFileSize != 0 && this.AllowImgFileSize < this.ImgFileSize)
		this.ErrMsg = this.ErrMsg + "\n文件大小超过限制。请上传小于" + this.AllowImgFileSize
				+ "KB的文件，当前文件大小为" + this.ImgFileSize + "KB";

	if (this.ErrMsg != "") {
		document.getElementById("photo_error").innerHTML = ErrMsg;
		this.ShowMsg(this.ErrMsg, false);
		return false;
	} else {
		alert(obj.value);
		document.getElementById("showPhoto").innerHTML = "<img width='60' height='60' src='"
				+ obj.value + "'/>";
		flag = true;
		return true;
	}
}

UpLoadFileCheck.prototype.ShowMsg = function(msg, tf)// 显示提示信息 tf=false
// 显示错误信息 msg-信息内容
{

	alert(msg);
}
function sleep(num) {
	var tempDate = new Date();
	var tempStr = "";
	var theXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	while ((new Date() - tempDate) < num) {
		tempStr += "\n" + (new Date() - tempDate);
		try {
			theXmlHttp.open("get", "about:blank?JK=" + Math.random(), false);
			theXmlHttp.send();
		} catch (e) {
			;
		}
	}
	// containerDiv.innerText=tempStr;
	return;
}

function check(obj) {
	var d = new UpLoadFileCheck();
	d.IsImg = true;
	d.AllowImgFileSize = 100;
	d.CheckExt(obj)
}
function checkInvitationCode() {
	var invitationCode = $("#form1_invitationCode").val();
	if (invitationCode.length == 6) {
		$
				.ajax( {
					cache : false,
					url : '/SeeWorld/svc_ajax/user/InvitationCode/checkInvitationCode_json.action',
					type : 'post',
					dataType : 'json',
					data : {
						id : invitationCode
					},
					success : checkInvitationResult
				});
	} else {
		$("#invitationCode_error").addClass("onError").html("邀请码位数不对");
		return false;
	}
}
function checkInvitationResult(json) {
	var result = json.message;
	if (result == "expired") {
		$("#invitationCode_error").addClass("onError").html("邀请码已过期");
		flag = false;
		return false;
	} else if (result == "used") {
		$("#invitationCode_error").addClass("onError").html("邀请码已被使用");
		flag = false;
		return false;
	} else if (result == "nonentity") {
		$("#invitationCode_error").addClass("onError").html("无效邀请码");
		flag = false;
		return false;
	} else {
		$("#invitationCode_error").addClass("onCorrect").html("&nbsp;");
		flag = true;
		return true;
	}
}

function check_form() {
	var flag = flag_invitationcode && flag_username && flag_realname
			&& flag_password1 && flag_password2 && flag_email;
	if (flag == true) {
		return true;
	} else {
		alertDiv("填入条件不完整");
		return false;
	}
}
