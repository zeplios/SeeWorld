package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Academy;

/**
 * @author Uranus
 * 
 */
public class AcademyDAO extends BaseHibernateDAO<Academy> implements
		IAcademyDAO {

	protected void initDao() {
		super.init(Academy.class.getName());
	}

	public List<Academy> findByName(String name) throws SeeWorldException {
		return findByProperty(NAME, name);
	}
}