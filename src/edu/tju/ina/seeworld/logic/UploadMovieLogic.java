package edu.tju.ina.seeworld.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import test.ContinueFTP;
import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.dao.resource.CategoryDAO;
import edu.tju.ina.seeworld.dao.resource.IActorDAO;
import edu.tju.ina.seeworld.dao.resource.IAreaAndCountryDAO;
import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.dao.resource.IDirectorDAO;
import edu.tju.ina.seeworld.dao.resource.IFormatDAO;
import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.dao.user.UserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Format;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.user.User;

public class UploadMovieLogic {

	private IMovieDAO movieDao;
	private IAreaAndCountryDAO areaDao;
	private IFormatDAO formatDao;
	private IDirectorDAO dirDao;
	private IActorDAO actorDao;
	private ICategoryDAO categoryDao;
	private IUserDAO userDao;
	private IRbacDAO resourceDao;

	public void setResourceDao(IRbacDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * ��Springע���ContinueFTP.java��Ķ���ContinueFTP.java���еķ���ʵ������Ƶ���ϴ���
	 * ��
	 */
	private ContinueFTP continueFtp;
	
	
	public boolean saveMovie(String category,String userid, String name, String introduction, String director, List<String> actors, String area, String year, String path) throws SeeWorldException{
		System.out.println(path);
		for(String actor:actors){
		System.out.println(actor);
		}
		System.out.println(category);
		System.out.println(name);
		System.out.println(introduction);
		System.out.println(director);
		System.out.println(area);
		System.out.println(year);
		
		//获得影片的类型
		List<Category> categoryList=categoryDao.findByName(category);
		Category type=categoryList.get(0);
		
		//获取上传者的信息
		List<User> userList=userDao.findByProperty("ID", userid);
		User user=userList.get(0);
		
		//获取资源类型MOVIE,VIDEO,PLAY,BOOK
		String resoureceName="MOVIE";
		Resource resourceLis=(Resource) resourceDao.findByName(resoureceName);
		
		
		
		for(String s:getMovieFilePath(path)){
			String format=getFormat(s);
			
		}
			
		return true;
	}
   
	//获取文件名
	 public String getFileName(String str){

		 return str.substring(str.lastIndexOf("\\")+1, str.length());
	 }
	//获取后缀
	public String getFormat(String s){
		String suffix=s.substring(s.lastIndexOf(".")+1, s.length());
		return suffix;
	}
	//获取电影路径
	 public List<String> getMovieFilePath(String path){
		 ArrayList<String> movieFilePathList=new ArrayList<String>();
		 String[] p=path.split("&&");
		 for(String s:p){
			 String suffix="";
			 if(s.lastIndexOf(".")>0){
			 suffix=getFormat(s);
			 }
			 if(isOneOfMoveFormat(suffix)){
				 movieFilePathList.add(s);
			 }
		 }
		 return movieFilePathList;
	 }
	 
	 //获得图片路径，不是影片就是图片
	 public String getImgFilePath(String path){
		 String imgFilePath="";
		 String[] p=path.split("&&");
		 for(String s:p){
			 String suffix="";
			 if(s.lastIndexOf(".")>0){
			 suffix=s.substring(s.lastIndexOf(".")+1, s.length());
			 }
			 if(!isOneOfMoveFormat(suffix)){
				  imgFilePath=suffix;
			 }
		 }
		 return imgFilePath;
		 
	 }
	
	 //判断是否为电影格式中的一种
	boolean isOneOfMoveFormat(String suffix){
		return ("rm".equals(suffix)||"rmvb".equals(suffix)||"wmv".equals("suffix")||"flv".equals(suffix));
	}

	public Integer saveMovie(Category category, User user, String name,
			String introduction, String director, String actor, String area,
			String year, String clientPath, Timestamp addtime, String fileName,
			Integer status, Float process, File image) throws IOException, SeeWorldException {
		// ��ȡ�û��ϴ��ļ�·�������һ��"."��λ��
		int posOfDot = clientPath.lastIndexOf(".") + 1;

		// ��ȡ�ļ���׺��·�������һ��"."��������ַ�
		String suffix = clientPath.substring(posOfDot);

		// �Ժ�׺���ȡformat����
		Format format = (Format) formatDao.findByProperty("format", suffix)
				.get(0);
		Integer delete = new Integer(0);

		Integer size = 5;

		Boolean delete_ = false;
		Boolean status_ = false;

		FileInputStream in = new FileInputStream(image);
		byte[] img = new byte[(int) in.available()];
		in.read(img);

		List<AreaAndCountry> areaList = (List<AreaAndCountry>) areaDao
				.findByProperty("name", area);
		AreaAndCountry area1 = areaList.get(0);

		System.out.println("director" + director);
		List<Director> dirList = dirDao.findByProperty("name", director);
		Director dirObject = dirList.get(0);
		Integer dirId = dirObject.getId();
		System.out.println("dirId" + dirId);
		HashSet<Integer> dir = (HashSet<Integer>) new HashSet();
		dir.add(dirId);

		System.out.println("actor" + actor);
		List<Actor> actorList = actorDao.findByProperty("name", actor);
		Actor actorObject = actorList.get(0);
		Integer actorId = actorObject.getId();
		System.out.println("actorId" + actorId);
		HashSet<Integer> act = (HashSet<Integer>) new HashSet();
		act.add(actorId);

		// ���video����
		// Movie movie = new Movie(category,user,format,name, img,
		// clientPath,size,status_,process,delete_ ,addtime,area1,year,dir,act);

		// ������Ƶ���
		// movie.setIntroduction(introduction);

		// ����video����
		// movieDao.save(movie);

		// return movie.getId();
		return 2;
	}

	public Integer saveVideo108(Category category, User user, String name,
			String introduction, String director, String actor, String area,
			String year, String clientPath, Timestamp addtime, String fileName,
			Integer status, Float process, File image, String clickCount)
			throws IOException, SeeWorldException {
		// ��ȡ�û��ϴ��ļ�·�������һ��"."��λ��
		int posOfDot = clientPath.lastIndexOf(".") + 1;

		// ��ȡ�ļ���׺��·�������һ��"."��������ַ�
		String suffix = clientPath.substring(posOfDot);

		// �Ժ�׺���ȡformat����
		Format format = (Format) formatDao.findByProperty("format", suffix)
				.get(0);
		Integer delete = new Integer(0);

		Integer size = 5;

		Boolean delete_ = false;
		Boolean status_ = false;

		FileInputStream in = new FileInputStream(image);
		byte[] img = new byte[(int) in.available()];
		in.read(img);

		List<AreaAndCountry> areaList = (List<AreaAndCountry>) areaDao
				.findByProperty("name", area);
		AreaAndCountry area1 = areaList.get(0);

		System.out.println("director" + director);
		List<Director> dirList = dirDao.findByProperty("name", director);
		Director dirObject = dirList.get(0);
		Integer dirId = dirObject.getId();
		System.out.println("dirId" + dirId);
		HashSet<Integer> dir = (HashSet<Integer>) new HashSet();
		dir.add(dirId);

		System.out.println("actor" + actor);
		List<Actor> actorList = actorDao.findByProperty("name", actor);
		Actor actorObject = actorList.get(0);
		Integer actorId = actorObject.getId();
		System.out.println("actorId" + actorId);
		HashSet<Integer> act = (HashSet<Integer>) new HashSet();
		act.add(actorId);

		Integer count = Integer.valueOf(clickCount);

		// ���video����
		// Movie movie = new Movie(category,user,format,name, img,
		// clientPath,size,status_,process,delete_
		// ,addtime,count,area1,year,dir,act);

		// ������Ƶ���
		// movie.setIntroduction(introduction);

		// ����video����
		// movieDao.save(movie);

		// return movie.getId();
		return 2;
	}

	public Boolean uploadMovie(String clientPath, String name,
			String categoryPath, Integer movieId) throws SeeWorldException {
		// ��Ƶ�ļ��ϴ��Ƿ��Ѿ����
		Boolean isCompleted = true;

		// ��ȡ�û��ϴ��ļ�·�������һ��"."��λ��
		int posOfDot = clientPath.lastIndexOf(".") + 1;

		// ��ȡ�ļ���׺��·�������һ��"."��������ַ�
		String suffix = clientPath.substring(posOfDot);

		int posOfSlash = clientPath.lastIndexOf("/") + 1;

		// ��ȡ�ļ���·�������һ��"/"��������ַ�
		String fileName = clientPath.substring(posOfSlash);

		// ���û��ϴ��ļ���������������浽�ĸ�·��ΪserverRootPath
		String serverRootPath = "/IPTV/" + categoryPath + name + "_" + movieId
				+ "/";

		// ���û��ϴ��ļ���������������浽��·��ΪserverPath
		String serverPath = serverRootPath + fileName;

		System.out.println(clientPath + "---------" + serverPath);

		try {
			// �����������������
			continueFtp.connect("219.243.47.149", 21, "Administrator",
					"tjuximo");

			// ʵ���ļ����ϴ�
			continueFtp.upload(clientPath, serverPath);// ���ļ����û�����д�ĸ�ʽ���������
		} catch (IOException e) {
			isCompleted = false;
			Float process = continueFtp.getUploadProcess();
		}

		Movie movie = movieDao.findById(movieId);
		movie.setPath(serverPath);
		movieDao.merge(movie);

		return isCompleted;
	}

	public IMovieDAO getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(IMovieDAO movieDao) {
		this.movieDao = movieDao;
	}

	public IFormatDAO getFormatDao() {
		return formatDao;
	}

	public void setFormatDao(IFormatDAO formatDao) {
		this.formatDao = formatDao;
	}

	public ContinueFTP getContinueFtp() {
		return continueFtp;
	}

	public void setContinueFtp(ContinueFTP continueFtp) {
		this.continueFtp = continueFtp;
	}

	public IAreaAndCountryDAO getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(IAreaAndCountryDAO areaDao) {
		this.areaDao = areaDao;
	}

	public IDirectorDAO getDirDao() {
		return dirDao;
	}

	public void setDirDao(IDirectorDAO dirDao) {
		this.dirDao = dirDao;
	}

	public IActorDAO getActorDao() {
		return actorDao;
	}

	public void setActorDao(IActorDAO actorDao) {
		this.actorDao = actorDao;
	}
}
