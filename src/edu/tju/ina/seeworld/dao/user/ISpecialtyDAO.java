package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Specialty;

public interface ISpecialtyDAO extends IBaseHibernateDAO<Specialty> {

	public static final String NAME = "name";
	public static final String ACADEMY = "academy";

	public List<Specialty> findByName(String name) throws SeeWorldException;

	public List<Specialty> findByAcademy(Object academy) throws SeeWorldException;
}
