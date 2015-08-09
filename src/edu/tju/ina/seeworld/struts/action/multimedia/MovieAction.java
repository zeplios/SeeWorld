package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.ActorLogic;
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.CategoryLogic;
import edu.tju.ina.seeworld.logic.DirectorLogic;
import edu.tju.ina.seeworld.logic.IUserLogic;
import edu.tju.ina.seeworld.logic.MovieLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.AreaType;
import edu.tju.ina.seeworld.vo.MovieVOForView;
import edu.tju.ina.seeworld.vo.Pagination;

public class MovieAction extends BaseAction implements ModelDriven<Movie> {

	private static final long serialVersionUID = -5657779553857776191L;
	
	/* AreaType枚举类型的索引值 */
	private int type;
	
	private List<MovieVOForView> randomList;
	private List<MovieVOForView> contentList;

	private List<MovieVOForView> EA_NewestList;//欧美
	private List<MovieVOForView> HT_NewestList;//港台
	private List<MovieVOForView> ML_NewestList;//大陆
	private List<MovieVOForView> JK_NewestList;//日韩
	private List<MovieVOForView> rankList;

	private IUserLogic userLogic;
	private MovieLogic movieLogic;
	private DirectorLogic directorLogic;
	private ActorLogic actorLogic;
	private AreaAndCountryLogic areaAndCountryLogic;
	private CategoryLogic categoryLogic;
	private SettingLogic settingLogic;

	private VOPOTransformator vOPOTransformator;

	private Category category;
	private AreaAndCountry areaAndCountry;
	private String publishedYear;
	private String image;

	public Movie movie = new Movie();
	
	private List<AreaAndCountry> areaAndCountryList;
	private List<Category> categoryList;
	private List<Director> directorList;
	private List<Actor> actorList;
	private List<String> publishedYearList;

	private int len = 0;
	private int categoryId;
	private int targetId;

