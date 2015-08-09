package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Lecturer;

public interface ILecturerDAO extends IBaseHibernateDAO<Lecturer> {
	public static final String NAME = "name";

	public List<Lecturer> findByName(Object name)  throws SeeWorldException;
	public List<Lecturer> findByPage(int offset, int length)
	throws SeeWorldException;
}
