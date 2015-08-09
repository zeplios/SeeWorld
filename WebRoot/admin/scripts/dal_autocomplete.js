/**
 * director, actor and lecturer autocomplete in the multimedia update and add page
 * id of textbox should be director, actor or lecturer
 * @author zhfch
 */
$(document).ready(function() {
	var args = {
		minChars : 1,
		max : 500,
		width : 250,
		matchSubset : false,
		matchContains : true,
		dataType : 'json',
		formatItem : function(item) {//显示下拉列表的内容  
			return "<div>"
					+ item.name
					+ "&nbsp;&nbsp;&nbsp;</div>";
		},
		formatMatch : function(item) {
			return item.name;
		},
		formatResult : function(item) {
			return item.name;
		}
	};
	var args_d = formPrivateAgrs(args, "director");
	$("#director").autocomplete("/SeeWorld/svc_ajax/multimedia/Multimedia/searchDirector_json.action", args_d);
	var args_a = formPrivateAgrs(args, "actor");
	$("#actor").autocomplete("/SeeWorld/svc_ajax/multimedia/Multimedia/searchActor_json.action", args_a);
	var args_l = formPrivateAgrs(args, "lecturer");
	$("#lecturer").autocomplete("/SeeWorld/svc_ajax/multimedia/Multimedia/searchLecturer_json.action", args_l);
});

function formPrivateAgrs(args, id){
	var args_new = args;
	args_new.extraParams = {
		q : function() {
			var s = $("#" + id).val();
			s = s.substring(s.lastIndexOf(" ") + 1)
			if(s == "")
				s = null;
			return s;
		}
	};
	args_new.parse = function(data) {
		var parsed = [];
		for ( var i = 0; i < data.resultList.length; i++) {
			parsed[parsed.length] = {
				data : data.resultList[i],
				value : data.resultList[i].name,
				result : $("#" + id).val().substring(0, $("#" + id).val().lastIndexOf(" ")) + " " + data.resultList[i].name
			};
		}
		return parsed;
	};
	return args_new;
}

function addActor() {
	window.showModalDialog("/SeeWorld/svc/multimedia/Actor/preAdd.action", "",
			"dialogWidth:300px;dialogHeight:100px;center:yes;status:no;scroll:no;help:no;");
	return false;
}
function addDirector() {
	window.showModalDialog("/SeeWorld/svc/multimedia/Director/preAdd.action", "",
			"dialogWidth:300px;dialogHeight:100px;center:yes;status:no;scroll:no;help:no;");
	return false;
}
function addLecturer(){
	window.showModalDialog("/SeeWorld/svc/multimedia/Lecturer/preAdd.action", "",
			"dialogWidth:300px;dialogHeight:100px;center:yes;status:no;scroll:no;help:no;");
	return false;
}