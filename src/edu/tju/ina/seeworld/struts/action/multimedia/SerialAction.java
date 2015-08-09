package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.ActorLogic;
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.CategoryLogic;
import edu.tju.ina.seeworld.logic.DirectorLogic;
import edu.tju.ina.seeworld.logic.SerialLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.logic.UserLogic;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.AreaType;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.SerialVOForView;

public class SerialAction extends BaseAction implements ModelDriven<Serial>{

	private static final long serialVersionUID = -5657779553857776191L;

	/* AreaType枚举类型的索引值 */
	private int type;
	
	/*
	 * 如果页面中有一个最重要的SerialVOForView列表，则使用contentList这个统一的数组
	 * 在More页中即为主体部分的细节部分
	 */
	private List<SerialVOForView> contentList;
	
	private List<SerialVOForView> randomList;
	private List<SerialVOForView> rankList;
	
	private List<SerialVOForView> EA_NewestList;
	private List<SerialVOForView> HT_NewestList;
	private List<SerialVOForView> ML_NewestList;
	private List<SerialVOForView> JK_NewestList;

	private SettingLogic settingLogic;
	private SerialLogic serialLogic;
	private AreaAndCountryLogic areaAndCountryLogic;
	private CategoryLogic categoryLogic;
	private DirectorLogic directorLogic;
	private ActorLogic actorLogic;
	private UserLogic userLogic;
	
	private VOPOTransformator vOPOTransformator;

	private Category category;
	private AreaAndCountry areaAndCountry;
	private String publishedYear;

	private Serial serial = new Serial();

	private List<AreaAndCountry> areaAndCountryList;
	private List<Category> categoryList;
	private List<String> publishedYearList;
	private List<Director> directorList;
	private List<Actor> actorList;
	
	int len = 0;
	private int categoryId;
	private int targetId;
	
