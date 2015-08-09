package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Format;

public interface IFormatDAO extends IBaseHibernateDAO<Format>{
	public static final String FORMATE = "formate";

	public List<Format> findByName(Object formate)  throws SeeWorldException;

}
