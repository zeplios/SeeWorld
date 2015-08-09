package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Category;

public interface ICategoryDAO extends IBaseHibernateDAO<Category> {
	public static final String NAME = "name";
	public static final String COUNT = "count";
	public static final String PARENT = "parent";

	public List<Category> findByName(Object name) throws SeeWorldException;

	public List<Category> findByCount(Object count) throws SeeWorldException;

	public List<Category> findByParent(Object parent) throws SeeWorldException;

	public List<Category> findCertainResourceCategoryList(
			Resource resource) throws SeeWorldException;
	
	public List<Category> findByPage(int offset, int length) throws SeeWorldException;
}
