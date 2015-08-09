package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IInvitationCodeLogic;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;

public class InvitationCodeAjaxAction extends AjaxAction {
	private static final long serialVersionUID = -8933510875888727045L;
	private String id;
	private String userId;
	private IInvitationCodeLogic invitationCodeLogic;
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setInvitationCodeLogic(IInvitationCodeLogic invitationCodeLogic) {
		this.invitationCodeLogic = invitationCodeLogic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String checkInvitationCode() {
		try {
			message = invitationCodeLogic.isValid(id);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String showInvitationCodeList() throws SeeWorldException {
		resultList = new JSONArray();
		resultList
				.addAll(invitationCodeLogic.findInvitationCodeForUser(userId));
		return Action.SUCCESS;
	}

	public void delete() {
		try {
			invitationCodeLogic.deleteInvitationCode(id);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
