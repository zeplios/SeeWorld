package edu.tju.ina.seeworld.dao.common;

import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;

public class RbacDAO<T> extends BaseHibernateDAO<T> implements IRbacDAO<T> {

	public T findByName(Object name) throws SeeWorldException {
		// TODO Auto-generated method stub
		T res = null;
		try {
			List<T> list = findByProperty(NAME, name);
			if (list != null && list.size() > 0) {
				res = list.get(0);
			}
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
		return res;
	}

}
