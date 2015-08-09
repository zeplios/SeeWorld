package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;

import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class AreaAndCountryAction extends BaseAction implements
		ModelDriven<AreaAndCountry>, ActionBasicOperation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5718546480490144853L;
	private AreaAndCountryLogic areaAndCountryLogic;
	private AreaAndCountry areaAndCountry;
	private List<AreaAndCountry> areaAndCountryList;
	private Integer targetId;
	private SettingLogic settingLogic;
	
	public String preAddAction() throws SeeWorldException {	
		return ADD;
	}

	public String deleteAction() throws SeeWorldException {
		areaAndCountryLogic.delete(targetId);
		return LIST_REDIRECT;
	}
	
	public String listAction() throws SeeWorldException {
		page = getPage();
		int pageSize = page.getPagesize();
		int offset = page.getStart();
		//Integer a=areaAndCountryLogic.getCount();
		page.setLen(areaAndCountryLogic.getCount());
		page.setPagelist();
		areaAndCountryList=areaAndCountryLogic.getAreaAndCountryByPage(offset, pageSize);
		return LIST;
	}

	public String saveAction() throws SeeWorldException {
		areaAndCountryLogic.save(areaAndCountry);
		return LIST_REDIRECT;
	}

	public String preUpdateAction() throws SeeWorldException {
		areaAndCountry=areaAndCountryLogic.findById(targetId);
		//areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();	
		return UPDATE;
	}
	
	public String updateAction() throws SeeWorldException {
		areaAndCountryLogic.update(areaAndCountry);
		return LIST_REDIRECT;
	}

	public AreaAndCountry getModel() {
		return areaAndCountry;
	}

	public AreaAndCountryLogic getAreaAndCountryLogic() {
		return areaAndCountryLogic;
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

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public AreaAndCountry getAreaAndCountry() {
		return areaAndCountry;
	}

	public void setAreaAndCountry(AreaAndCountry areaAndCountry) {
		this.areaAndCountry = areaAndCountry;
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
