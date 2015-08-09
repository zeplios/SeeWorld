package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.LecturerLogic;
import edu.tju.ina.seeworld.logic.OrganizationLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;

import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Lecturer;
import edu.tju.ina.seeworld.po.resource.Organization;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class LecturerAction extends BaseAction implements
		ModelDriven<Lecturer>, ActionBasicOperation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5718546480490144853L;
	private LecturerLogic lecturerLogic;
	private OrganizationLogic organizationLogic;
	private Lecturer lecturer = new Lecturer();
	private List<AreaAndCountry> areaAndCountryList;
	private SettingLogic settingLogic;

	private List<Lecturer> lecturerList;
	private List<Organization> organizationList;
	private Integer targetId;
	
	public String preAddAction() throws SeeWorldException {
		organizationList = organizationLogic.getAllOrganizationList();
		return ADD;
	}

	public String deleteAction() throws SeeWorldException {
		lecturerLogic.delete(targetId);
		return LIST_REDIRECT;
	}

	public String listAction() throws SeeWorldException {
		page = getPage();
		int pageSize = page.getPagesize();
		int offset = page.getStart();
		page.setLen(lecturerLogic.getCount());
		page.setPagelist();
		lecturerList=lecturerLogic.getLecturerByPage(offset, pageSize);
		return LIST;
	}

	public String saveAction() throws SeeWorldException {
		lecturerLogic.save(lecturer);
		return LIST_REDIRECT;
	}

	public String preUpdateAction() throws SeeWorldException {
		lecturer = lecturerLogic.findById(targetId);
		organizationList = organizationLogic.getAllOrganizationList();	
		return UPDATE;
	}
	
	public String updateAction() throws SeeWorldException {
		lecturerLogic.update(lecturer);
		return LIST_REDIRECT;
	}

	public Lecturer getModel() {
		return lecturer;
	}

	public LecturerLogic getLecturerLogic() {
		return lecturerLogic;
	}

	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}

	public void setLecturerLogic(LecturerLogic lecturerLogic) {
		this.lecturerLogic = lecturerLogic;
	}

	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public void setOrganizationLogic(OrganizationLogic organizationLogic) {
		this.organizationLogic = organizationLogic;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public List<AreaAndCountry> getAreaAndCountryList() {
		return areaAndCountryList;
	}
	
	public void setAreaAndCountryList(List<AreaAndCountry> areaAndCountryList) {
		this.areaAndCountryList = areaAndCountryList;
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
