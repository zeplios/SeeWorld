package edu.tju.ina.seeworld.dao.user;

// default package

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Specialty;

/**
 * @see edu.tju.ina.seeworld.struts.vo.User_VO.Specilty
 * @author Uranus
 */

public class SpecialtyDAO extends BaseHibernateDAO<Specialty> implements
		ISpecialtyDAO {
	protected void initDao() {
		super.init(Specialty.class.getName());
	}
	
	public List<Specialty> findByAcademy(Object academy) throws SeeWorldException{
		return findByProperty(ACADEMY, academy);
	}

	public List<Specialty> findByName(String name) throws SeeWorldException{
		return findByProperty(NAME, name);
	}
}