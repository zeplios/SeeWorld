package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.INewThingsLogic;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;

public class NewThingsAjaxAction extends AjaxAction {
	private static final long serialVersionUID = -7793631350694482153L;
	private Integer ID;
	private Integer start;
	private Integer resultSize;
	private Boolean reply;
	private String content;
	private String toUserID;
	private Integer ReplyID;

	private INewThingsLogic newThingsLogic;

	private JSONObject newthing;

	public String moreNewThings() {
		try {
			User user = new User(getCurrentUser());
			resultList = new JSONArray();
			resultList.addAll(newThingsLogic.moreNewThings(start, resultSize,
					user));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String viewWatchHistory() {
		try {
			User user = new User(getCurrentUser());
			resultList = new JSONArray();
			resultList.addAll(newThingsLogic.viewNewThingsOfUser(0, 6,
					user,3));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String deleteNewThings() {
		try {
			newThingsLogic.deleteNewThings(ID);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String viewAllReply() {
		try {
			User user = new User(getCurrentUser());
			resultList = new JSONArray();
			resultList.addAll(newThingsLogic.viewAllNewThingsReply(ID, user));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addReply() {
		try {
			User user = new User(getCurrentUser());
			ReplyID = newThingsLogic.addNewThingsReply(ID, reply, user,
					toUserID, content);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String deleteNewThingsReply() {
		try {
			ID = newThingsLogic.deleteNewThingsReply(ReplyID);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			User user = new User(getCurrentUser());

			resultList = new JSONArray();
			resultList.addAll(newThingsLogic.viewAllNewThingsReply(ID, user));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String viewNewReply() {
		try {
			User user = new User(getCurrentUser());
			resultList = new JSONArray();
			resultList.addAll(newThingsLogic.viewNewReply(user));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String updateReplyReaded() {
		try {
			User user = new User(getCurrentUser());
			newThingsLogic.setReplyReaded(ID, user);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String viewOneNewThing() {
		try {
			User user = new User(getCurrentUser());
			newthing = JSONObject.fromObject(newThingsLogic.getOneNewThing(ID,
					user));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setID(Integer id) {
		ID = id;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}

	public void setNewThingsLogic(INewThingsLogic newThingsLogic) {
		this.newThingsLogic = newThingsLogic;
	}

	public void setReply(Boolean reply) {
		this.reply = reply;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setToUserID(String toUserID) {
		this.toUserID = toUserID;
	}

	public void setReplyID(Integer replyID) {
		ReplyID = replyID;
	}

	@JSON(serialize = false)
	public INewThingsLogic getNewThingsLogic() {
		return newThingsLogic;
	}

	public JSONObject getNewthing() {
		if (newthing == null) {
			newthing = new JSONObject();
		}
		return newthing;
	}
}