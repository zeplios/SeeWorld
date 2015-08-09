package edu.tju.ina.seeworld.logic;

import java.util.List;

import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.util.IDAssistant;

public class CategoryLogic {
	private ICategoryDAO categoryDao;
	private IDAssistant iDAssistant;

	public List<Category> findAll() throws SeeWorldException{
		return categoryDao.findAll(" order by "+ICategoryDAO.NAME,true);
	}
	
	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}

	public Category getById(int id) throws SeeWorldException {
		return categoryDao.findById(id);
	}

	public List<Category> getCategoryListByParentID(int categoryID)
			throws SeeWorldException {
		Category c = new Category();
		c.setId(categoryID);
		List<Category> categoryList = categoryDao.findByParent(c);
		return categoryList;
	}

	public List<Category> getAllCategoryListForCertainResource(
			String resourceName) throws SeeWorldException {
		String resourceId = iDAssistant.getResourceID(resourceName);
		Resource res = new Resource();
		res.setId(resourceId);
		res.setName(resourceName);
		return categoryDao.findCertainResourceCategoryList(res);
	}

	public void save(Category category) throws SeeWorldException {
		categoryDao.save(category);
	}

	public void delete(Category category) throws SeeWorldException {
		categoryDao.delete(category);
	}

	public void update(Category category) throws SeeWorldException {
		categoryDao.update(category);
	}
	
	public List<Category> getCategoryByPage(int offset, int length) throws SeeWorldException{
		return categoryDao.findByPage(offset, length);
	}
	
	public Integer getCount() throws SeeWorldException{
		return categoryDao.getCount(null);
	}
}
