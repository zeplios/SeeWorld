<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
	.completeDIV{
	border: #CCCCCC 1px solid;
	width:670px;
	min-height:100px;
	padding-bottom:10px;
	}
	.completeDIV div{
		position:relative;
		top:-10px;
		left:20px;
		width:70px;
		background:#FFFFFF;
		font-size:14px;
		color:#999999;
	}
	.completeDIV li{
	list-style:none;
	margin-left:20px;
	overflow:hidden;
	margin-bottom:5px;
	font-size:12px;
	color:#999999;
	}
	.completeDIV input{
	width:50px;	
	}
	.process,.percentage{
		width:250px;
		height:15px;
	}
	.percentage{
	z-index:999;
	text-align:center;
	color:#808080;
	font-size:12px;
	position:relative;
	top:-15px;
	left:0px;
	}
	.process{
		border:#B4D7FE 1px solid;
		background:#FCFFEE;
	}
	.process div{
	background:#9BF8FF;
	width:0px;
	height:15px;
	}
	.status{
	font-size:12px;
	color:#666666;

	}
	.status span{
	width:170px;
	overflow:hidden;
	}
	.status a{	
	color:#666666;
	font-size:12px;
	padding-right:5px;}
	.status a:hover{
	color:#FF0000;
	font-size:12px;
	padding-right:5px;
	}
	.status a:active{
	color:#666666;
	font-size:12px;
	padding-right:5px;
	}
	
	</style>
	<script src="<%=basePath%>js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	var fileInfo=new Array(5);
	var remoteDirectoy="<s:property value="directory" />";//�ϴ��ļ���·��
	var type=<s:property value="type" />;//�ϴ����ͣ������¾缯�����µĵ�Ӱ
	var multimediaID=<s:property value="mulID" />;
	for(var i=0;i<=4;i++){
		fileInfo[i]=new Array(3);
	}
	function getRemotePath(){
		return remoteDirectoy;
	}
	function setLocalPath(path,size,index){//ע���ϴ��ļ�������ַ
		fileInfo[index][0]=path;
		fileInfo[index][1]=size;
		fileInfo[index][2]=path.substring(path.lastIndexOf('\\')+1,path.length);
		document.getElementById("status"+index).innerHTML=path+"&nbsp;&nbsp;<a href='javascript:cancel("+index+")'>ȡ��</a>";
		document.getElementById("percentage"+index).innerHTML="�ȴ��ϴ�";
		document.getElementById("process"+index).style.width="0px";
	}

	function setProcess(process,i){//���ý���
						if(process==1){
							document.getElementById("status"+i).innerHTML="�����ϴ�:"+fileInfo[i][0];
							createFile(i);
						}
						document.getElementById("percentage"+i).innerHTML=process+"%";
						var temp=process/100*250;
						document.getElementById("process"+i).style.width=temp+"px";
						if(process==100){
							document.getElementById("status"+i).innerHTML="���ڸ����ϴ���Ϣ�����Ժ�:"+fileInfo[i][0];
							findFile(i);
						}
}
	function cancel(index){//ȡ��
		document.getElementById("AppletFTP").cancel(index);
		fileInfo[index][0]="";
		fileInfo[index][1]=0;
		fileInfo[index][2]="";
		document.getElementById("status"+index).innerHTML="��ѡ���ϴ��ļ�";
		document.getElementById("percentage"+index).innerHTML="0%";
		document.getElementById("process"+index).style.width="0px";
	}

	function createFile(index){//��ʼ�ϴ�ʱ��ͬ���������ݿ⣬�����µ���ʱ�ļ���¼
		$.ajax({   
       			cache:false,   
       			url:'backgrounjson/file_createFile.action',   
       			type:'post',   
      			dataType:'json',   
       			data:{multimediaID:multimediaID,path:remoteDirectoy+"/"+fileInfo[index][2],size:fileInfo[index][1],type:type},   
       			success:getCreateFile
   			}); 
	}
	function getCreateFile(json){
	
	}
	function findFile(index){//�ϴ������󣬸�����ʱ�ļ���¼״̬
			$.ajax({   
       			cache:false,   
       			url:'backgrounjson/file_findFile.action',   
       			type:'post',   
      			dataType:'json',   
       			data:{path:remoteDirectoy+"/"+fileInfo[index][2],index:index},   
       			success:getFindFile
   			}); 
	}
	function getFindFile(json){
		document.getElementById("complete").innerHTML+="<li><span  id='filestatus_"+json.file.id+"'>������ļ���Ϣ</span>:"+fileInfo[json.index][0]+"&nbsp;&nbsp;���<input id='sequence_"+json.file.id+"' name='' type='text'>&nbsp;&nbsp;��ǩ��<input id='otherInfo_"+json.file.id+"' name='' type='text'><input name='' type='button' value='�޸�' onClick='finishFile("+json.file.id+");' /></li>";
		document.getElementById("status"+json.index).innerHTML="����ϴ�,���������ϴ���Ϣ:"+fileInfo[json.index][0]+"&nbsp;&nbsp;<a href='javascript:cancel("+json.index+")'>����</a>";
		
	}
	function finishFile(id){//����ϴ���¼��Ϣ��ͬ�����ݿ�
		var sequence=document.getElementById("sequence_"+id).value;
		var otherInfo=document.getElementById("otherInfo_"+id).value;
		$.ajax({   
       			cache:false,   
       			url:'backgrounjson/file_finishFile.action',   
       			type:'post',   
      			dataType:'json',   
       			data:{id:id,sequence:sequence,otherInfo:otherInfo},   
       			success:getFinishFile
   			}); 
	}
	function getFinishFile(json){
		document.getElementById("filestatus_"+json.file.id).innerHTML="�����";
	}
	</script>
  </head>
  <body >

  <div class="completeDIV" id="complete">
  <div>������ϴ�
  </div>

  </div>
  <br>
  <div style="width:105px; float:left">
  <!--
  <applet codebase="C:\Users\Administrator\Documents"
            code="applet.AppletFTP.class"
            archive ="AppletFTP.jar"
