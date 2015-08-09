package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Format;

/**
 * @see edu.tju.ina.seeworld.po.resource.Format
 * @author Uranus
 */

public class FormatDAO extends BaseHibernateDAO<Format> implements IFormatDAO {

	protected void initDao() {
		super.init(Format.class.getName());
	}

	public List<Format> findByName(Object formate) throws SeeWorldException {
		return findByProperty(FORMATE, formate);
	}

}