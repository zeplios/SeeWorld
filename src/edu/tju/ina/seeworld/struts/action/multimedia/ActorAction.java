package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.ActorLogic;
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;

import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.struts.action.common.ActionBasicOperation;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class ActorAction extends BaseAction implements
		ModelDriven<Actor>, ActionBasicOperation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5718546480490144853L;
	private ActorLogic actorLogic;
	private AreaAndCountryLogic areaAndCountryLogic;
	private SettingLogic settingLogic;
	private Actor actor;
	
	private List<AreaAndCountry> areaAndCountryList;
	private List<Actor> actorList;
	
	private Integer targetId;
	
	public String preAddAction() throws SeeWorldException {
		areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();	
		return ADD;
	}
	
	public String deleteAction() throws SeeWorldException {
		actorLogic.delete(targetId);
		return LIST_REDIRECT;
	}

	public String listAction() throws SeeWorldException {
		page = getPage();
		int pageSize = page.getPagesize();
		int offset = page.getStart();
		page.setLen(actorLogic.getCount());
		page.setPagelist();
		actorList=actorLogic.findByPage(offset, pageSize);
		return LIST;
	}

	public String saveAction() throws SeeWorldException {
		actorLogic.save(actor);
		return LIST_REDIRECT;
	}

	public String preUpdateAction() throws SeeWorldException {
		actor=actorLogic.findById(targetId);
		areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();	
		return UPDATE;
	}
	
	public String updateAction() throws SeeWorldException {
		actorLogic.update(actor);
		return LIST_REDIRECT;
	}

	public Actor getModel() {
		return actor;
	}

	public ActorLogic getActorLogic() {
		return actorLogic;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorLogic(ActorLogic actorLogic) {
		this.actorLogic = actorLogic;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public Actor getActor() {
		return actor;
	}

	public List<AreaAndCountry> getAreaAndCountryList() {
		return areaAndCountryList;
	}

	public void setAreaAndCountryLogic(AreaAndCountryLogic areaAndCountryLogic) {
		this.areaAndCountryLogic = areaAndCountryLogic;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
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
