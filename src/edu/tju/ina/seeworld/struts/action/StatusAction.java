package edu.tju.ina.seeworld.struts.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IStatusLogic;
import edu.tju.ina.seeworld.vo.StatusVO;

public class StatusAction extends ActionSupport implements ModelDriven<StatusVO>{
	private IStatusLogic statusLogic;
	private StatusVO model=new StatusVO();
	private JSONObject status;
	private JSONArray statusList;
	private int statusSum;
	private Integer currentPage;
	private JSONObject PageJson;
	
	
	public JSONObject getPageJson() {
		return PageJson;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public int getStatusSum() {
		return statusSum;
	}

	public  JSONArray getStatusList() {
		return statusList;
	}

	public  JSONObject getStatus() {
		return status;
	}

	public  void setStatus(JSONObject status) {
		this.status = status;
	}

	public  StatusVO getModel() {
		return model;
	}

	public  void setStatusLogic(IStatusLogic statusLogic) {
		this.statusLogic = statusLogic;
	}
	public String deleteStatus() throws SeeWorldException{
		statusLogic.delStatus(model);
		return Action.SUCCESS;
	}
	public String updateStatus() throws SeeWorldException{
		statusLogic.updateStatus(model);
		statusSum=statusLogic.getStatusSum();
		status=statusLogic.getJsonStatus();
		return Action.SUCCESS;
	}
	public String showStatus() throws SeeWorldException{
		statusLogic.showStatus(model);
		statusSum=statusLogic.getStatusSum();
		status=statusLogic.getJsonStatus();
		return Action.SUCCESS;
	}
	public String showAllStatus() throws SeeWorldException{
		statusLogic.getAllStatus(model);
		statusList=statusLogic.getStatusList();
		return Action.SUCCESS;
	}
	public String gotoPage() throws SeeWorldException{
		statusLogic.gotoPage(statusSum, currentPage, model.getUserId());
		statusList=statusLogic.getStatusList();
		PageJson=statusLogic.getPageJson();
		return Action.SUCCESS;
	}
}
