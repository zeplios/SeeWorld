package edu.tju.ina.seeworld.logic;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.struts.upload.FormFile;


public class UploadPhoto {
	private String newName;
	private String defaultName;
	public byte upload(FormFile photo, String path,String name) {
		if(photo!=null && photo.getFileSize()>=10 && photo.getFileName()!=null){

			try{
				String dir=path;
				String filename=photo.getFileName();
				String fileType=(filename.substring(filename.lastIndexOf('.')+1)).toLowerCase();
				newName=name+"."+fileType;
				if(photo.getFileSize()>2000000){
					   return -1;//体积太大
					  }	
				else if((!fileType.equals("jpg")) && (!fileType.equals("bmp")) && (!fileType.equals("gif")) && (!fileType.equals("jpeg"))){
					  
					  return -2;//格式不符合
					  }
				else{
					 String url=dir+"\\"+newName+"."+fileType;
					 InputStream streamIn=photo.getInputStream();
					 FileOutputStream streamOut=new FileOutputStream(url);
					 int bytesRead=0;
					  byte[] buffer=new byte[8192];
					  while((bytesRead=streamIn.read(buffer,0,8192))!=-1){
					   streamOut.write(buffer,0,bytesRead);
					  }
					  streamOut.close();
					  streamIn.close();
					  photo.destroy();
					  this.setNewName(name+"."+fileType);
					  return 1;
				 }

			}catch(Exception e){
				return -3;//
			}
			}else{
				newName=this.getDefaultName();
				return 0;//
				}
	
	}
	public String getDefaultName() {
		return defaultName;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
}
