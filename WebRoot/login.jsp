<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>SeeWorld——天津大学视频中心</title>
		<meta name="description" content="SeeWorld 天津大学视频中心" />
		<base href="/SeeWorld/"/>

		<link href="style/index.css" rel="stylesheet" type="text/css" />
		<link href="script/msgBox/jquery.msgbox.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="script/jquery-1.7.2.min.js" ></script>
		<script type="text/javascript" src="script/slides.min.jquery.js" ></script>
		<script type="text/javascript" src="script/seeworld_js.js" ></script>
		<script type="text/javascript" src="script/msgBox/jquery.msgbox.min.js" ></script>
		<script type="text/javascript" src="script/toggle_text.js" ></script>
		<script type="text/javascript" src="script/load_mainpage.js" ></script>
		<script type="text/javascript">
			$(document).ready(function(){
				/*msgbox这有个异常，所以把alertDiv暂时放到最后，留待以后解决*/
				menuControl();
				<s:if test="#parameters.login[0] == 'false'">
				alertDiv("请先登录！");
				</s:if>
				<s:elseif test="#parameters.error">
				alertDiv("密码错误！");
				</s:elseif>
			});
		</script>
	</head>

	<body>
		<s:include value="/WEB-INF/page/common/header.jsp"></s:include>
		<!---------------------------------------------- main -------------------------------------------->
		<div class="main">
			<div id="poster_list" class="poster">
				<ul class="poster_container">
				</ul>
				<div class="shadow"></div>
				<div class="line1"></div>
				<div class="line2"></div>
				<ul class="dots">
				</ul>
			</div>
			<div class="recommend">
				<ul>
				</ul>
			</div>
		</div>
		<!---------------------------------------------- footer ------------------------------------------>
		<s:include value="/WEB-INF/page/common/footer.jsp"></s:include>
	</body>
</html>
<s:debug/>