package edu.tju.ina.seeworld.dao.user;

// default package

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.User;

/**
 * @see edu.tju.ina.seeworld.struts.vo.User_VO.User
 * @author MyEclipse Persistence Tools
 */

public class UserDAO extends BaseHibernateDAO<User> implements IUserDAO {

	private static final String SPECIALTY = null;

	public void initDao(){
		super.init(User.class.getName());
	}
	
	public List<User> findByUserName(Object userName) throws SeeWorldException{
		return findByProperty(USER_NAME, userName);
	}

	public List<User> findByRealName(Object realName) throws SeeWorldException{
		return findByProperty(REAL_NAME, realName);
	}

	public List<User> findByRole(Object role) throws SeeWorldException{
		return findByProperty(ROLE, role);
	}

	public List<User> findByAcademy(Object academy) throws SeeWorldException{
		return findByProperty(ACADEMY, academy);
	}

	public List<User> findBySpecialty(Object specialty) throws SeeWorldException{
		return findByProperty(SPECIALTY, specialty);
	}

	public List<User> findByUID(String uid) throws SeeWorldException{
		return findByProperty(UID, uid);
	}

	public List<User> findByEmail(String email) throws SeeWorldException{
		return findByProperty(EMAIL, email);
	}
	@SuppressWarnings("unchecked")
	public List<Comment> findByPropertyLimited(final String propertyName, final Object value,final int start,final int maxsize) throws SeeWorldException{
		try {
			return (List<Comment>) getHibernateTemplate().execute(
					new HibernateCallback(){
						public Object doInHibernate(Session session)
									throws HibernateException {
					Query query1=session.createQuery("from Comment as model where model."
							+ propertyName + "= ?");
					query1.setParameter(0, value);
					query1.setFirstResult(start);
					query1.setMaxResults(maxsize);
					List<Comment> result = query1.list();
					return result;
						}
					});
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public String getNextAvailableId() throws SeeWorldException {
		// TODO Auto-generated method stub
		try {
			return (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						@SuppressWarnings("deprecation")
						Connection conn = session.connection();
						String sql = "SELECT (CASE WHEN EXISTS(SELECT * FROM user WHERE id=10000)" +  
											"THEN MIN(id)+1 ELSE 10000 END) FROM user WHERE id NOT IN" +  
											"(SELECT id-1 FROM user) AND id>=10000";
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next())
							return rs.getString(1);
						else
							return null;
					}
				});
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}
}