package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.OrganizationLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;

import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Organization;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class OrganizationAction extends BaseAction implements
		ModelDriven<Organization>, ActionBasicOperation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5718546480490144853L;
	private OrganizationLogic organizationLogic;
	private Organization organization;
	private List<Organization> organizationList;
	private Integer targetId;
	private AreaAndCountryLogic areaAndCountryLogic;
	private List<AreaAndCountry> areaAndCountryList;
	private SettingLogic settingLogic;
	
	public String preAddAction() throws SeeWorldException {
		areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
		return ADD;
	}
	public String deleteAction() throws SeeWorldException {
		organizationLogic.delete(targetId);
		return LIST_REDIRECT;
	}

	public String listAction() throws SeeWorldException {
		page = getPage();
		int pageSize = page.getPagesize();
		int offset = page.getStart();
		page.setLen(organizationLogic.getCount());
		page.setPagelist();
		organizationList=organizationLogic.getOrganizationByPage(offset, pageSize);
		return LIST;
	}

	public String saveAction() throws SeeWorldException {
		organizationLogic.save(organization);
		return LIST_REDIRECT;
	}

	public String preUpdateAction() throws SeeWorldException {
		organization=organizationLogic.getOrganizationById(targetId);
		areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();	
		return UPDATE;
	}
	
	public String updateAction() throws SeeWorldException {
		organizationLogic.update(organization);
		return LIST_REDIRECT;
	}

	public Organization getModel() {
		return organization;
	}

	public OrganizationLogic getOrganizationLogic() {
		return organizationLogic;
	}

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationLogic(OrganizationLogic organizationLogic) {
		this.organizationLogic = organizationLogic;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	public Integer getTargetId() {
		return targetId;
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
	
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	
	public Organization getOrganization() {
		return organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
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
