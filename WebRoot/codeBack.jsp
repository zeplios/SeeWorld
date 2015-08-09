<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>找回密码</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>codeBack</title>
	<link href="styles/init.css" type="text/css" rel="stylesheet" />
	<link href="styles/codeBack.css" type="text/css" rel="stylesheet" />
	<link href="styles/code_nav.css" type="text/css" rel="stylesheet" />
	<link href="styles/login.css" type="text/css" rel="stylesheet" />
	<link href="styles/film.css" type="text/css" rel="stylesheet" />
	<script src="js/jquery_last.js" type="text/javascript"></script>
	<link type="text/css" rel="stylesheet" href="styles/validator.css"></link>
	<script src="js/formValidator.js" type="text/javascript" charset="UTF-8"></script>
	<script src="js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript" src="./js/jquery-1.4.1.js"></script>
	<script language="javascript">
		function findPassword(){
			var userName=$("#userName").val();
			if(userName==""){
				alert("用户名不能为空");
			}else{
		   		 $.ajax({   
		       	 cache:false,   
		       	 url:'User_ajax/FindPassword.action',   
		       	 type:'post',   
		      	 dataType:'json',   
		       	 data:{userName:userName},   
		       	 success:getFindPassword
		   		 }); 
			 }
		}
		function getFindPassword(json){
			var result=json.message;
			if(result=="success"){
				alert("密码已经发到您的邮箱");
			}else if(result=="nameErr"){
				alert("该账户不能使用");
			}else{
				alert("邮件发送失败,请稍后再试");
			}
		}
	</script>

  </head>
  
  <body style="background-color:#ffffff;">
	      <div class="code_content">

   <div class="code-nav">
      <div class="first"><a href="index.html">首 页</a></div>
      <div class="getback"><a href="#">&gt;&gt; 找回密码</a></div>
   </div>
   
   <div class="clear"></div>
   
   <div id="mid_codeback">
   <div class="code_logo">
     <p><img alt="" src="images/code_logo.gif"/></p>
   </div>
   
  <div class="email">
  
      <form action="" method="post" name="form1" id="form1">
        <table class="tb">
        
         <tr class="tr1"> 
             <td class="td1">用户名</td>
             <td class="td2"><input id="userName" type="text" name="userName" class="io2" /></td>
             <td><div id="emailTip" class="td3"></div></td>
          </tr>
           <tr> 
             <td colspan="3" class="td4"><button type="button"  onclick="findPassword()" class="ok"/>找回密码</button></td>
          </tr>
       </table>
      </form>
  </div>
  <div class="clear"></div>
  
  </div><!--End of codeback-->
  
  <!-- footer -->
    <div class="line"></div>
   		<s:include value="./WEB-INF/page/common/footer.jsp"></s:include>
</div>
</body>
</html>
