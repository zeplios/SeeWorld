package edu.tju.ina.seeworld.dao.resource;

// default package

import java.util.ArrayList;
import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Category;

/**
 * @see edu.tju.ina.seeworld.resource.Category
 * @author Uranus
 */

public class CategoryDAO extends BaseHibernateDAO<Category> implements
		ICategoryDAO {

	protected void initDao() {
		super.init(Category.class.getName());
	}

	public List<Category> findByName(Object name) throws SeeWorldException {
		return findByProperty(NAME, name);
	}

	public List<Category> findByCount(Object count) throws SeeWorldException {
		return findByProperty(COUNT, count);
	}

	public List<Category> findByParent(Object parent) throws SeeWorldException {
		return findByProperty(PARENT, parent);
	}

	public List<Category> findCertainResourceCategoryList(Resource resource)
			throws SeeWorldException {
		String hql = null;
		List<Category> list = new ArrayList<Category>(0);
		hql = "select c FROM Category c join c.multimedias m where m.resource.id=? group by c.name";
		list = findByQuery(hql, resource.getId());
		return list;
	}

	public List<Category> findByPage(int offset, int length)
			throws SeeWorldException {
		// TODO Auto-generated method stub
		String hql = "select c from Category c";
		return getListByPage(hql, offset, length);
	}
}