	//电影overview页
	public String overviewAction() {
		try {
			int rankListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
			int updatedListLength = settingLogic.getIntConfigValue(Constant.NEWEST_LIST_LENGTH);
			
			EA_NewestList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.findByAreaOrderByTime(1, 0, updatedListLength));
			HT_NewestList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.findByAreaOrderByTime(2, 0, updatedListLength));
			ML_NewestList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.findByAreaOrderByTime(3, 0, updatedListLength));
			JK_NewestList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.findByAreaOrderByTime(4, 0, updatedListLength));
			
			rankList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.findClickMost(0, rankListLength));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LISTMULTIMEDIA;
	}
	
	//欧美最新细节页
	public String eaNewestAction(){
		type = AreaType.EA.ordinal();
		getNewestMovies(type);
		return MOREMULTIMEDIA;
	}
	//港台最新细节页
	public String htNewestAction(){
		type = AreaType.HT.ordinal();
		getNewestMovies(type);
		return MOREMULTIMEDIA;
	}
	//大陆最新细节页
	public String mlNewestAction(){
		type = AreaType.ML.ordinal();
		getNewestMovies(type);
		return MOREMULTIMEDIA;
	}
	//日韩最新细节页
	public String jkNewestAction(){
		type = AreaType.JK.ordinal();
		getNewestMovies(type);
		return MOREMULTIMEDIA;
	}
	//排行榜细节页
	public String rankAction(){
		page = getPage();
		try {
			int rankListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
			int len = movieLogic.getCount(new HashMap<String, Object>());
			contentList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.findClickMost(0, rankListLength));
			randomList = vOPOTransformator.transferMovieToVOForViewList(
					movieLogic.getRandomMovies(rankListLength));
			page.setPagesize(rankListLength);
			page.setLen(len);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type = AreaType.RANK.ordinal();
		page.setPagelist();
		return MOREMULTIMEDIA;
	}
	
	/**
	 * 根据地区id获取列表
	 * @param areaId为1表示欧美，2表示港台，3表示大陆，4表示日韩
	 * @author zhfch
	 */
	private void getNewestMovies(int areaId){
		page = getPage();
		try {
			int rankListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(IMultimediaDAO.AREA, areaId);
			params.put("status", true);
			int len = movieLogic.getCount(params);
			contentList = vOPOTransformator
					.transferMovieToVOForViewList(movieLogic.findByAreaOrderByTime(areaId, 0, rankListLength));
			randomList = vOPOTransformator
					.transferMovieToVOForViewList(movieLogic.getRandomMovies(rankListLength));
			page.setPagesize(rankListLength);
			page.setLen(len);
			page.setPagelist();
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String viewByCategoryAction() {
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		if (category != null && category.getId() != null) {
			try {
				len = movieLogic.getCountForSetProperty(IMultimediaDAO.CATEGORY, 
						IBaseHibernateDAO.ID, category.getId(), true);
				
				contentList = vOPOTransformator.transferMovieToVOForViewList(
						movieLogic.findByCategoryOrderByTime(category.getId(), offset, pageSize));
				category = categoryLogic.getById(category.getId());
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				doFindLatestAndTotalLen(offset, pageSize);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.getCategoryAreaPublishYearList();

		page.setLen(len);
		page.setPagelist();
		return VIEWMULTIMEDIA;
	}

	public String viewByYearAction() {
		page = getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.ITEMS_PER_PAGE));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int offset = page.getStart();
		int pageSize = page.getPagesize();
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(publishedYear)) {
			param.put(IMultimediaDAO.PULISHEDYEAR, publishedYear);
			try {
				len = movieLogic.getCount(param);
				contentList = vOPOTransformator
						.transferMovieToVOForViewList(movieLogic
								.findByYearOrderByTime(publishedYear, offset,
										pageSize));
			} catch (SeeWorldException e) {
				e.printStackTrace();
			}
		} else {
			try {
				doFindLatestAndTotalLen(offset, pageSize);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.getCategoryAreaPublishYearList();

		page.setLen(len);
		page.setPagelist();
		return VIEWMULTIMEDIA;
	}

	/**
	 * 返回最新的电影列表，以及总的页数
	 * 
	 * @param offset
	 * @param pageSize
	 * @throws SeeWorldException
	 */
	private void doFindLatestAndTotalLen(int offset, int pageSize)
			throws SeeWorldException {
		len = movieLogic.getCount(new HashMap<String, Object>());
		contentList = vOPOTransformator.transferMovieToVOForViewList(
				movieLogic.findLatestList(offset, pageSize));
	}

	public String viewByAreaAction() {
		page = getPage();
		try {
			page.setPagesize(settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		Map<String, Object> param = new HashMap<String, Object>();
		if (areaAndCountry != null && areaAndCountry.getId() != null) {
			param.put(IMultimediaDAO.AREA + ".id", areaAndCountry.getId());
			try {
				len = movieLogic.getCount(param);

				contentList = vOPOTransformator.transferMovieToVOForViewList(
						movieLogic.findByAreaOrderByTime(areaAndCountry.getId(), offset, pageSize));
				
				areaAndCountry = areaAndCountryLogic.findById(areaAndCountry.getId());
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				doFindLatestAndTotalLen(offset, pageSize);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.getCategoryAreaPublishYearList();

		page.setLen(len);
		page.setPagelist();
		return VIEWMULTIMEDIA;
	}

	public String preAddAction() {
		try {
			areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			categoryList = categoryLogic.getCategoryListByParentID(1);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ADD;
	}

	public String addAction() {
		if (movie != null && movie.getTitle() != null
				&& movie.getResource() != null) {
			try {
				HashSet<Category> hs = new HashSet<Category>();
				hs.add(categoryLogic.getById(categoryId));
				movie.setCategory(hs);

				movie.setDirectors(directorLogic.directorNamesToHashSet(directorList));
				movie.setActors(actorLogic.actorNamesToHashSet(actorList));

				movie.setAddTime(DateUtil.getCurrentTimestamp());
				movie.setDeleted(false);

				User u = new User();
				u = userLogic.findByUserName(getCurrentUsername()).get(0);
				movie.setUser(u);

				movie.setStatus(true);
				
				movieLogic.add(movie);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return TO_MAIN_PAGE;
		}
	}

	public String preUpdateAction() {
		try {
			movie = movieLogic.getMovieById(targetId);
			areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			categoryList = categoryLogic.getCategoryListByParentID(1);

			// Only one director & actor
			actorList = new ArrayList<Actor>();
			String actors = "";
			for (Actor a : movie.getActors()) {
				actors += a.getName() + " ";
			}
			actors = actors.trim();
			actorList.add(new Actor(actors));
			
			directorList = new ArrayList<Director>();
			String directors = "";
			for (Director d : movie.getDirectors()) {
				directors += d.getName() + " ";
			}
			directors = directors.trim();
			directorList.add(new Director(directors));
			
			Iterator<Category> iterator_category = movie.getCategory().iterator();
			if (iterator_category.hasNext()){
				categoryId=iterator_category.next().getId();
			}

		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String updateAction() {
		if (movie != null && movie.getId() != null && movie.getTitle() != null
				&& movie.getResource() != null) { 
			try {
				Movie old = movieLogic.getMovieById(movie.getId());
				old.setTitle(movie.getTitle());
				old.setAlias(movie.getAlias());
				old.setPublishedYear(movie.getPublishedYear());
				old.setIntroduction(movie.getIntroduction());
				
				HashSet<Category> hs = new HashSet<Category>();
				hs.add(categoryLogic.getById(categoryId));
				old.setCategory(hs);

				old.setDirectors(directorLogic.directorNamesToHashSet(directorList));
				old.setActors(actorLogic.actorNamesToHashSet(actorList));

				old.setDeleted(false);
				old.setStatus(true);

				movieLogic.update(old);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return TO_MAIN_PAGE;
		}
	}

	public void getCategoryAreaPublishYearList() {
		getCategoryList();
		getAreaAndCountryList();
		getPublishedYearList();
	}

	public List<MovieVOForView> getContentList() {
		return contentList;
	}

	public List<MovieVOForView> getEA_NewestList() {
		return EA_NewestList;
	}

	public List<MovieVOForView> getHT_NewestList() {
		return HT_NewestList;
	}

	public List<MovieVOForView> getML_NewestList() {
		return ML_NewestList;
	}

	public List<MovieVOForView> getJK_NewestList() {
		return JK_NewestList;
	}

	public List<MovieVOForView> getRankList() {
		return rankList;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public AreaAndCountry getAreaAndCountry() {
		return areaAndCountry;
	}

	public void setAreaAndCountry(AreaAndCountry areaAndCountry) {
		this.areaAndCountry = areaAndCountry;
	}

	public String getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}

	public List<AreaAndCountry> getAreaAndCountryList() {
		if (areaAndCountryList == null) {
			try {
				areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return areaAndCountryList;
	}

	public void setAreaAndCountryList(List<AreaAndCountry> areaAndCountryList) {
		this.areaAndCountryList = areaAndCountryList;
	}

	public List<Category> getCategoryList() {
		if (categoryList == null) {
			try {
				categoryList = categoryLogic.getCategoryListByParentID(1);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<String> getPublishedYearList() {
		if (publishedYearList == null) {
			try {
				publishedYearList = movieLogic.getPublishedYearList();
			} catch (SeeWorldException e) {
				e.printStackTrace();
			}
		}
		return publishedYearList;
	}

	public void setPublishedYearList(List<String> publishedYearList) {
		this.publishedYearList = publishedYearList;
	}

	public void setAreaAndCountryLogic(AreaAndCountryLogic areaAndCountryLogic) {
		this.areaAndCountryLogic = areaAndCountryLogic;
	}

	public void setCategoryLogic(CategoryLogic categoryLogic) {
		this.categoryLogic = categoryLogic;
	}

	public void setMovieLogic(MovieLogic MovieLogic) {
		this.movieLogic = MovieLogic;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public List<Director> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(List<Director> directorList) {
		this.directorList = directorList;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDirectorLogic(DirectorLogic directorLogic) {
		this.directorLogic = directorLogic;
	}

	public void setActorLogic(ActorLogic actorLogic) {
		this.actorLogic = actorLogic;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<MovieVOForView> getRandomList() {
		return randomList;
	}

	public int getType() {
		return type;
	}

	public void setUserLogic(IUserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public Movie getModel() {
		// TODO Auto-generated method stub
		return movie;
	}
}
