package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.ICollectionLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.EssentialUser;
import edu.tju.ina.seeworld.vo.Pagination;

public class CollectionAjaxAction extends AjaxAction {
	private static final long serialVersionUID = 7973524796770147291L;
	
	private ICollectionLogic collectionLogic;
	private SettingLogic settingLogic;
	
	private Integer targetId;
	private Integer collectionId;
	private String resourceId;
	private String targetUserId;
	private String alreadyCollected = "false";
	private boolean managable = false;

	public String addCollection() {
		try {
			EssentialUser eUser = getCurrentUser();
			User user = new User(eUser);
			collectionLogic.addCollection(targetId, resourceId, user);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}

	public String findCollectionsOfResourceByPage() {
		page = getPage();
		try {
			if (len == null) {
				len = collectionLogic.getCollectionSum(targetUserId, resourceId);
			}
			page.setLen(len);
			page.setPagelist();
			managable = targetUserId.equals(getCurrentUser().getId());
			
			resultList = new JSONArray();
			resultList.addAll(collectionLogic.findCollectionsOfResourceByPage(
					targetUserId, page.getStart(), page.getPagesize(), resourceId));
			
			pageJSON = JSONObject.fromObject(page);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	@JSON(serialize = false)
	public Pagination getPage() {
		this.page = new Pagination();
		page.setCurrentpage(getCurrentPage());
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.ITEMS_PER_PAGE));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	public String checkAlreadyCollected() {
		try {
			boolean isCollected = collectionLogic.isAlreadyCollected(targetId,
					resourceId, targetUserId);
			alreadyCollected = Boolean.toString(isCollected);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String deleteCollection() throws SeeWorldException {
		Collection c = collectionLogic.findById(collectionId);
		if (c != null) {
			if (c.getUser().getId().equals(getCurrentUser().getId())) {
				collectionLogic.deleteCollection(collectionId);
			} else {
				throw new SeeWorldException("只有收藏的拥有者有权限删除收藏");
			}
		}
		return Action.SUCCESS;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public boolean isManagable() {
		return managable;
	}

	public void setManagable(boolean managable) {
		this.managable = managable;
	}

	public String getAlreadyCollected() {
		return alreadyCollected;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public void setCollectionLogic(ICollectionLogic collectionLogic) {
		this.collectionLogic = collectionLogic;
	}

}
