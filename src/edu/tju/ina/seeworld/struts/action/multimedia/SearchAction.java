package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.MovieLogic;
import edu.tju.ina.seeworld.logic.SerialLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.logic.VideoLogic;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.MovieVOForView;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.SerialVOForView;
import edu.tju.ina.seeworld.vo.VideoVOForView;

public class SearchAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7414135096296533783L;
	private final static String MOVIE = "movie";
	private final static String SERIAL = "serial";
	private final static String VIDEO = "video";

	private List<MovieVOForView> movieListForView;
	private List<SerialVOForView> serialListForView;
	private List<VideoVOForView> videoListForView;
	private MovieLogic movieLogic;
	private SerialLogic serialLogic;
	private VideoLogic videoLogic;
	
	private List<MovieVOForView> RandomList;
	
	private String queryCategory;
	private String queryKey;
	
	private Pagination page;
	private VOPOTransformator vOPOTransformator;
	private SettingLogic settingLogic;
	
	private int len = 0;
	
	public String searchAction(){
		page = this.getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		//如果用户直接在浏览器中敲入该地址，则将关键词设为空字符串
		if(queryKey == null){
			queryKey = "";
			queryCategory = MOVIE;
		}
		try {
			if(queryCategory.equals(MOVIE)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(IMovieDAO.TITLE, "%" + queryKey + "%");
				len = movieLogic.getCount(map);
				int rankListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
				RandomList = vOPOTransformator
						.transferMovieToVOForViewList(movieLogic.getRandomMovies(rankListLength));
				movieListForView = vOPOTransformator.transferMovieToVOForViewList(
						movieLogic.getMovieByNameByPage("%" + queryKey + "%", offset, pageSize));
			}
			else if(queryCategory.equals(SERIAL)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(ISerialDAO.TITLE, "%" + queryKey + "%");
				len = serialLogic.getCount(map);
				int rankListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
				RandomList = vOPOTransformator
						.transferMovieToVOForViewList(movieLogic.getRandomMovies(rankListLength));
				serialListForView = vOPOTransformator.transferSerialToVOForViewList(
						serialLogic.findByNameOrderByTime("%" + queryKey + "%", offset, pageSize));
			}
			else if(queryCategory.equals(VIDEO)){
				int rankListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
				RandomList = vOPOTransformator
						.transferMovieToVOForViewList(movieLogic.getRandomMovies(rankListLength));
				videoListForView = vOPOTransformator.transferVideoToVOForViewList(videoLogic.getVideoByName("%" + queryKey + "%"));
				if((len = videoListForView.size()) > 10){
					videoListForView = videoListForView.subList(offset, pageSize);
				}
				else{
					videoListForView = videoListForView.subList(offset, len);
				}
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setLen(len);
		page.setPagelist();
		return SEARCHRESULT;
	}
	
	public Pagination getPage() {
		page = super.getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.ITEMS_PER_PAGE));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	public List<MovieVOForView> getMovieListForView() {
		return movieListForView;
	}

	public List<SerialVOForView> getSerialListForView() {
		return serialListForView;
	}

	public List<VideoVOForView> getVideoListForView() {
		return videoListForView;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
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
	
	public String getQueryCategory() {
		return queryCategory;
	}

	public void setQueryCategory(String queryCategory) {
		this.queryCategory = queryCategory;
	}
	
	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
	public List<MovieVOForView> getRandomList() {
		return RandomList;
	}
}
