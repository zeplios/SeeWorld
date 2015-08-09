package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;


import edu.tju.ina.seeworld.apachetls.PrintCommandListener;
import edu.tju.ina.seeworld.ftp.FTP;
import edu.tju.ina.seeworld.ftp.FTPClient;
import edu.tju.ina.seeworld.ftp.FTPClientConfig;
import edu.tju.ina.seeworld.ftp.FTPFile;
import edu.tju.ina.seeworld.ftp.FTPReply;

/**
 * continueFTP�ṩ�˶�ftp���ù��ܵ�֧��
 */
public class ContinueFTP {
	//uploadProcessΪ�ļ��ϴ��Ľ��
    private float uploadProcess=0;
    
    //downloadProcessΪ�ļ����ؽ��
    private long dowmloadProcess=0;
    
	public FTPClient ftpClient = new FTPClient();

	public ContinueFTP() {
		// ���ý������ʹ�õ����������������̨
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(
				new PrintWriter(System.out)));
	}

	/** */
	/**
	 * ���ӵ�FTP������
	 * 
	 * @param hostname
	 *            ������
	 * @param port
	 *            �˿�
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return �Ƿ����ӳɹ�
	 * @throws IOException
	 */
	public boolean connect(String hostname, int port, String username,
			String password) throws IOException {
		ftpClient.connect(hostname, port);
		ftpClient.setControlEncoding("GBK");
		ftpClient.configure(getFtpConfig());
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			if (ftpClient.login(username, password)) {
				return true;
			}
		}
		disconnect();
		return false;
	}
    private static FTPClientConfig getFtpConfig() { 
        FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX); 
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING); 
        return ftpConfig; 
} 

	/** */
	/**
	 * ��FTP�������������ļ�,֧�ֶϵ����ϴ��ٷֱȻ㱨
	 * 
	 * @param remote
	 *            Զ���ļ�·��
	 * @param local
	 *            �����ļ�·��
	 * @return �ϴ���״̬
	 * @throws IOException
	 */
	public DownloadStatus download(String remote, String local)
			throws IOException {
		// ���ñ���ģʽ
		ftpClient.enterLocalPassiveMode();
		// �����Զ����Ʒ�ʽ����
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		DownloadStatus result;

		// ���Զ���ļ��Ƿ����
		FTPFile file = getFile(remote);
		if (file == null) {
			System.out.println(" Զ���ļ������� ");
			return DownloadStatus.Remote_File_Noexist;
		}

		long lRemoteSize = file.getSize();
		File f = new File(local);
		float process = 0f;
		// ���ش����ļ������жϵ�����
		if (f.exists()) {
			long localSize = f.length();
			// �жϱ����ļ���С�Ƿ����Զ���ļ���С
			if (localSize >= lRemoteSize) {
				System.out.println(" �����ļ�����Զ���ļ���������ֹ ");
				return DownloadStatus.Local_Bigger_Remote;
			}

			// ���жϵ�������¼״̬
			FileOutputStream out = new FileOutputStream(f, true);
			ftpClient.setRestartOffset(localSize);
			InputStream in = ftpClient.retrieveFileStream(new String(f
					.getName().getBytes(), "iso-8859-1"));
			byte[] bytes = new byte[1024];
			long step = lRemoteSize / 100;
			dowmloadProcess = localSize / step;
			int c;
			while ((c = in.read(bytes)) != -1) {
				out.write(bytes, 0, c);
				localSize += c;
				long nowProcess = localSize / step;
				if (nowProcess > dowmloadProcess) {
					dowmloadProcess = nowProcess;
					if (dowmloadProcess % 10 == 0)
						System.out.println(" ���ؽ�ȣ� " + dowmloadProcess);
					// TODO �����ļ����ؽ��,ֵ�����process������
				}
			}
			in.close();
			out.close();
			boolean isDo = ftpClient.completePendingCommand();
			if (isDo) {
				result = DownloadStatus.Download_From_Break_Success;
			} else {
				result = DownloadStatus.Download_From_Break_Failed;
			}
		} else {
			if (f.createNewFile()) {
				OutputStream out = new FileOutputStream(f);
				InputStream in = ftpClient.retrieveFileStream(new String(f
						.getName().getBytes(), "iso-8859-1"));
				byte[] bytes = new byte[1024];
				long step = lRemoteSize / 100;
				long localSize = 0L;
				int c;
				while ((c = in.read(bytes)) != -1) {
					out.write(bytes, 0, c);
					localSize += c;
					long nowProcess = localSize / step;
					if (nowProcess > dowmloadProcess) {
						dowmloadProcess = nowProcess;
						if (dowmloadProcess % 10 == 0)
							System.out.println(" ���ؽ�ȣ� " + dowmloadProcess);
						// TODO �����ļ����ؽ��,ֵ�����process������
					}
				}
				in.close();
				out.close();
				boolean upNewStatus = ftpClient.completePendingCommand();
				if (upNewStatus) {
					result = DownloadStatus.Download_New_Success;
				} else {
					result = DownloadStatus.Download_New_Failed;
				}
			} else
				result = DownloadStatus.Local_Create_Failed;
		}
		return result;
	}

	/** */
	/**
	 * �ϴ��ļ���FTP��������֧�ֶϵ���
	 * 
	 * @param local
	 *            �����ļ���ƣ����·��
	 * @param remote
	 *            Զ���ļ�·����ʹ��/home/directory1/subdirectory/file.ext����
	 *            http://www.guihua.org /subdirectory/file.ext
	 *            ����Linux�ϵ�·��ָ����ʽ��֧�ֶ༶Ŀ¼Ƕ�ף�֧�ֵݹ鴴�������ڵ�Ŀ¼�ṹ
	 * @return �ϴ����
	 * @throws IOException
	 */
	public  UploadStatus upload(String local,String remote)  throws  IOException {   
         // ����PassiveMode����   
        ftpClient.enterLocalPassiveMode();   
         // �����Զ��������ķ�ʽ����   
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
        ftpClient.setControlEncoding( "GBK" ); 
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);   
        conf.setServerLanguageCode("zh");  
        UploadStatus result=null;   
         // ��Զ��Ŀ¼�Ĵ���   
        //System.out.println(ftpClient.printWorkingDirectory()+"------------------1");
        String remoteFileName  =  remote;   
        if (remote.contains( "/" )) 
        {   
            remoteFileName  =  remote.substring(remote.lastIndexOf( "/" ) + 1 );   
             // ����������Զ��Ŀ¼�ṹ������ʧ��ֱ�ӷ���   
            if (CreateDirecroty(remote, ftpClient) == UploadStatus.Create_Directory_Fail)
            {   
                 return  UploadStatus.Create_Directory_Fail;   
            }    
         }    
         System.out.println(ftpClient.printWorkingDirectory()+"----------------------2");
 
         //ת������Ϊutf-8��ʾ
         String name =  new  String(remoteFileName.getBytes("utf-8"), "utf-8" );
         System.out.println(name);
         
         //ȡ��Զ��ftp�������ϵ��ļ��б�
         FTPFile[] files  =  ftpClient.listFiles();    
         if (files.length>0 ) { 
        	 
        	 //ȡ�ñ����ļ��Ĵ�С
        	 File f  =   new  File(local);    
	         long  localSize  =  f.length();
	         Boolean isFind=false;
	         int index=0;
	         int i;
        	 //���ϴ��ļ��������ļ��б��е��ļ����ֽ��жԱ�
        	 for( i=0;i<files.length;i++)
        	 {
        		 String str=new String(files[i].getName().getBytes("utf-8"),"utf-8");
        		 System.out.println(str+"-------------------------------4");
        	     if(str.equals(name))
        	     {
        		     isFind=true;
        		     index=i;
        		     System.out.println("fileName has existed!----------------------------5");
        		     break;
        	     } 
        	  } //forѭ��

            if(isFind==true)
            { 
            	long  remoteSize  =  files[index].getSize();
            	System.out.println(remoteSize+"-------------------------------------6");
   	            System.out.println(ftpClient.printWorkingDirectory()+"----------------------7");  
   	         
   	            //��Զ���ļ���С�뱾���ļ���С���жԱȣ���������������ļ��Ѵ���
   	            if (remoteSize == localSize)
   	            {   
   	               return  UploadStatus.File_Exits;   
   	            } 
   	            else   if (remoteSize  >  localSize) 
   	            {   
   	               return  UploadStatus.Remote_Bigger_Local;   
   	            }    
   	               
   	             // �����ƶ��ļ��ڶ�ȡָ��,ʵ�ֶϵ���   
   	            result  =  uploadFile(remoteFileName, f, ftpClient, remoteSize);   
   	               
   	            // ���ϵ���û�гɹ�����ɾ����������ļ��������ϴ�   
   	            if (result  ==  UploadStatus.Upload_From_Break_Failed)
   	            {   
   	                if ( ! ftpClient.deleteFile(remoteFileName))
   	                {
   	                    return UploadStatus.Delete_Remote_Faild;  
   	                }  
   	                result = uploadFile(remoteFileName, f, ftpClient, 0); 
                } 
            }        //end  �Գ��ȵ��ж�      
            else
            {
                System.out.println("do not find an exsited fileName------------------------8");
   	            result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
            }
        }           
        else
        {
        	 System.out.println("the remote file is empty------------------------9");
	         result = uploadFile(remoteFileName, new File(local), ftpClient, 0);  
        }
         return result;  
     }

	/**
	 * �Ͽ���Զ�̷�����������
	 * 
	 * @throws IOException
	 */
	public void disconnect() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.disconnect();
		}
	}

	/**
	 * �ݹ鴴��Զ�̷�����Ŀ¼
	 * 
	 * @param remote
	 *            Զ�̷������ļ����·��
	 * @param ftpClient
	 *            FTPClient����
	 * @return Ŀ¼�����Ƿ�ɹ�
	 * @throws IOException
	 */
	public UploadStatus CreateDirecroty(String remote, FTPClient ftpClient)
			throws IOException {
		UploadStatus status = UploadStatus.Create_Directory_Success;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !ftpClient.changeWorkingDirectory(new String(directory
						.getBytes("GBK"), "iso-8859-1"))) {
			// ���Զ��Ŀ¼�����ڣ���ݹ鴴��Զ�̷�����Ŀ¼
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end)
						.getBytes("GBK"), "iso-8859-1");
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("����Ŀ¼ʧ��");
						return UploadStatus.Create_Directory_Fail;
					}
				}

				start = end + 1;
				end = directory.indexOf("/", start);

				// �������Ŀ¼�Ƿ񴴽����
				if (end <= start) {
					break;
				}
			}
		}
		return status;
	}

	/**
	 * �ϴ��ļ���������,���ϴ��Ͷϵ���
	 * 
	 * @param remoteFile
	 *            Զ���ļ������ϴ�֮ǰ�Ѿ�������������Ŀ¼���˸ı�
	 * @param localFile
	 *            �����ļ�File�����·��
	 * @param processStep
	 *            ��Ҫ��ʾ�Ĵ����Ȳ���ֵ
	 * @param ftpClient
	 *            FTPClient����
	 * @return
	 * @throws IOException
	 */
	public UploadStatus uploadFile(String remoteFile, File localFile,
			FTPClient ftpClient, long remoteSize) throws IOException {
		UploadStatus status;
		// ��ʾ��ȵ��ϴ�
		float step = (float) localFile.length() / 100;
		long localreadbytes = 0L;
		RandomAccessFile raf = new RandomAccessFile(localFile, "r");
		if (raf != null) {
			System.out.println(raf);
		}
		OutputStream out = ftpClient.appendFileStream(new String(remoteFile
				.getBytes("GBK"), "iso-8859-1"));
		// �ϵ���
		if (remoteSize > 0) {
			ftpClient.setRestartOffset(remoteSize);
			uploadProcess = remoteSize / step;
			raf.seek(remoteSize);
			localreadbytes = remoteSize;
		}
		byte[] bytes = new byte[1024];
		int c;
		while ((c = raf.read(bytes)) != -1) {
			out.write(bytes, 0, c);
			localreadbytes += c;
			if (localreadbytes / step != uploadProcess) {
				uploadProcess = localreadbytes / step;
				System.out.println("�ϴ����:" + uploadProcess);
				// TODO �㱨�ϴ�״̬
			}
		}
		out.flush();
		raf.close();
		out.close();
		
		boolean result = ftpClient.completePendingCommand();
		ftpClient.disconnect();
		if (remoteSize > 0) {
			status = result ? UploadStatus.Upload_From_Break_Success
					: UploadStatus.Upload_From_Break_Failed;
		} else {
			status = result ? UploadStatus.Upload_New_File_Success
					: UploadStatus.Upload_New_File_Failed;
		}
		return status;
	}

	private String getDirectory(String path) {
		return path.indexOf('/') >= 0 ? path
				.substring(0, path.lastIndexOf('/')) : "";
	}

	private String[] getDirectories(String path) {
		if (path.startsWith("/"))
			path = path.substring(1);
		if (path.endsWith("/"))
			path = path.substring(0, path.length() - 2);
		return path.split("/");
	}

	private String getFileName(String path) {
		return path.indexOf('/') >= 0 ? path
				.substring(path.lastIndexOf('/') + 1) : path;
	}

	public FTPFile getFile(String remote) {
		try {
			String name = getFileName(remote);
			String[] dirs = getDirectories(getDirectory(remote));
			for (int i = 0; i < dirs.length; i++)
				ftpClient.changeWorkingDirectory(dirs[i]);
			FTPFile[] files = ftpClient.listFiles();
			for (FTPFile f : files) {
				System.out.println(f.getName());
				if (f.getName().equals(name)) {
					return f;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public float getUploadProcess(){
		return uploadProcess;
	}
	public long getDownloadProcess(){
		return dowmloadProcess;
	}

	public static void main(String args[]) {
		ContinueFTP myFtp = new ContinueFTP();
		try {
			myFtp.connect("202.113.13.92", 21, "ftpmgr", "vft)zk");
			// myFtp.ftpClient.makeDirectory(new
			// String("���Ӿ�".getBytes("GBK"),"iso-8859-1"));
			// myFtp.ftpClient.changeWorkingDirectory(new
			// String("���Ӿ�".getBytes("GBK"),"iso-8859-1"));
			// myFtp.ftpClient.makeDirectory(new
			// String("������".getBytes("GBK"),"iso-8859-1"));
			// System.out.println(myFtp.upload("E:\\yw.flv", "/yw.flv",5));
			myFtp.upload("C:/Users/asus/Desktop/hehe.txt", "/wind/hehe.txt");
			// System.out.println(myFtp.upload("E:\\������24.mp4",
			// "/����������/������/������24.mp4"));
			// myFtp.download("/xwood/profile/Attention.txt",
			// "D:/Attention.txt");
			// myFtp.download("/xwood/profile/�α�.docx", "G:/�α�.docx");
			// myFtp.printFilePath("/xwood/profile/");
			// myFtp.disconnect();
		} catch (IOException e) {
			System.out.println("����FTP���?" + e.getMessage());
		}
	}

}
