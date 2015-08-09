package edu.tju.ina.seeworld.struts.action.multimedia;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.ActorLogic;
import edu.tju.ina.seeworld.logic.DirectorLogic;
import edu.tju.ina.seeworld.logic.LecturerLogic;
import edu.tju.ina.seeworld.logic.MovieLogic;
import edu.tju.ina.seeworld.logic.SerialLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.logic.SingleSerialLogic;
import edu.tju.ina.seeworld.logic.VideoLogic;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Lecturer;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.MultimediaType;
import edu.tju.ina.seeworld.vo.MovieVOForView;
import edu.tju.ina.seeworld.vo.Pagination;

public class MultimediaAjaxAction extends AjaxAction {

	private static final long serialVersionUID = 7513849776896848639L;
	private Integer categoryId;
	private Integer areaAndCountryId;
	private String publishedYear;
	private int orderBy;
	private Integer targetId;
	private String resourceId;
	private String q;
	
	private DirectorLogic directorLogic;
	private ActorLogic actorLogic;
	private LecturerLogic lecturerLogic;
	private MovieLogic movieLogic;
	private VOPOTransformator vOPOTransformator;
	private VideoLogic videoLogic;
	private SerialLogic serialLogic;
	private SingleSerialLogic singleSerialLogic;
	private SettingLogic settingLogic;

	private IDAssistant iDAssistant;

