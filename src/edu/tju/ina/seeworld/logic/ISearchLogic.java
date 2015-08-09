package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.exception.SeeWorldException;

public interface ISearchLogic {
	public Integer search(String keyword) throws SeeWorldException;
	
	public void changePage(String keyword,Integer sum,Integer currentPage) throws SeeWorldException;
	
	public JSONArray getResultList();
	
	public JSONObject getPageJson();
}
