package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IFriendLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.FriendVO;

public class FriendAjaxAction extends AjaxAction implements
		ModelDriven<FriendVO> {
	private static final long serialVersionUID = 8531532533772346745L;
	private FriendVO model = new FriendVO();
	private IFriendLogic friendLogic;
	private JSONObject friend;
	private SettingLogic settingLogic;
	
	public void setFriendLogic(IFriendLogic friendLogic) {
		this.friendLogic = friendLogic;
	}

	@JSON(serialize = false)
	public FriendVO getModel() {
		return this.model;
	}

	public JSONObject getFriend() {
		return friend;
	}

	public String makeFriend() {
		try {
			friendLogic.makeFriends(model);
			friend = friendLogic.getFriendJson();
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String endFriendship() {
		try {
			friendLogic.endFriendship(model);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String findFriendsByPage() {
		page = getPage();
		page.setCurrentpage(currentPage);
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.FRIENDS_PER_PAGE));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int len = 0;
		try {
			len = friendLogic.getFriendSum(model.getOfferId());
			page.setLen(len);
			page.setPagelist();
			resultList = friendLogic.findFriendsByPage(model, page.getStart(),
					page.getPagesize());
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageJSON = JSONObject.fromObject(page);
		return Action.SUCCESS;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
}
