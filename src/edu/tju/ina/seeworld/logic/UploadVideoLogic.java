package edu.tju.ina.seeworld.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;

import test.ContinueFTP;
import edu.tju.ina.seeworld.dao.resource.IFormatDAO;
import edu.tju.ina.seeworld.dao.resource.IVideoDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Format;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.po.user.User;

/**
 * ����ʵ���˱���video����ͽ���Ƶ�ļ��ϴ����������ϵĹ���
 * @author IBM
 */
public class UploadVideoLogic 
{	
	private IVideoDAO videoDao;
	
	private IFormatDAO formatDao;
	
	private ContinueFTP continueFtp;
	
	/** 
	 * 	
	 *  
	 *  @author IBM
	 *  
	 * 	@param  user 
	 * 			�ϴ���
	 *  @param  name 
	 *  		��Ƶ���
	 *  @param  introduction
	 *  		��Ƶ���
	 *  @param	clientPath
	 *  		��Ƶ�Ŀͻ���·��
	 *  @param	addtime
	 *  		��Ƶ�ϴ�ʱ��
	 *  @param	fileName
	 *  		��Ƶ�ļ������
	 * @throws IOException 
	 * @throws SeeWorldException 
	 *  
	 * */
	public Integer saveVideo(Category category,User user,String name,String introduction,String speaker,String clientPath,Timestamp addtime,String fileName,Integer status,Float process,File image) throws IOException, SeeWorldException
    {   
		//��ȡ�û��ϴ��ļ�·�������һ��"."��λ��
		int posOfDot = clientPath.lastIndexOf(".")+1;
		
		//��ȡ�ļ���׺��·�������һ��"."��������ַ�
		String suffix = clientPath.substring(posOfDot);
		
		//�Ժ�׺���ȡformat����
		Format format = (Format) formatDao.findByProperty("format", suffix).get(0);
	    Integer delete=new Integer(0);
	    
	    //System.out.println("clientPath:" + clientPath);
	    //FTPFile file = continueFtp.getFile(clientPath);
	    //.out.println("fileName:" + file.getName());
	    
	    Integer size = 5;
	    
	    Boolean delete_ = false;
	    Boolean status_ = false;
	       
	    FileInputStream in = new FileInputStream(image);
	    byte[] img = new byte[(int) in.available()];
	    in.read(img);

		//���video����
//		Video video = new Video(category,user,format,name, img, clientPath,size,status_,process,delete_ ,addtime,speaker);
		
		//������Ƶ���
//		video.setIntroduction(introduction);
		
		//������Ƶ�ݽ���
//		video.setSpeaker(speaker);
		
		//����video����
//		videoDao.save(video);
		
//		return video.getId();
	    return 1;
    }
	
	
	public Boolean uploadVideo(String clientPath,String name,String categoryPath,Integer videoId) throws SeeWorldException
	{
    	//��Ƶ�ļ��ϴ��Ƿ��Ѿ���� 
    	Boolean isCompleted=true;
    	
    	//��ȡ�û��ϴ��ļ�·�������һ��"."��λ��
		int posOfDot = clientPath.lastIndexOf(".")+1;
		
		//��ȡ�ļ���׺��·�������һ��"."��������ַ�
		String suffix = clientPath.substring(posOfDot);
		
		int posOfSlash = clientPath.lastIndexOf("/")+1;
		
		//��ȡ�ļ���·�������һ��"/"��������ַ�
		String fileName = clientPath.substring(posOfSlash);
		
		//���û��ϴ��ļ���������������浽�ĸ�·��ΪserverRootPath
		String serverRootPath = "/wind/" + categoryPath + name + "_" + videoId + "/"; 
		
		//���û��ϴ��ļ���������������浽��·��ΪserverPath
		String serverPath = serverRootPath + fileName;
		
		System.out.println(clientPath+"---------"+serverPath);
		
    	try
		{
			//�����������������
			continueFtp.connect("202.113.13.92", 21, "ftpmgr", "vft)zk"); 
			
			//ʵ���ļ����ϴ�
			continueFtp.upload(clientPath,serverPath);//���ļ����û�����д�ĸ�ʽ���������
		}
		catch(IOException e)
		{
			isCompleted = false;
			Float process = continueFtp.getUploadProcess();
		}
        
		Video video = videoDao.findById(videoId);
		video.setPath(serverPath);
		videoDao.save(video);
		
    	return  isCompleted;
    	
    	
    }
			

	//���¾�Ϊ����˽�б�����getter��setter����
	

	public void setVideoDao(IVideoDAO videoDao) {
		this.videoDao = videoDao;
	}


	public void setFormatDao(IFormatDAO formatDao) {
		this.formatDao = formatDao;
	}


	public void setContinueFtp(ContinueFTP continueFtp) {
		this.continueFtp = continueFtp;
	}
		
}
