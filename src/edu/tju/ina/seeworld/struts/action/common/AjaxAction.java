package edu.tju.ina.seeworld.struts.action.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

public class AjaxAction extends BaseAction {

	private static final long serialVersionUID = 7513849776896848639L;
	protected Integer currentPage;
	protected Integer totalPages;
	protected JSONArray resultList;
	protected JSONObject pageJSON;
	protected Integer len;

	public Integer getCurrentPage() {
		if (currentPage == null) {
			currentPage = 1;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public JSONArray getResultList() {
		return resultList;
	}

	public void setResultList(JSONArray resultList) {
		this.resultList = resultList;
	}

	public JSONObject getPageJSON() {
		return pageJSON;
	}

	public void setPageJSON(JSONObject pageJSON) {
		this.pageJSON = pageJSON;
	}

	@JSON(serialize = false)
	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getLen() {
		return len;
	}

	public void setLen(Integer len) {
		this.len = len;
	}
}
