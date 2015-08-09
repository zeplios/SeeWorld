package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.MovieLogic;
import edu.tju.ina.seeworld.logic.SerialLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.logic.VideoLogic;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.MovieVOForView;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.SerialVOForView;
import edu.tju.ina.seeworld.vo.VideoVOForView;

public class SearchAjaxAction extends AjaxAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3766384274053545642L;
	private List<MovieVOForView> movieList_For_View;
	private List<SerialVOForView> serialList_For_View;
	private List<VideoVOForView> videoList_For_View;
	
	private MovieLogic movieLogic;
	private SerialLogic serialLogic;
	private VideoLogic videoLogic;
	private SettingLogic settingLogic;
	
	private String queryKey;
	
	private VOPOTransformator vOPOTransformator;
	
	public String findMovieByNameDisplayByPage(){
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();

		try {
			if (len == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(IMovieDAO.TITLE, "%" + queryKey + "%");
				len = movieLogic.getCount(map);
			}
			movieList_For_View = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.getMovieByNameByPage("%" + queryKey + "%", offset, pageSize));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultList = new JSONArray();
		resultList.addAll(movieList_For_View);
		pageJSON = JSONObject.fromObject(page);
		return SUCCESS;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public String findSerialByNameDisplayByPage(){
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();

		try {
			//暂时没有找到之前写没写过对应的方法，所以写成找前1000个匹配的结果
			serialList_For_View = vOPOTransformator.transferSerialToVOForViewList(serialLogic.findByNameOrderByTime("%" + queryKey + "%", 0, 1000));
			if (len == null) {
				len = serialList_For_View.size();
			}
			if(len - offset > 10){
				serialList_For_View = serialList_For_View.subList(offset, pageSize);
			}
			else{
				serialList_For_View = serialList_For_View.subList(offset, len);
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultList = new JSONArray();
		resultList.addAll(serialList_For_View);
		pageJSON = JSONObject.fromObject(page);
		return SUCCESS;
	}

	public String findVideoByNameDisplayByPage(){
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();

		try {
			videoList_For_View = vOPOTransformator.transferVideoToVOForViewList(videoLogic.getVideoByName("%" + queryKey + "%"));
			if (len == null) {
				len = videoList_For_View.size();
			}
			if(len - offset > 10){
				videoList_For_View = videoList_For_View.subList(offset, pageSize);
			}
			else{
				videoList_For_View = videoList_For_View.subList(offset, len);
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultList = new JSONArray();
		resultList.addAll(videoList_For_View);
		pageJSON = JSONObject.fromObject(page);
		return SUCCESS;
	}
	
	@JSON(serialize = false)
	public Pagination getPage() {
		page = super.getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.ITEMS_PER_PAGE));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setCurrentpage(getCurrentPage());
		page.setLen(len);
		page.setPagenum(len / page.getPagesize() + 1);
		// page.setPagenum(getTotalPages());
		page.setPagelist();
		return page;
	}

	public void setMovieLogic(MovieLogic movieLogic) {
		this.movieLogic = movieLogic;
	}

	public void setSerialLogic(SerialLogic serialLogic) {
		this.serialLogic = serialLogic;
	}

	public void setVideoLogic(VideoLogic videoLogic) {
		this.videoLogic = videoLogic;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	
}
