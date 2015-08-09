<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>账号注册 SeeWorld-TJU</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<link href="/SeeWorld/style/account_signin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript" src="/SeeWorld/script/jquery-1.7.2.min.js" >
	</script>
	<script type="text/javascript" language="javascript" src="/SeeWorld/script/seeworld_js.js" ></script>
	<script type="text/javascript" language="javascript" src="/SeeWorld/script/personal/register.js" ></script>
	<script type="text/javascript" language="javascript" >
		$(document).ready(function(){
			menuControl();
		});
	</script>

  </head>
  
  <body>
    <s:include value="../common/header.jsp"></s:include>
	<div class="main">
	    <div class="narrow_main">
	    	<h2 class="signin_h2">注册</h2>
	        <div class="narrow_container_outside">
	        	<s:form id="form1" method="post" action="/svc/user/User/register.action" onsubmit="check_form()">
		        	<div class="narrow_container_inside">
		                <p class="top_note">在SeeWorld注册账号，即可以观看视频、进行评论视频。以下均为必填项目。</p>
		                <div class="signin_table_container">
		                    <table class="signin_table" cellpadding="0" cellspacing="0" border="0">
		                    	<s:if test='requireIC'>
							    <tr class="tr_bg">
							      <td class="register_td1">邀请码:</td>
							      <td class="register_td5"><s:textfield id="form1_invitationCode" name="invitationCode" onblur="checkInvitationCode()" cssClass="register_io2" ></s:textfield></td>
							      <td><div id="invitationCode_error"></div></td>
							    </tr>
								</s:if>
			                    <tr>
			                        <td class="item_title">用户名：</td>
			                        <td class="item_content">
			                            <s:textfield name="userName" id="userName" onblur="check_repeat_username()" 
			                            		onkeyup="check_username_isValid()" cssClass="input_style3" />
			                            <span class="form_note" id="username_error"></span>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="item_title">密码：</td>
			                        <td class="item_content">
			                        	<s:password name="password" id="password1" onblur="check_password()"  cssClass="input_style3" />
			                            <span class="form_note" id="password_error">请使用6位以上的密码</span>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="item_title">确认密码：</td>
			                        <td class="item_content">
			                        	<s:password name="password2" id="password2" onblur="check_password2()"  cssClass="input_style3" />
			                            <span class="form_note_error" id="password2_error">&nbsp;</span>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="item_title">邮箱：</td>
			                        <td class="item_content">
			                        	<s:textfield id="email" name="email" onblur="check_email()" cssClass="input_style3" />
			                            <span class="form_note" id="email_error"><!--电子邮箱用于找回您的密码--></span>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="item_title">真实姓名：</td>
			                        <td class="item_content">
			                        	<s:textfield name="realName" id="realName" onblur="check_realname()" cssClass="input_style3"/>
			                            <span class="form_note" id="realname_error">请输入真实姓名</span>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="item_title">学院：</td>
			                        <td class="item_content">
			                        	<s:select name="academy_id" id="academyId"  list="academyList" key="0" headerKey="0"
											headerValue="未选择" listKey="id" listValue="name" onchange="search_specialty();check_school()"
											cssClass="select_style2"></s:select>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="item_title">专业：</td>
			                        <td class="item_content">
			                        	<select name="specialty_id" id="specialty" class="select_style2">
											<option value="0">未选择</option>
										</select>
			                        </td>
			                    </tr>
		                    </table>
		                </div>
		                <div class="signin_rules">
		                    <p class="rule_note"><a class="rule_note_a" href="#0" title="">SeeWorld账号注册服务条款</a></p>
		                    <div class="rule_container">
		                        <div class="rule_content">天津大学视频中心服务协议
		                        </div>
		                    </div>
		                </div>
		                <div class="submit_container">
		                	<s:submit cssClass="signin_btn" value=""></s:submit>
		                </div>
		            </div>
	            </s:form>
	        </div>
	    </div>
	</div>
	<s:include value="../common/footer.jsp"></s:include>
  </body>
</html>
