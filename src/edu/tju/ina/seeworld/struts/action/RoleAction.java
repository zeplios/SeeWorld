package edu.tju.ina.seeworld.struts.action;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IRoleLogic;
import edu.tju.ina.seeworld.vo.RoleVO;

public class RoleAction extends ActionSupport implements ModelDriven<RoleVO>{
	private RoleVO  model=new RoleVO();
	private JSONArray roleList;
	private IRoleLogic roleLogic;
	
	
	public JSONArray getRoleList() {
		return roleList;
	}


	public void setRoleLogic(IRoleLogic roleLogic) {
		this.roleLogic = roleLogic;
	}


	public RoleVO getModel() {
		return model;
	}
	
	public String  showRoleList() throws SeeWorldException{
		roleList=roleLogic.showRoleList();
		return Action.SUCCESS;
	}

}
