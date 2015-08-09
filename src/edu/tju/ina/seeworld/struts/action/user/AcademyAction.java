package edu.tju.ina.seeworld.struts.action.user;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IAcademyLogic;
import edu.tju.ina.seeworld.po.user.Academy;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;

public class AcademyAction extends BaseAction implements
		ModelDriven<Academy>,ActionBasicOperation{
	private static final long serialVersionUID = 5157778262337064667L;
	Academy model = new Academy();
	IAcademyLogic academyLogic;
	List<Academy> academyList;

	public Academy getModel() {
		return model;
	}

	public IAcademyLogic getAcademyLogic() {
		return academyLogic;
	}

	public void setModel(Academy academy) {
		this.model = academy;
	}

	public void setAcademyLogic(IAcademyLogic academyLogic) {
		this.academyLogic = academyLogic;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public String listAction() throws SeeWorldException {
		academyList = academyLogic.showAcademyList();
		return LISTMULTIMEDIA;
	}
	
	public String saveAction() throws SeeWorldException{
		academyLogic.addAcademy(model);
		return SUCCESS;
	}
	
	public String deleteAction() throws SeeWorldException{
		academyLogic.addAcademy(model);
		return SUCCESS;
	}
	
	public String updateAction() throws SeeWorldException{
		academyLogic.updateAcademy(model);
		return SUCCESS;
	}
}
