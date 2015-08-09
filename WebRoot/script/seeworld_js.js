// JavaScript Document
var menuControl=function(){				//菜单控制函数
	var currentBox=0;
	var loginBtn=document.getElementById("login");
	var	searchBtn=document.getElementById("search");
	var loginMenu=document.getElementById("login_box");
	var	searchMenu=document.getElementById("search_box");
	var closeMenu=function(){
		if(loginMenu)
			loginMenu.style.display="none";
		searchMenu.style.display="none";
	}
	var menuClick=function(e){
		if(window.event){
			window.event.cancelBubble=true;	//取消事件冒泡 for IE
		}else{
			e.stopPropagation();	//取消事件冒泡 for W3C
		}
	}
	var toggleMenu=function(e){
		closeMenu();
		var targetID="";
		if(window.event){
			targetID=window.event.srcElement.id;
		}else{
			targetID=e.target.id;
		}
		if(targetID=="login"&&currentBox!=1){
			loginMenu.style.display="block";
			currentBox=1;
		}else if(targetID=="search"&&currentBox!=2){
			searchMenu.style.display="block";
			document.getElementById("search_input").focus();
			currentBox=2;
		}else{
			currentBox=0;
		}
	}
	var init=function(){
		if(searchBtn.addEventListener){
			document.addEventListener("click",toggleMenu,false);
			if(loginMenu)
				loginMenu.addEventListener("click",menuClick,false);
			searchMenu.addEventListener("click",menuClick,false);
		}else if(searchBtn.attachEvent){
			document.attachEvent("onclick",toggleMenu);
			if(loginMenu)
				loginMenu.attachEvent("onclick",menuClick);
			searchMenu.attachEvent("onclick",menuClick);
		}
	}
	init();
}

function alertDiv(content){
	$.msgbox({
        height:100,
        width:300,
        autoClose:3,
        content:content 
    });
}