	/**
	 * 后台电影列表页的快速查询，搜索q开头的电影，z
	 * @return
	 */
	public String searchMovie() throws SeeWorldException {
		try {
			int pageSize = settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE);
			if (len == null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(IMovieDAO.TITLE, q + "%");
				len = movieLogic.getCount(map);
			}
			resultList = new JSONArray();
			resultList.addAll(vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.getMovieByNameByPage(q + "%", 0, pageSize)));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 用于电影管理页获取电影列表等，z
	 * @param 搜索条件：categoryId或areaAndCountryId或publishedYear， 都有时以第一个（categoryId）为准
	 * @param 排序方式：orderBy
	 * @return
	 */
 	public String findMovieContentByPage() {
 		String propertyName = "";
 		Object value = "";
 		Map<String, Object> param = new HashMap<String, Object>();
 		if(categoryId != null){
 			param.put(IMultimediaDAO.CATEGORY + ".id", categoryId);
 			propertyName = IMultimediaDAO.CATEGORY;
 			value = categoryId;
 		}
 		else if(areaAndCountryId != null){
 			param.put(IMultimediaDAO.AREA + ".id", areaAndCountryId);
 			propertyName = IMultimediaDAO.AREA;
 			value = areaAndCountryId;
 		}
 		else if(StringUtils.isNotBlank(publishedYear)){
 			param.put(IMultimediaDAO.PULISHEDYEAR, publishedYear);
 			propertyName += IMultimediaDAO.PULISHEDYEAR;
 			value = publishedYear;
 		}
		if (len == null) {
			try {
				len = movieLogic.getCount(param);
				setTotalPages(len
						/ settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		String order = getOrderMode();

		if (StringUtils.isNotBlank(order)) {
			try {
				resultList = new JSONArray();
				List<MovieVOForView> list = vOPOTransformator.transferMovieToVOForViewList(movieLogic
					.findOrderedListByPropertyByPage(propertyName, value, true, order, offset, pageSize));
				resultList.addAll(list);
				pageJSON = JSONObject.fromObject(page);
			} catch (SeeWorldException e) {
				// TODO Handle exception
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 根据前台jQuery传入的参数进行后台查询，返回一定条件下的分页数据
	 * 
	 * @return
	 */
	public String findVideoContentByPage() {
		if (len == null) {
			//Map<String, Object> param = new HashMap<String, Object>();
			//param.put(IMultimediaDAO.CATEGORY + ".id", categoryId);
			try {
				len = videoLogic.getCount(new HashMap<String, Object>());
				setTotalPages(len / 
						settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		String propertyName = (categoryId != null ? IMultimediaDAO.CATEGORY
				: (areaAndCountryId != null ? IMultimediaDAO.AREA
						: (StringUtils.isNotBlank(publishedYear) ? IMultimediaDAO.PULISHEDYEAR
								: "")));
		Object value = (categoryId != null ? categoryId
				: (areaAndCountryId != null ? areaAndCountryId : (StringUtils
						.isNotBlank(publishedYear) ? publishedYear : "")));
		String order = getOrderMode();

		if (StringUtils.isNotBlank(order)) {
			try {
				resultList = new JSONArray();
				resultList.addAll(vOPOTransformator
						.transferVideoToVOForViewList(videoLogic
								.findOrderedListByPropertyByPage(propertyName,
										value, true, order, offset, pageSize)));
				pageJSON = JSONObject.fromObject(page);
			} catch (SeeWorldException e) {
				// TODO Handle exception
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 用于电影管理页获取剧集列表等，z
	 * @param 搜索条件
	 * @param 排序方式：orderBy
	 * @return
	 */
	public String findSerialContentByPage() {
 		String propertyName = "";
 		Object value = "";
 		Map<String, Object> param = new HashMap<String, Object>();
 		if(categoryId != null){
 			param.put(IMultimediaDAO.CATEGORY + ".id", categoryId);
 			propertyName = IMultimediaDAO.CATEGORY;
 			value = categoryId;
 		}
 		else if(areaAndCountryId != null){
 			param.put(IMultimediaDAO.AREA + ".id", areaAndCountryId);
 			propertyName = IMultimediaDAO.AREA;
 			value = areaAndCountryId;
 		}
 		else if(StringUtils.isNotBlank(publishedYear)){
 			param.put(IMultimediaDAO.PULISHEDYEAR, publishedYear);
 			propertyName += IMultimediaDAO.PULISHEDYEAR;
 			value = publishedYear;
 		}
		if (len == null) {
			try {
				len = serialLogic.getCount(param);
				setTotalPages(len
						/ settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();

		String order = getOrderMode();

		if (StringUtils.isNotBlank(order)) {
			try {
				resultList = new JSONArray();
				resultList.addAll(vOPOTransformator.transferSerialToVOForViewList(serialLogic
					.findByPropertyByPage(propertyName, value, order, offset, pageSize)));
				pageJSON = JSONObject.fromObject(page);
			} catch (SeeWorldException e) {
				// TODO Handle exception
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 查询某剧集的单集列表，z
	 * @return
	 */
	public String findSingleSerialContentByPage() {
		if (len == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(IMultimediaDAO.SERIAL + ".id", targetId);
			try {
				len = singleSerialLogic.getCount(param);
				setTotalPages(len
						/ settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();

		try {
			resultList = new JSONArray();
			resultList.addAll(vOPOTransformator.transferSingleSerialToVOForViewList(singleSerialLogic
							.findByAllBySeason(targetId, offset, pageSize)));
			pageJSON = JSONObject.fromObject(page);
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	/**
	 * 获取用于构造sql语句的order by分句成分，需要参数orderby，z
	 * @return
	 */
	public String getOrderMode(){
		String order = null;
		switch (orderBy) {
		case Constant.ORDERBY_NAME:
			order = IMultimediaDAO.ORDER_BY_NAME;
			break;
		case Constant.ORDERBY_TIME:
			order = IMultimediaDAO.ORDER_BY_ADDTIME;
			break;
		case Constant.ORDERBY_CLICK:
			order = IMultimediaDAO.ORDER_BY_CLICKCOUNT;
			break;
		case Constant.ORDERBY_RECOMMEND:
			order = IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT;
			break;
		case Constant.ORDERBY_COLLECT:
			order = IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT;
			break;
		}
		return order;
	}

	/**
	 * 管理页面删除视频，z
	 * @return
	 */
	public String delete() {
		if (targetId != null && StringUtils.isNotBlank(resourceId)) {
			String resourcename = iDAssistant.getResourceName(resourceId);
			
			String tempPicPath = "";
			try {
				tempPicPath = settingLogic.getStringConfigValue(Constant.MULTIMEDIA_IMAGE_PATH);
			} catch (SeeWorldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("/");	//返回一直到/SeeWorld/的绝对路径
			path = path.substring(0, path.indexOf(context.getContextPath().substring(1)) - 1);	//返回\SeeWorld\之前的部分
			path = path + "/" + tempPicPath + "/";
			try {
				if (StringUtils.equalsIgnoreCase(resourcename, MultimediaType.Serial.toString())) {
					Serial s = new Serial();
					s = serialLogic.findByID(targetId);
					path = path + s.getImage();
					serialLogic.delete(s);
				} else if (StringUtils.equalsIgnoreCase(resourcename, MultimediaType.SingleSerial.toString())) {
					SingleSerial ss = new SingleSerial();
					ss = singleSerialLogic.findByID(targetId);
					singleSerialLogic.delete(ss);
				} else if (StringUtils.equalsIgnoreCase(resourcename, MultimediaType.Movie.toString())) {
					Movie m = new Movie();
					m = movieLogic.getMovieById(targetId);
					path = path + m.getImage();
					movieLogic.delete(m);
				} else if (StringUtils.equalsIgnoreCase(resourcename, MultimediaType.Serial.toString())) {
					Video v = new Video();
					v = videoLogic.getVideoById(targetId);
					path = path + v.getImage();
					videoLogic.delete(v);
				}
			} catch (SeeWorldException e) {
				// TODO Handle exception
				//e.printStackTrace();
			}
			File file = new File(path);
			file.delete();
		}
		return SUCCESS;
	}

	/*
	 * 在上传影片页面 根据传入的director关键字 搜索相关导演名称
	 */

	public String searchDirector() throws SeeWorldException {
		try {
			resultList = new JSONArray();
			List<Director> list = directorLogic.search(q);
			for (Director d : list) {
				// 过滤不必要字段，避免循环引用错误
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String[] { "movies", "singleSerials",
						"areaAndCountry" });
				/*
				 * 第二种实现过滤字段的方法 config.setJsonPropertyFilter(new
				 * PropertyFilter() { public boolean apply(Object source, String
				 * name, Object value) { if (name.equals("movies") ||
				 * name.equals("singleSerials") ||
				 * name.equals("areaAndCountry")) { return true; } else { return
				 * false; } } });
				 */
				resultList
						.add(JSONArray.fromObject(d, config).getJSONObject(0));
			}
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String searchActor() throws SeeWorldException {
		try {
			resultList = new JSONArray();
			List<Actor> list = actorLogic.search(q);
			for (Actor a : list) {
				// 过滤不必要字段，避免循环引用错误
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String[] { "movies", "singleSerials",
						"areaAndCountry" });
				resultList
						.add(JSONArray.fromObject(a, config).getJSONObject(0));
			}
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String searchLecturer() throws SeeWorldException {
		try {
			resultList = new JSONArray();
			List<Lecturer> list = lecturerLogic.search(q);
			for (Lecturer l : list) {
				// 过滤不必要字段，避免循环引用错误
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String[] { "videos", "organization" });
				resultList
						.add(JSONArray.fromObject(l, config).getJSONObject(0));
			}
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
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

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@JSON(serialize = false)
	public Integer getAreaAndCountryId() {
		return areaAndCountryId;
	}

	public void setAreaAndCountryId(Integer areaAndCountryId) {
		this.areaAndCountryId = areaAndCountryId;
	}

	@JSON(serialize = false)
	public String getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}

	@JSON(serialize = false)
	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setActorLogic(ActorLogic actorLogic) {
		this.actorLogic = actorLogic;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public void setLecturerLogic(LecturerLogic lecturerLogic) {
		this.lecturerLogic = lecturerLogic;
	}

	public void setMovieLogic(MovieLogic movieLogic) {
		this.movieLogic = movieLogic;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setVideoLogic(VideoLogic videoLogic) {
		this.videoLogic = videoLogic;
	}

	public void setDirectorLogic(DirectorLogic directorLogic) {
		this.directorLogic = directorLogic;
	}

	public void setSingleSerialLogic(SingleSerialLogic singleSerialLogic) {
		this.singleSerialLogic = singleSerialLogic;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}

	public void setSerialLogic(SerialLogic serialLogic) {
		this.serialLogic = serialLogic;
	}
}
