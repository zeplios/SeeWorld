package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONObject;

import org.springframework.util.Assert;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IRecommendationLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.RecommendationVO;

public class RecommendationAjaxAction extends AjaxAction implements
		ModelDriven<RecommendationVO> {
	private static final long serialVersionUID = -5867249513869721773L;
	private RecommendationVO model = new RecommendationVO();
	private IRecommendationLogic recommendationLogic;
	private Integer recommendationSum;
	private SettingLogic settingLogic;

	public void setRecommendationLogic(IRecommendationLogic recommendationLogic) {
		this.recommendationLogic = recommendationLogic;
	}

	public RecommendationVO getModel() {
		return this.model;
	}

	public String addRecommendation() {
		try {
			recommendationLogic.recommend(model);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String findRecommendedByPage() {
		page = getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.COMMENTS_RECOMMENDATION_PER_PAGE));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		page.setCurrentpage(getCurrentPage());
		try {
			String receiver_id = model.getReceiver_id();
			Assert.notNull(receiver_id);
			page.setLen(recommendationLogic.getRecommendedNum(receiver_id));
			page.setPagelist();
			pageJSON = JSONObject.fromObject(page);
			resultList = recommendationLogic.getRecommendedByPage(receiver_id,
					page.getStart(), page.getPagesize());
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String findRecommendationByPage() {
		page = getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.COMMENTS_RECOMMENDATION_PER_PAGE));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		page.setCurrentpage(getCurrentPage());
		try {
			String sender_id = model.getSender_id();
			page.setPagelist();
			resultList = recommendationLogic.getRecommendationByPage(sender_id,
					page.getStart(), page.getPagesize());
			pageJSON = JSONObject.fromObject(page);
			page.setLen(recommendationLogic.getRecommendationSum(sender_id));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public String showRecommendedSum() {
		try {
			recommendationSum = recommendationLogic.getRecommendedNum(model
					.getReceiver_id());
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public Integer getRecommendationSum() {
		return recommendationSum;
	}

	public void setRecommendationSum(Integer recommendationSum) {
		this.recommendationSum = recommendationSum;
	}

	public String initNewRecommended() {
		page = getPage();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.COMMENTS_RECOMMENDATION_PER_PAGE));
		} catch (SeeWorldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		page.setCurrentpage(getCurrentPage());

		try {
			String receiver_id = model.getReceiver_id();
			page.setLen(recommendationLogic.getNewRecommendedNum(receiver_id));
			page.setPagelist();
			resultList = recommendationLogic.initNewRecommended(receiver_id,
					page.getStart(), page.getPagesize());
			pageJSON = JSONObject.fromObject(page);

		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
}
