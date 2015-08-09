package edu.tju.ina.seeworld.struts.action.user;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IAcademyLogic;
import edu.tju.ina.seeworld.logic.ISpecialtyLogic;
import edu.tju.ina.seeworld.po.user.Academy;
import edu.tju.ina.seeworld.po.user.Specialty;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.vo.SpecialtyVO;

public class SpecialtyAjaxAction extends AjaxAction implements
		ModelDriven<SpecialtyVO> {
	private static final long serialVersionUID = 3367827864651010654L;
	SpecialtyVO model = new SpecialtyVO();
	private List<Academy> academyList;
	private List<Specialty> specialtyList;
	private ISpecialtyLogic specialtyLogic;
	private IAcademyLogic academyLogic;

	public SpecialtyVO getModel() {
		return model;
	}

	public void setSpecialty(SpecialtyVO specialty) {
		this.model = specialty;
	}

	public void setSpecialtyLogic(ISpecialtyLogic specialtyLogic) {
		this.specialtyLogic = specialtyLogic;
	}

	@JSON(serialize = false)
	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	@JSON(serialize = false)
	public List<Specialty> getSpecialtyList() {
		return specialtyList;
	}

	public void setSpecialtyList(List<Specialty> specialtyList) {
		this.specialtyList = specialtyList;
	}

	@JSON(serialize = false)
	public IAcademyLogic getAcademyLogic() {
		return academyLogic;
	}

	public void setAcademyLogic(IAcademyLogic academyLogic) {
		this.academyLogic = academyLogic;
	}

	public String showSpecialtyList() throws SeeWorldException {
		resultList = specialtyLogic.showSpecialtyList(model.getAcademy_id());
		return SUCCESS;
	}

	public String showAandSList() throws SeeWorldException {
		academyList = academyLogic.showAcademyList();
		specialtyList = specialtyLogic.showSpecialtyList();
		return Action.SUCCESS;
	}
	
	@Override
	public JSONObject getPageJSON(){
		return null;
	}
}
