package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Lecturer;

/**
 * A data access object (DAO) providing persistence and search support for
 * Lecturer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see tju.ina.uranus.po.Lecturer
 * @author Uranus
 */

public class LecturerDAO extends BaseHibernateDAO<Lecturer> implements
		ILecturerDAO {
	public void initDao() {
		super.init(Lecturer.class.getName());
	}

	public List<Lecturer> findByName(Object name) throws SeeWorldException{
		return findByProperty(NAME, name);
	}
	
	public List<Lecturer> findByPage(int offset, int length) throws SeeWorldException{
		String hql="select d from Lecturer d ";
		return getListByPage(hql, offset, length);
	}
	
}