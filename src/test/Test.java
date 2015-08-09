package test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.opensymphony.xwork2.ActionSupport;

import edu.tju.ina.seeworld.dao.user.UserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.User;

public class Test extends ActionSupport
{
	/**
	 * @param args
	 */
	private static UserDAO userDao;
	

	public static void main(String args[]) throws NumberFormatException, SeeWorldException
	{
		Resource rs = new ClassPathResource("dao.xml");
		
		//BeanFactory bf = new XmlBeanFactory(rs);
		userDao = new UserDAO();
		User user = userDao.findById(1);
		System.out.println(user.getUsername());
		
		//return "success";
	}

	
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}	
}
