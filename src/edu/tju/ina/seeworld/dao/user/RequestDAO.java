package edu.tju.ina.seeworld.dao.user;

// default package

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Request;

/**
 * @see edu.tju.ina.seeworld.po.user.Request
 * @author Uranus
 */

public class RequestDAO extends BaseHibernateDAO<Request> implements
		IRequestDAO {

	protected void initDao() {
		super.init(Request.class.getName());
	}

	@SuppressWarnings("unchecked")
	public List<Request> findByTwoProperty(String propertyName1, Object value1,
			String propertyName2, Object value2) throws SeeWorldException{
		try {
			String queryString = "from Request as model where model."
					+ propertyName1 + "= ? and model." + propertyName2 + "=?";
			Object[] values = { value1, value2 };
			return getHibernateTemplate().find(queryString, values);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}
}