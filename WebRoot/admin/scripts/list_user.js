/*
 * handle dataTable in the page listUser.jsp
 */
(function($) {
	/*
	 * Function: fnGetColumnData
	 * Purpose: Return an array of table values from a particular column.
	 * Returns: array string: 1d data array
	 * Inputs: object:oSettings - dataTable settings object. This is always the last argument past to the function
	 * int:iColumn - the id of the column to extract the data from
	 * bool:bUnique - optional - if set to false duplicated values are not filtered out
	 * bool:bFiltered - optional - if set to false all the table data is used (not only the filtered)
	 * bool:bIgnoreEmpty - optional - if set to false empty values are not filtered from the result array
	 * Author: Benedikt Forchhammer <b.forchhammer /AT\ mind2.de>
	 */
	$.fn.dataTableExt.oApi.fnGetColumnData = function(oSettings, iColumn,
				bUnique, bFiltered, bIgnoreEmpty) {
		// check that we have a column id
		if (typeof iColumn == "undefined")
			return new Array();
		// by default we only wany unique data
		if (typeof bUnique == "undefined")
			bUnique = true;
		// by default we do want to only look at filtered data
		if (typeof bFiltered == "undefined")
			bFiltered = true;
		// by default we do not wany to include empty values
		if (typeof bIgnoreEmpty == "undefined")
			bIgnoreEmpty = true;
		// list of rows which we're going to loop through
		var aiRows;
		// use only filtered rows
		if (bFiltered == true)
			aiRows = oSettings.aiDisplay;
		// use all rows
		else
			aiRows = oSettings.aiDisplayMaster; // all row numbers
		// set up data array
		var asResultData = new Array();
		for ( var i = 0, c = aiRows.length; i < c; i++) {
			iRow = aiRows[i];
			var aData = this.fnGetData(iRow);
			var sValue = aData[iColumn];
			// ignore empty values?
			if (bIgnoreEmpty == true && sValue.length == 0)
				continue;
			// ignore unique values?
			else if (bUnique == true
					&& jQuery.inArray(sValue, asResultData) > -1)
				continue;
			// else push the value onto the result data array
			else
				asResultData.push(sValue);
		}
		return asResultData;
	}
}(jQuery));

function fnCreateSelect(aData) {
	var r = '<select><option value="">全选</option>', i, iLen = aData.length;
	//这段有问题.jquery.dataTable这个js的问题，这里aData取出的数带有"<tr><a ...></tr>"等一堆标签
	//但是在FF和chrome都能比较好的处理，只是IE不给力啊，
	//下面这段代码自已写的,但是aData里的数据只适合这种格式 <tr><a href=>abc</a><tr>
	//其它格式的估计就还得再改了...
	for (i = 0 ; i < iLen ; i++) {
		if (aData[i].indexOf("<") == -1) {
			r += '<option value="' + aData[i] + '">' + aData[i] + '</option>';
		} else {
			var data_tmp = aData[i];
			var data_firstSplit = data_tmp.split(">");
			var data_secondSplit = data_firstSplit[2].split("<");
			r += '<option value="' + data_secondSplit[0] + '">'
					+ data_secondSplit[0] + '</option>';
		}
	}
	//alert(r);
	return r + '</select>';
}
function createSelect() {
	$("tfoot th").each(function(i) {
		if ((i != 5) && (i != 6)){
			this.innerHTML = fnCreateSelect($('#example').dataTable()
					.fnGetColumnData(i));
			$('select', this).change(function() {
				$('#example').dataTable().fnFilter($(this).val(), i);
			});
		};
	});
}

function getUserList(){
	$.ajax( {
		"cache" : false,
		"dataType" : "json",
		"type" : "POST",
		"url" : "/SeeWorld/svc_ajax/user/User/findAllUser_json.action",
		"success" : dataTableShow
	});
}
$(document).ready(function() {
	var oTable = $('#example').dataTable({
		"oLanguage" : {
			"sSearch" : "快速搜索:"
		},
		"bAutoWidth" : false
	});
	
	getUserList();
	//延迟2秒执行createSelect建立筛选，否则有问题，原因可能是ajax有延迟
	//setTimeout('createSelect()', 2000);
});

function dataTableShow(json) {
	$('#example').dataTable().fnClearTable();
	var list = json.resultList;
	for ( var i = 0; i < json.resultList.length; i++) {
		var user = list[i];
		var enable = "";
		if(user.enabled)
			enable = "<a class='permissionDeny' id='" + user.id + "' href='javascript:void(0)'>禁止</a>";
		else
			enable = "<a class='permissionAllow' id='" + user.id + "' href='javascript:void(0)'>恢复</a>"
		$('#example').dataTable().fnAddData([
			"<td><a href=#userinfo rel='modal'>" + user.userName + "</a></td>",
			user.realName,
			user.academy_name,
			user.specialty_name,
			user.roleName,
			"<td class='center'>" + enable + "</td>",
			"<td class='center'><a href='javascript:void(0)' id='"
					+ user.id
					+ "' class='deleteUser'><img src='images/icons/cross_circle.png'/></a></td>"
		]);
	}
	
	$('.permissionDeny').bind('click', function(){
		$.ajax({
			"cache" : false,
			"dataType" : 'json',
			"type" : "POST",
			"url" : "/SeeWorld/svc_ajax/user/User/banUser_json.action",
			"data" : {
				userId : this.id
			},
			"success" : function(){
				alert("禁止权限成功！");
				getUserList();
			}
		});
	});
		
	$('.permissionAllow').bind('click', function() {
		$.ajax( {
			"cache" : false,
			"dataType" : 'json',
			"type" : "POST",
			"url" : "/SeeWorld/svc_ajax/user/User/allowUser_json.action",
			"data" : {
				userId : this.id
			},
			"success" : function(){
				alert("恢复权限成功！");
				getUserList();
			}
		});
	});

	$('.deleteUser').bind('click', function() {
		$.ajax( {
			"cache" : false,
			"dataType" : 'json',
			"type" : "POST",
			"url" : "/SeeWorld/svc_ajax/user/User/deleteUser_json.action",
			"data" : {
				userId : this.id
			},
		    "success" : function(){
		    	alert("删除用户成功！");
		    	getUserList();
		    }
		});
	});
	//建立筛选
	createSelect();
}
