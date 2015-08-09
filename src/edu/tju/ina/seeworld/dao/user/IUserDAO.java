package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.User;

public interface IUserDAO extends IBaseHibernateDAO<User> {

	// property constants
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	public static final String REAL_NAME = "realName";
	public static final String ACADEMY = "academy";
	public static final String SPECILTY = "specialty";
	public static final String PHOTOPATH = "photoPath";
	public static final String ROLE = "role";
	public static final String EMAIL = "email";
	public static final String UID = "uid";

	public List<User> findByUserName(Object userName) throws SeeWorldException;

	public List<User> findByRealName(Object realName) throws SeeWorldException;

	public List<User> findByRole(Object role) throws SeeWorldException;

	public List<User> findByAcademy(Object academy) throws SeeWorldException;

	public List<User> findBySpecialty(Object specialty) throws SeeWorldException;

	public List<User> findByUID(String uid) throws SeeWorldException;

	public List<User> findByEmail(String email) throws SeeWorldException;
	
	public String getNextAvailableId() throws SeeWorldException;
}