id="AppletFTP" 
name = "AppletFTP"
width = 100
height = 250
hspace = 0
vspace = 0
align = middle >
    </applet>
	-->
	
<object id="AppletFTP"
	classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
	codebase="http://java.sun.com/update/1.5.0/jinstall-1_5-windows-i586.cab#Version=5,0,0,3"
	WIDTH=100 HEIGHT=300 NAME = "myapp" > 
	<PARAM NAME = CODE VALUE = "applet.AppletFTP.class" >
	<PARAM NAME = CODEBASE VALUE = "<%=basePath%>" >
	<PARAM NAME = ARCHIVE VALUE = "AppletFTP.jar" >
	<PARAM NAME = NAME VALUE = "appletftp" >
	<param name = "type" value = "application/x-java-applet;version=1.5">
	<param name = "scriptable" value = "true">
	</object>
  </div>
  <div style="height:250px; vertical-align:middle; ">
  <table width="577" border="0" height="242" >
    <tr>
      <td width="186" height="43" class="status" id="status0">��ѡ���ϴ��ļ�</td>
      <td width="276" style="position:relative "><div class="process" ><div id="process0"></div></div><div class="percentage" id="percentage0">0%</div></td>
    </tr>
    <tr>
      <td height="45" class="status" id="status1">��ѡ���ϴ��ļ�</span></td>
      <td style="position:relative"><div class="process" ><div id="process1"></div></div><div class="percentage" id="percentage1">0%</div></td>
    </tr>
    <tr>
      <td height="43" class="status" id="status2">��ѡ���ϴ��ļ�</span></td>
      <td style="position:relative"><div class="process" ><div id="process2"></div></div><div class="percentage" id="percentage2">0%</div></td>
    </tr>
    <tr>
      <td height="46" class="status" id="status3">��ѡ���ϴ��ļ�</span></td>
      <td style="position:relative"><div class="process" ><div id="process3"></div></div><div class="percentage" id="percentage3">0%</div></td>
    </tr>
    <tr>
      <td class="status" id="status4">��ѡ���ϴ��ļ�</td>
      <td style="position:relative"><div class="process" ><div id="process4"></div></div><div class="percentage" id="percentage4">0%</div></td>
    </tr>
  </table>
  
  </div>
 
  </body>
</html>
