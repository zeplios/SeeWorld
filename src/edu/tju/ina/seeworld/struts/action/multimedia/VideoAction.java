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
import edu.tju.ina.seeworld.logic.AreaAndCountryLogic;
import edu.tju.ina.seeworld.logic.CategoryLogic;
import edu.tju.ina.seeworld.logic.LecturerLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.logic.UserLogic;
import edu.tju.ina.seeworld.logic.VideoLogic;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Lecturer;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.MultimediaType;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.VideoVOForView;

public class VideoAction extends BaseAction implements ModelDriven<Video> {

	private static final long serialVersionUID = -4830099066711173999L;

	private List<VideoVOForView> rankList;
	private List<VideoVOForView> contentList;	/* overview页表示近期更新 */
	
	private SettingLogic settingLogic;
	private VideoLogic videoLogic;
	private AreaAndCountryLogic areaAndCountryLogic;
	private CategoryLogic categoryLogic;
	private LecturerLogic lecturerLogic;
	private UserLogic userLogic;

	private Category category;
	private AreaAndCountry areaAndCountry;
	private String publishedYear;

	private Video model = new Video();

	private VOPOTransformator vOPOTransformator;
	private List<AreaAndCountry> areaAndCountryList;
	private List<Category> categoryList;
	private List<String> publishedYearList;
	private List<Lecturer> lecturerList;
	
	private int categoryId;
	private int len = 0;
	private int targetId;

	public String overviewAction() {
		try {
			int rankListLength = settingLogic
					.getIntConfigValue(Constant.RANK_LIST_LENGTH);
			int newestListLength = settingLogic
					.getIntConfigValue(Constant.NEWEST_LIST_LENGTH);
			contentList = vOPOTransformator.transferVideoToVOForViewList(
					videoLogic.findLatestList(0, newestListLength));
			rankList = vOPOTransformator.transferVideoToVOForViewList(
					videoLogic.findClickMost(0, rankListLength));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LISTMULTIMEDIA;
	}

	public String viewByCategoryAction() {
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		if (category != null && category.getId() != null) {
			try {
				len = videoLogic.getCountForSetProperty(
						IMultimediaDAO.CATEGORY, IBaseHibernateDAO.ID, category
								.getId(), true);
				contentList = vOPOTransformator
						.transferVideoToVOForViewList(videoLogic
								.findByCategoryOrderByTime(category.getId(),
										offset, pageSize));
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
				len = videoLogic.getCount(param);
				contentList = vOPOTransformator.transferVideoToVOForViewList(videoLogic
						.findByYearOrderByTime(publishedYear, offset, pageSize));
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

	/**
	 * 按地域查找，这个方法先不删，不过暂时用不着，用地域划分视频意义不是很大，z
	 * @return
	 */
	public String viewByAreaAction() {
		page = getPage();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		Map<String, Object> param = new HashMap<String, Object>();
		if (areaAndCountry != null && areaAndCountry.getId() != null) {
			param.put(IMultimediaDAO.AREA + ".id", areaAndCountry.getId());
			try {
				len = videoLogic.getCount(param);
				contentList = vOPOTransformator.transferVideoToVOForViewList(videoLogic
						.findByAreaOrderByTime(areaAndCountry.getId(), offset, pageSize));
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
		len = videoLogic.getCount(new HashMap<String, Object>());
		contentList = vOPOTransformator.transferVideoToVOForViewList(
				videoLogic.findLatestList(offset, pageSize));
	}

	public String preAddAction() {
		try {
			areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			categoryList = categoryLogic.getCategoryListByParentID(2);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ADD;
	}

	public String addAction() {
		if (model != null && model.getTitle() != null
				&& model.getIntroduction() != null ) {
			try {
				HashSet<Category> hs=new HashSet<Category>();
				hs.add(categoryLogic.getById(categoryId));
				model.setCategory(hs);
				
				model.setLecturers(lecturerLogic.lecturerNamesToHashSet(lecturerList));

				model.setAddTime(DateUtil.getCurrentTimestamp());
				model.setDeleted(false);

				User u = new User();
				u = userLogic.findByUserName(getCurrentUsername()).get(0);
				model.setUser(u);

				model.setStatus(true);
				model.setSections(1);
				model.setSize(0);
				model.setProcess(new Float(100));
				
				targetId = videoLogic.add(model);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return LOGIN;
		}

	}
	
	public String preUpdateAction() {
		try {
			model = videoLogic.getVideoById(targetId);
			areaAndCountryList = areaAndCountryLogic.getAllAreaAndCountryList();
			categoryList = categoryLogic.getCategoryListByParentID(2);

			if(model.getLecturers() != null){
				lecturerList = new ArrayList<Lecturer>();
				lecturerList.addAll(model.getLecturers());
			}
			
			Iterator<Category> iterator_category = model.getCategory().iterator();
			if (iterator_category.hasNext()){
				categoryId = iterator_category.next().getId();
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UPDATE;
	}
	
	public String updateAction() {
		if (model != null && model.getTitle() != null
				&& model.getIntroduction() != null ) {
			try {
				Video old = videoLogic.getVideoById(model.getId());
				if(old == null)
					return LIST;

				old.setTitle(model.getTitle());
				old.setAlias(model.getAlias());
				old.setAreaAndCountry(model.getAreaAndCountry());
				old.setPublishedYear(model.getPublishedYear());
				old.setIntroduction(model.getIntroduction());
				if(model.getImage() != null && !"".equals(model.getImage().trim()))
					old.setImage(model.getImage());
				HashSet<Category> hs=new HashSet<Category>();
				hs.add(categoryLogic.getById(categoryId));
				old.setCategory(hs);
				
				old.setLecturers(lecturerLogic.lecturerNamesToHashSet(lecturerList));

				old.setDeleted(false);
				old.setStatus(true);
				
				videoLogic.update(old);
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return LOGIN;
		}
	}

	public List<VideoVOForView> getContentList() {
		return contentList;
	}

	public List<VideoVOForView> getRankList() {
		return rankList;
	}
	
	public void setVideoLogic(VideoLogic VideoLogic) {
		this.videoLogic = VideoLogic;
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
						.getAllAreaAndCountryListForCertainResource(MultimediaType.Video.name());
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
						.getAllCategoryListForCertainResource(MultimediaType.Video.name());
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
				publishedYearList = videoLogic.getPublishedYearList();
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

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}

	public List<Lecturer> getLecturerList() {
		return lecturerList;
	}

	public void setLecturerList(List<Lecturer> lecturerList) {
		this.lecturerList = lecturerList;
	}

	public void setLecturerLogic(LecturerLogic lecturerLogic) {
		this.lecturerLogic = lecturerLogic;
	}

	public void setUserLogic(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Video getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
