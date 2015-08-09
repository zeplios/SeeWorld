package edu.tju.ina.seeworld.struts.action.common;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.util.Constant;

/**
 * 用于设置各项配置的Ajax类
 * @author zhfch
 *
 */
public class SettingAjaxAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5672665102155492990L;
	private SettingLogic settingLogic;
	private String value;

	public String modifyCommentsPerPage(){
		try {
			settingLogic.setConfigValue(Constant.COMMENTS_RECOMMENDATION_PER_PAGE, value);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
