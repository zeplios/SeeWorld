package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.CategoryLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class CategoryAction extends BaseAction implements ModelDriven<Category>, ActionBasicOperation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2959995624582182906L;
	private CategoryLogic categoryLogic;
	private Category category;
	private SettingLogic settingLogic;

	private List<Category> categoryList;

	private Integer targetId; 
	
	public String listAction() throws SeeWorldException {
		// TODO Auto-generated method stub
		page = getPage();
		int pageSize = page.getPagesize();
		int offset = page.getStart();
		page.setLen(categoryLogic.getCount());
		page.setPagelist();
		setCategoryList(categoryLogic.getCategoryByPage(offset, pageSize));
		return LIST;
	}
	
	public Pagination getPage() {
		page = super.getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.ITEMS_PER_PAGE));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	public SettingLogic getSettingLogic() {
		return settingLogic;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
	public String preAddAction() throws SeeWorldException {	
		return ADD;
	}
	
	public CategoryLogic getCategoryLogic() {
		return categoryLogic;
	}

	public void setCategoryLogic(CategoryLogic categoryLogic) {
		this.categoryLogic = categoryLogic;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String saveAction() throws SeeWorldException {
		// TODO Auto-generated method stub
		categoryLogic.save(category);
		return LIST_REDIRECT;
	}

	public String deleteAction() throws SeeWorldException {
		// TODO Auto-generated method stub
		category = categoryLogic.getById(targetId);
		categoryLogic.delete(category);
		return LIST_REDIRECT;
	}
	
	public String preUpdateAction() throws SeeWorldException {
		category = categoryLogic.getById(targetId);
		return UPDATE;
	}

	public String updateAction() throws SeeWorldException {
		// TODO Auto-generated method stub
		categoryLogic.update(category);
		return LIST_REDIRECT;
	}

	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

}
