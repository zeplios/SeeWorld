package edu.tju.ina.seeworld.struts.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import test.ContinueFTP;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.CategoryDAO;
import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.dao.user.UserDAO;
import edu.tju.ina.seeworld.logic.UploadVideoLogic;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.user.User;

/**
 * �˷������û��ϴ���Ƶҳ���л�ȡ���������Ƶ��ơ���Ƶ��顢�ݽ��ߺ���Ƶ�ļ��ڿͻ��˵�·����
 * Ȼ����Ӧ����ݸ�UploadVideoLogic.java���saveVideo������������֮��
 * 
 * @author IBM
 */
public class UploadVideoAction implements Action {
	/** �������ȡ��Ƶ��Ƶı?���� */
	private String name;

	/** �������ȡ��Ƶ���ı?���� */
	private String introduction;

	/** �������ȡ��Ƶ�ݽ��ߵı?���� */
	private String speaker;

	/** �������ȡ��Ƶ�ͻ���·���ı?���� */
	private String path;

	/** �������ȡ��Ƶһ������ı?���� */
	private String firstCategory;

	/** �������ȡ��Ƶ��������ı?���� */
	private String secondCategory;

	/** �������ȡ�ϴ�ͼƬ�ı?���� */
	private File image;
	private String imageContentType;
	private String imageFileName;

	/** ��Springע���UploadVideoLogic.java��Ķ��� */
	private UploadVideoLogic uploadVideoLogic;

	/** ��Springע���UserDAO.java��Ķ��� */
	private IUserDAO userDao;

	/** ��Springע���CategoryDAO.java��Ķ��� */
	private ICategoryDAO categoryDao;

	/**
	 * �˷������в���׼��������UploadVideoLogic.java���saveVideo����
	 */
	public String execute() throws Exception {
		// ��ȡrequest��session��session�б���ĵ�ǰ�û���ID
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		// String userId = session.getAttribute("user").toString();

		ContinueFTP continueFtp = new ContinueFTP();

		// ϵͳ����
		String userId = "1";

		// ��userId��ȡuser����
		User user = userDao.findById(Integer.valueOf(userId));

		// ��ȡ ��ǰ�ϴ����
		Float process = 0f;

		// ��ȡ��ǰ�ϴ�״̬
		Integer status = 0;

		// ��ȡ��ǰϵͳʱ��
		Timestamp addtime = new java.sql.Timestamp(System.currentTimeMillis());

		System.out.println(path);
		// ��path�е�"\\"���ַ�������i��
		String str = "\\";
		Matcher m = Pattern.compile(Pattern.quote(str)).matcher(path);
		int i = 0;
		while (m.find()) {
			i++;
		}

		// ת��path��ʽ,����clientPath��
		int k = 0;
		int l = 0;
		String clientPath = "";
		for (int j = 0; j < i; j++) {
			l = path.indexOf("\\", k);
			clientPath = clientPath + path.substring(k, l) + "/";
			k = l + 1;
		}
		clientPath = clientPath + path.substring(l + 1);

		// ��ȡ�ļ���
		String fileName = path.substring(l + 1);

		String categoryPath = "video/" + firstCategory + "/" + secondCategory
				+ "/";

		List<Category> category_ = (List<Category>) categoryDao.findByProperty(
				"name", secondCategory);

		Category category = category_.get(0);

		// ����uploadVideoLogic��saveVideo��������Video
		// Integer videoId = uploadVideoLogic.saveVideo(category,user, name,
		// introduction, speaker, clientPath, addtime,
		// fileName,status,process,image);
		Integer videoId = 1;

		// ����ϴ��Ƿ���� ������status��processֵ
		if (uploadVideoLogic.uploadVideo(clientPath, name, categoryPath,
				videoId)) {
			status = 1;
			process = 100f;
		} else {
			status = 0;
			process = continueFtp.getUploadProcess();
		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	// ���¾�Ϊ����˽�б�����getter��setter����
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

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public UploadVideoLogic getUploadVideoLogic() {
		return uploadVideoLogic;
	}

	public void setUploadVideoLogic(UploadVideoLogic uploadVideoLogic) {
		this.uploadVideoLogic = uploadVideoLogic;
	}

	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public String getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(String firstCategory) {
		this.firstCategory = firstCategory;
	}

	public String getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}

	public ICategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

}
