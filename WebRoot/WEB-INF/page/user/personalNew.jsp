<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/SeeWorld/script/toggle_text.js"></script>
<script type="text/javascript" src="/SeeWorld/script/personal/new_things.js"></script>

<div class="main_container">
	<h4 class="page_title">最新动态</h4>
    <p class="title_explain">您可以在这里看到SeeWorld中其他用户的动态。</p>
    
    <div class="v_list_container">
    	<ul class="v_list">
        </ul>
        <a id="moreNewThings" href="javascript:findNewThings();" style="display:none;padding-left:300px;padding-top:10px;">查看更多新鲜事</a>
        <div id='loadingNewThings' style="display:none;padding-left:260px;padding-top:10px;">
			<img src='<s:property value="basePath"/>images/loading.gif' />
			正在加载新鲜事, 请稍后……
		</div>
    </div>
    
    <div class="page_nav">
    </div>
</div>



