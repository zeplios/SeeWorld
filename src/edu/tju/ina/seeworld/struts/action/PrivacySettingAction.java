package edu.tju.ina.seeworld.struts.action;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.logic.IPrivacySettingLogic;
import edu.tju.ina.seeworld.vo.PrivacySettingVO;


public class PrivacySettingAction extends ActionSupport implements ModelDriven<PrivacySettingVO>{
	private PrivacySettingVO model=new PrivacySettingVO();
	private IPrivacySettingLogic privacySettingLogic;
	private String message;
	private JSONArray privacyList;
	public String getMessage() {
		return message;
	}
	
	public JSONArray getPrivacyList() {
		return privacyList;
	}

	public void setPrivacySettingLogic(IPrivacySettingLogic privacySettingLogic) {
		this.privacySettingLogic = privacySettingLogic;
	}

	public PrivacySettingVO getModel() {
		return this.model;
	}
	
	public String setPrivacy(){
//		message=privacySettingLogic.setPrivacy(model);
		return Action.SUCCESS;
	}
	public String showUserSetting(){
//		privacySettingLogic.showUserSetting(model);
		privacyList=privacySettingLogic.getPrivacyList();
		return Action.SUCCESS;
	}
}
