package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import edu.tju.ina.seeworld.ftp.FTPClient;

public class testUpload {

	/**
	 * @param args
	 */
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
       //ContinueFTP conFtp=new ContinueFTP();
      //FTPClient ftpclient=new FTPClient();
      //String local ="G:/test/test.txt";
      //String remote="/wind/test.txt";
     // Boolean connected=false;
      //try {  
      // connected=conFtp.connect("202.113.13.92", 21, "ftpmgr", "vft)zk");  
      //} catch (IOException e) {  
      //    System.out.println("����FTP���?"+e.getMessage());  
     // }  
      //if(connected==true){
    	//  try{
    	//  conFtp.upload(local, remote);
     // }
    	//  catch(IOException ex){
    	//	  System.out.println("�ϴ�ʧ��ԭ��"+ex.getMessage());
    	 // }
	////}

//}
	 public static void testUpload() {    
	        FTPClient ftpClient = new FTPClient();    
	        FileInputStream fis = null;    
	  
	        try {    
	            ftpClient.connect("202.113.13.92");    
	            ftpClient.login("ftpmgr", "vft)zk");    
	            File srcFile = new File("G:/test/test.txt");    
	            fis = new FileInputStream(srcFile);    
	            //�����ϴ�Ŀ¼    
	            ftpClient.changeWorkingDirectory("/wind");    
	            ftpClient.setBufferSize(1024);    
	            ftpClient.setControlEncoding("GBK");    
	            //�����ļ����ͣ������ƣ�    
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
	            Boolean OK=ftpClient.storeFile("test.txt", fis);
	                
	            if(OK==true){
	            	System.out.println("�ɹ���"); 
	            	} 
	        } catch (IOException e) {    
	            e.printStackTrace();       
	        }   
	        }   
	 public static void main(String[] args){
		 testUpload();
	 }
	    }    


