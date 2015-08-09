package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.KeyWord;
import edu.tju.ina.seeworld.po.resource.KeyWordRelationship;

public interface IKeyWordRelationshipDAO extends
		IBaseHibernateDAO<KeyWordRelationship> {
	public static final String WEIGHT = "weight";

	public List<KeyWordRelationship> findByWeight(Object weight)  throws SeeWorldException;

	public List<KeyWordRelationship> findByList(final List<KeyWord> list)  throws SeeWorldException;
	
	public List<KeyWordRelationship> findByListWithinPage(final List<KeyWord> list,
			final Integer start, final Integer MaxResult)  throws SeeWorldException;
}
