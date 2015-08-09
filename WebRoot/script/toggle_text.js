/**
 * 初始化文本的展开/收起操作
 * @param className 内部只有文字信息的标签的class名，要求父元素有position定位
 * @param maxLength 要显示的最大文字长度
 * @param allowToggle 是否设置展开/收起操作
 * @author zhfch
 */
function initToggle(className, maxLength, allowToggle){
	$("." + className).each(function(){
		var text = $(this).text();
		var short;
		if(text.length > maxLength){
			short = text.substring(0, maxLength) + "……";
			$(this).attr("origin", text);
			$(this).attr("short", short);
			$(this).text(short);
			if(allowToggle){
				var newLink = createLink("更多介绍");
				$(newLink).click(function(){
					$element = $(this).parent().find("." + className).first();
					if($(this).text() == "收起"){
						$element.text($element.attr("short"));
						$(this).text("更多介绍");
						return;
					}
					$element.text($element.attr("origin"));
					$(this).text("收起");
				});
				$(this).parent().get(0).appendChild(newLink);
			}
		}
	});
}

function createLink(text){
	var newlink=document.createElement("a");
	newlink.style.position="absolute";//设置更多信息/收起按键的位置
	newlink.style.bottom="3px";
	newlink.style.right="10px";
	newlink.style.background="url(../images/bg.png)";
	$(newlink).text(text);
	newlink.href="javascript:return false;";
	return newlink;
}