package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.PrivacySettingVO;

public interface IPrivacySettingLogic {
	public void setPrivacy(PrivacySettingVO privacySetting)
			throws SeeWorldException;

	public JSONArray getPrivacyList();

	public void showUserSetting(PrivacySettingVO privacySetting)
			throws SeeWorldException;
	
	public void initUserSetting(User user) throws SeeWorldException;
}
