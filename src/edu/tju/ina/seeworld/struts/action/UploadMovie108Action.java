package edu.tju.ina.seeworld.struts.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import test.ContinueFTP;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.logic.UploadMovieLogic;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.user.User;

public class UploadMovie108Action implements Action 
{
	/** �������ȡ��Ӱ��Ƶı?���� */
	private String name;
	
	/** �������ȡ��Ӱ���ı?����*/
	private String introduction;
	
	/** �������ȡ��Ӱ�ݽ��ߵı?����*/
	private String director;
	
	/** �������ȡ��Ӱ�ͻ���·���ı?����*/
	private String actor;
	
	/** �������ȡ��Ӱ�ͻ���·���ı?����*/
	private String path;
	
	/** �������ȡ��Ӱ����ı?����*/
	private String category;
	
	private String area;
	
	private String year;
	
	private String clickCount;
	
	/** �������ȡ�ϴ�ͼƬ�ı?����*/
	private File image;

	/** ��Springע���UploadVideoLogic.java��Ķ��� */
	private UploadMovieLogic uploadMovieLogic;
	
	/** ��Springע���UserDAO.java��Ķ��� */
	private IUserDAO userDao;
	
	/** ��Springע���CategoryDAO.java��Ķ��� */
	private ICategoryDAO categoryDao;
	
	public String execute() throws Exception 
	{
		//��ȡrequest��session��session�б���ĵ�ǰ�û���ID
		HttpServletRequest request = ServletActionContext.getRequest();
		
		ContinueFTP continueFtp=new ContinueFTP();

		//ϵͳ����
		String userId = "1";

		//��userId��ȡuser����
		User user = userDao.findById(Integer.valueOf(userId));
		
		//��ȡ ��ǰ�ϴ����
		Float process = 0f;
		
		//��ȡ��ǰ�ϴ�״̬
		Integer status = 0;
		
		//��ȡ��ǰϵͳʱ��
		Timestamp addtime = new java.sql.Timestamp(System.currentTimeMillis());
		
		//��path�е�"\\"���ַ�������i��
		String str = "\\";
		Matcher m = Pattern.compile(Pattern.quote(str)).matcher(path);
		int i = 0;
        while (m.find()) {
            i++;
        }
        
        //ת��path��ʽ,����clientPath��
        int k = 0;
        int l = 0;
		String clientPath = "";
        for(int j=0;j<i;j++)
        {       	
        	l = path .indexOf("\\", k);       	
        	clientPath = clientPath + path.substring(k, l) + "/";
        	k = l+1;       	
        }
        clientPath = clientPath + path.substring(l+1);
       
        //��ȡ�ļ���
        String fileName = path.substring(l+1);
        
        List<Category> category_ = (List<Category>)categoryDao.findByProperty("name", category);
        
        Category categoryObject = category_.get(0);
        
        String categoryPath = "movie/" + category + "/"; 
        
        Integer movieId = uploadMovieLogic.saveVideo108(categoryObject, user, name, introduction, director,  actor, area,year, clientPath, addtime, fileName, status, process, image,clickCount);
               
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public UploadMovieLogic getUploadMovieLogic() {
		return uploadMovieLogic;
	}

	public void setUploadMovieLogic(UploadMovieLogic uploadMovieLogic) {
		this.uploadMovieLogic = uploadMovieLogic;
	}

	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public ICategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getClickCount() {
		return clickCount;
	}

	public void setClickCount(String clickCount) {
		this.clickCount = clickCount;
	}
	
}