	public String overviewAction() {
		try {
			int recommandListLength = settingLogic.getIntConfigValue(Constant.RANK_LIST_LENGTH);
			int serialListLength = settingLogic.getIntConfigValue(Constant.SERIAL_LIST_LENGTH);
			
			EA_NewestList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.findByAreaOrderByTime(AreaType.EA.ordinal(), 0, serialListLength));
			HT_NewestList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.findByAreaOrderByTime(AreaType.HT.ordinal(), 0, serialListLength));
			ML_NewestList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.findByAreaOrderByTime(AreaType.ML.ordinal(), 0, serialListLength));
			JK_NewestList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.findByAreaOrderByTime(AreaType.JK.ordinal(), 0, serialListLength));
			
			rankList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.findByClickCount(0, recommandListLength));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LISTMULTIMEDIA;
	}
	
	//“欧美最新”细节页, z
	public String eaNewestAction(){
		type = AreaType.EA.ordinal();
		getNewestSerials(type);
		return MOREMULTIMEDIA;
	}
	//“港台最新”细节页, z
	public String htNewestAction(){
		type = AreaType.HT.ordinal();
		getNewestSerials(type);
		return MOREMULTIMEDIA;
	}
	//“大陆最新”细节页, z
	public String mlNewestAction(){
		type = AreaType.ML.ordinal();
		getNewestSerials(type);
		return MOREMULTIMEDIA;
	}
	//“日韩最新”细节页, z
	public String jkNewestAction(){
		type = AreaType.JK.ordinal();
		getNewestSerials(type);
		return MOREMULTIMEDIA;
	}
	//“排行榜”细节页, z
	public String rankAction(){
		page = getPage();
		try {
			int listLength = settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE);
			int len = serialLogic.getCount(new HashMap<String, Object>());
			rankList = vOPOTransformator
					.transferSerialToVOForViewList(serialLogic.findByClickCount(0, listLength));
			randomList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
					.getRandomSerials(listLength));
			page.setPagesize(listLength);
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
	 * @param areaId 地域ID
	 * @author zhfch
	 */
	private void getNewestSerials(int areaId){
		page = getPage();
		try {
			int listLength = settingLogic.getIntConfigValue(Constant.ITEMS_PER_PAGE);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(IMultimediaDAO.AREA, areaId);
			params.put("status", true);
			int len = serialLogic.getCount(params);
			contentList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.findByAreaOrderByTime(areaId, 0, listLength));
			randomList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
							.getRandomSerials(listLength));
			page.setPagesize(listLength);
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
				len = serialLogic.getCountForSetProperty(ISerialDAO.CATEGORY,
						IBaseHibernateDAO.ID, category.getId());
				contentList = vOPOTransformator.transferSerialToVOForViewList(serialLogic
								.findByCategoryOrderByTime(category.getId(), offset, pageSize));
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
		page.setLen(len);
		page.setPagelist();
		return VIEWMULTIMEDIA;
	}

	public String viewByYearAction() {
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(publishedYear)) {
			param.put(IMultimediaDAO.PULISHEDYEAR, publishedYear);
			try {
				len = serialLogic.getCount(param);
				contentList = vOPOTransformator
						.transferSerialToVOForViewList(serialLogic
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
		page.setLen(len);
		page.setPagelist();
		return VIEWMULTIMEDIA;
	}

	public String viewByAreaAction() {
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		Map<String, Object> param = new HashMap<String, Object>();
		if (areaAndCountry != null && areaAndCountry.getId() != null) {
			param.put(IMultimediaDAO.AREA + ".id", areaAndCountry.getId());
			try {
				len = serialLogic.getCount(param);
				contentList = vOPOTransformator
						.transferSerialToVOForViewList(serialLogic
								.findByAreaOrderByTime(areaAndCountry.getId(),
										offset, pageSize));
				areaAndCountry = areaAndCountryLogic.findById(areaAndCountry
						.getId());
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
		page.setLen(len);
		page.setPagelist();
		return VIEWMULTIMEDIA;
	}

	private void doFindLatestAndTotalLen(int offset, int pageSize)
			throws SeeWorldException {
		len = serialLogic.getCount(new HashMap<String, Object>());
		contentList = vOPOTransformator
				.transferSerialToVOForViewList(serialLogic.findByAddTime(
						offset, pageSize));
	}

	public String preAddAction() {
		try {
			areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			categoryList = categoryLogic.getCategoryListByParentID(3);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ADD;
	}
	
	public String addAction() {
		 if (serial != null && serial.getTitle() != null
				&& serial.getIntroduction() != null) { 
			try {
				HashSet<Category> hs = new HashSet<Category>();
				hs.add(categoryLogic.getById(categoryId));
				serial.setCategory(hs);

				serial.setDirectors(directorLogic.directorNamesToHashSet(directorList));
				serial.setActors(actorLogic.actorNamesToHashSet(actorList));
				
				serial.setStatus(true);
				serial.setResource(new Resource("3"));
				serial.setAddTime(DateUtil.getCurrentTimestamp());
				User u = userLogic.findByUserName(getCurrentUsername()).get(0);
				serial.setUser(u);
				
				targetId = serialLogic.add(serial);
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
			serial = serialLogic.findByID(targetId);
			areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			categoryList = categoryLogic.getCategoryListByParentID(3);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UPDATE;
	}
	
	public String updateAction() {
		if (serial != null && serial.getTitle() != null
				&& serial.getIntroduction() != null) { 
			try {
				Serial s = serialLogic.findByID(serial.getId());
				if(s == null)
					return LIST;
				HashSet<Category> hs = new HashSet<Category>();
				hs.add(categoryLogic.getById(categoryId));
				s.setCategory(hs);
				
				s.setDirectors(directorLogic.directorNamesToHashSet(directorList));
				s.setActors(actorLogic.actorNamesToHashSet(actorList));
				
				s.setTitle(serial.getTitle());
				s.setAlias(serial.getAlias());
				s.setPublishedYear(serial.getPublishedYear());
				if(serial.getImage() != null && !"".equals(serial.getImage().trim()))
					s.setImage(serial.getImage());
				s.setIntroduction(serial.getIntroduction());
				serialLogic.update(s);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return LOGIN;
		}
	}

	public List<SerialVOForView> getEA_NewestList() {
		return EA_NewestList;
	}

	public List<SerialVOForView> getHT_NewestList() {
		return HT_NewestList;
	}

	public List<SerialVOForView> getML_NewestList() {
		return ML_NewestList;
	}

	public List<SerialVOForView> getJK_NewestList() {
		return JK_NewestList;
	}

	public List<SerialVOForView> getRankList() {
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
				areaAndCountryList = areaAndCountryLogic
						.getAllAreaAndCountryList();
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
				categoryList = categoryLogic
						.getCategoryListByParentID(3);
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
				publishedYearList = serialLogic.getPublishedYearList();
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

	public void setSerialLogic(SerialLogic SerialLogic) {
		this.serialLogic = SerialLogic;
	}

	public void setUserLogic(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public List<Director> getDirectorList() {
		return directorList;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setDirectorList(List<Director> directorList) {
		this.directorList = directorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public void setDirectorLogic(DirectorLogic directorLogic) {
		this.directorLogic = directorLogic;
	}

	public void setActorLogic(ActorLogic actorLogic) {
		this.actorLogic = actorLogic;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public Serial getModel() {
		// TODO Auto-generated method stub
		return serial;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<SerialVOForView> getContentList() {
		return contentList;
	}

	public List<SerialVOForView> getRandomList() {
		return randomList;
	}
}
