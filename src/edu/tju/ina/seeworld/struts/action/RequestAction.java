package edu.tju.ina.seeworld.struts.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IRequestLogic;
import edu.tju.ina.seeworld.vo.RequestVO;

public class RequestAction extends ActionSupport implements ModelDriven<RequestVO>{
	private String message;
	private IRequestLogic requestLogic;
	private RequestVO model=new RequestVO();
	private JSONObject request;
	private JSONArray requestList;
	private JSONObject PageJson;
	private Integer requestSum;
	private Integer currentPage;
	
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getRequestSum() {
		return requestSum;
	}
	public JSONArray getRequestList() {
		return requestList;
	}
	public JSONObject getPageJson() {
		return PageJson;
	}
	public JSONObject getRequest() {
		return request;
	}
	@JSON(serialize=false)  	
	public RequestVO getModel() {
		return this.model;
	}
	public String getMessage() {
		return message;
	}
	public void setRequestLogic(IRequestLogic requestLogic) {
		this.requestLogic = requestLogic;
	}
	public String addRequest() throws SeeWorldException{
		message=requestLogic.addRequest(model);
		request=requestLogic.getRequestJson();
		return Action.SUCCESS;
	}
	public String deleteRequest() throws SeeWorldException{
		message=requestLogic.deleteRequest(model);
		return Action.SUCCESS;
	}
	public String showRequestList() throws SeeWorldException{
		requestLogic.showRequestList(model);
		requestList=requestLogic.getRequestList();
		PageJson=requestLogic.getPageJson();
		requestSum=requestLogic.getRequestSum();
		return Action.SUCCESS;
	}
	public String gotoPage() throws SeeWorldException{
		requestLogic.gotoPage(model, requestSum, currentPage);
		requestList=requestLogic.getRequestList();
		PageJson=requestLogic.getPageJson();
		return Action.SUCCESS;
	}
	public String showRequestSum() throws SeeWorldException{
		requestSum=requestLogic.getRequestNum(model);
		return Action.SUCCESS;
	}
}
