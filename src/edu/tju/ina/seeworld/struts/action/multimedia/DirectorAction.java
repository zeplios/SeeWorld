package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;


import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.DirectorLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;

import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class DirectorAction extends BaseAction implements
		ModelDriven<Director>, ActionBasicOperation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5718546480490144853L;
	private DirectorLogic directorLogic;
	private AreaAndCountryLogic areaAndCountryLogic;
	private SettingLogic settingLogic;
	
	private Director director = new Director();

	private List<Director> directorList;
	private List<AreaAndCountry> areaAndCountryList;

	private Integer targetId;
	
	public String preAddAction() throws SeeWorldException {
		areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
		return ADD;
	}

	public String deleteAction() throws SeeWorldException {
		directorLogic.delete(targetId);
		return LIST_REDIRECT;
	}

	public String listAction() throws SeeWorldException {
		page = getPage();
		int pageSize = page.getPagesize();
		int offset = page.getStart();
		page.setLen(directorLogic.getCount());
		page.setPagelist();
		directorList=directorLogic.findByPage(offset, pageSize);
		return LIST;
	}

	public String saveAction() throws SeeWorldException {
		directorLogic.save(director);
		return LIST_REDIRECT;
	}
 
	public String preUpdateAction() throws SeeWorldException {
		director=directorLogic.findById(targetId);
		areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();	
		return UPDATE;
	}
	
	public String updateAction() throws SeeWorldException {
		directorLogic.update(director);
		return LIST_REDIRECT;
	}

	public Director getModel() {
		return director;
	}

	public DirectorLogic getDirectorLogic() {
		return directorLogic;
	}

	public List<Director> getDirectorList() {
		return directorList;
	}

	public void setDirectorLogic(DirectorLogic directorLogic) {
		this.directorLogic = directorLogic;
	}

	public void setDirectorList(List<Director> directorList) {
		this.directorList = directorList;
	}

	public List<AreaAndCountry> getAreaAndCountryList() {
		return areaAndCountryList;
	}

	public void setAreaAndCountryLogic(AreaAndCountryLogic areaAndCountryLogic) {
		this.areaAndCountryLogic = areaAndCountryLogic;
	}

	public void setAreaAndCountryList(List<AreaAndCountry> areaAndCountryList) {
		this.areaAndCountryList = areaAndCountryList;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
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

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
}
