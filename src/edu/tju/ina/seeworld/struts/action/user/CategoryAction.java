package edu.tju.ina.seeworld.struts.action.user;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.CategoryLogic;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;

public class CategoryAction extends BaseAction implements
		ModelDriven<Category>, ActionBasicOperation {
	private static final long serialVersionUID = 7712561972757206440L;
	private CategoryLogic categoryLogic;
	private Category category;
	private List<Category> categoryList;

	public void setCategoryLogic(CategoryLogic categoryLogic) {
		this.categoryLogic = categoryLogic;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Category getModel() {
		return category;
	}

	public String deleteAction() throws SeeWorldException {
		categoryLogic.delete(category);
		return SUCCESS;
	}

	public String listAction() throws SeeWorldException {
		categoryList = categoryLogic.findAll();
		return SUCCESS;
	}

	public String saveAction() throws SeeWorldException {
		categoryLogic.save(category);
		return SUCCESS;
	}

	public String updateAction() throws SeeWorldException {
		categoryLogic.update(category);
		return SUCCESS;
	}
}
