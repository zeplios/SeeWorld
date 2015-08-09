package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.KeyWord;

public interface IKeyWordDAO extends IBaseHibernateDAO<KeyWord>{
	public static final String KEYWORD = "keyword";

	public List<KeyWord> findByKeyword(Object keyword)  throws SeeWorldException;
	
	public List<KeyWord> findBySQL(final String queryString)  throws SeeWorldException;
}
