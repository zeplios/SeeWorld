package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Academy;

public interface IAcademyDAO extends IBaseHibernateDAO<Academy> {
	public static final String NAME = "name";

	public List<Academy> findByName(String name) throws SeeWorldException;
}
