package edu.tju.ina.seeworld.struts.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.context.HttpSessionContextIntegrationFilter;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.WebAuthenticationDetails;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IAcademyLogic;
import edu.tju.ina.seeworld.logic.IUserLogic;
import edu.tju.ina.seeworld.logic.InvitationCodeLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.po.user.Academy;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.PtConnector;
import edu.tju.ina.seeworld.util.StringUtils;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.EssentialUser;
import edu.tju.ina.seeworld.vo.UserVO;

public class UserAction extends BaseAction implements ModelDriven<UserVO> {
	public static final Logger log = Logger.getLogger(UserAction.class);
	private static final long serialVersionUID = -5173308098952030075L;
	protected static final String TO_PERSONAL_CENTER = "toPersonalCenter";	/*用来跳转到个人中心的result*/
	protected static final String TO_REGISTER_PAGE = "toRegisterPage";		/*用来跳转到注册页的result*/

	/**
	 * 下面的变量和5个常量控制显示个人中心的哪个子页面
	 */
	private String personalCenterType = "";
	public static final String PERSONAL_CONFIG = "personalConfig";
	public static final String PERSONAL_NEW = "personalNew";
	public static final String PERSONAL_FAVORITE = "personalFavorite";
	public static final String PERSONAL_COMMENT = "personalComment";
	public static final String PERSONAL_HISTORY = "personalHistory";

	private AuthenticationManager authenticationManager;
	private UserVO model = new UserVO();

	private IAcademyLogic academyLogic;
	private IUserLogic userLogic;
	private VOPOTransformator vOPOTransformator;
	private InvitationCodeLogic invitationCodeLogic;
	private SettingLogic settingLogic;
	private String message;

	private String invitationCode;
	private List<UserVO> list;
	private List<Academy> academyList;
	
	/**
	 * 是否需要邀请码
	 */
	private boolean requireIC;
	
	/**
	 * 交给Spring Security验证
	 */
	public String securityAction(){
		String username = model.getUserName();
		String password = model.getPassword();
		try {
			List<User> users = userLogic.findByUserName(username);
			User user = null;
			if(users != null && users.size() != 0)
				user = users.get(0);
			else{
				user = userLogic.findById(username);
				if(user != null){
					username = user.getUsername();
				}
			}
			//数据库中不存在该用户
			if(user == null){
				if(PtConnector.query(PtConnector.PT_URL, "username=" + username + "&password=" + password)){
					UserVO user_vo = new UserVO();
					user_vo.setUserName(username);
					user_vo.setPassword(StringUtils.encodeByMd5(password));
					user_vo.setAcademy_id(0);
					user_vo.setSpecialty_id(0);
					user_vo.setRealName("");
					user_vo.setEmail("");
					userLogic.userRegister(user_vo);
				}
			}
			else{
				//存在用户但密码不匹配
				if(!user.getPassword().equals(StringUtils.encodeByMd5(password))){
					if(PtConnector.query(PtConnector.PT_URL, "username=" + username + "&password=" + password)){
						user.setPassword(StringUtils.encodeByMd5(password));
						userLogic.update(user);
					}
				}
			}
			loginByAuthentication(username, password);
			model.setPassword(null);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TO_MAIN_PAGE;
	}
	
	//注册用户，z
	public String registerAction() {
		if (model != null && model.getAcademy_id() != null
				&& model.getSpecialty_id() != null) {
			try {
				userLogic.userRegister(model);
			} catch (SeeWorldException e) {
				e.printStackTrace();
			}
			if (isRequireIC()) {
				try {
					invitationCodeLogic.useInvitationCode(getInvitationCode());
				} catch (SeeWorldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loginByAuthentication(model.getUserName(), model.getPassword());
			//return ADDAVATAR;	原来的设计是注册完成后跳转到上传头像页面上传头像，暂时不做，直接去个人中心修改
			return TO_MAIN_PAGE;
		} else {
			return TO_REGISTER_PAGE;
		}
	}

	//用于在注册后将新用户自动登录，z
	public void loginByAuthentication(String userName, String password) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					userName, password);
			// generate session if one doesn't exist
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			//HttpSession session = request.getSession();

			token.setDetails(new WebAuthenticationDetails(request));

			Authentication authenticatedUser = authenticationManager
					.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);

			// Sorry for this ugly hack, I have no idea for what else can be done.
			session.put(HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY,
							SecurityContextHolder.getContext());
		} catch (BadCredentialsException e) {
			System.out.println("BadCredentialsException: "
					+ e.getLocalizedMessage());
		}
		loginAction();
	}
	
	//@Spring Security验证后的处理函数
	public String loginAction() {
		try {
			EssentialUser userDetails = getCurrentUser();
			User user = userLogic.findById(userDetails.getId());
			if (!isLoggedIn()) {
				session.put(Constant.CURRENT_USER_MODEL_IN_SESSION, new UserVO(user));
				user.setLoginTimes(user.getLoginTimes() + 1);
				user.setLastLoginTime(DateUtil.getCurrentTimestamp());
				userLogic.update(user);
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TO_MAIN_PAGE;
	}
	
	public String modifyUser() throws SeeWorldException {
		userLogic.updateInfo(model);
		return SUCCESS;
	}

	public String searchUser() throws SeeWorldException {
		list = vOPOTransformator.transferUserToUserVOForViewList(userLogic
				.searchUser(model, 0, 10));
		page = getPage();
		return SUCCESS;
	}

	public String findPassword() throws SeeWorldException {
		userLogic.findPassword(model);
		return SUCCESS;
	}

	public String deleteUser() throws SeeWorldException {
		userLogic.delUser(model.getId());
		return SUCCESS;
	}

	public String banUser() throws SeeWorldException {
		userLogic.banUser(model.getId());
		return SUCCESS;
	}

	public String allowUser() throws SeeWorldException {
		userLogic.allowUser(model.getId());
		return SUCCESS;
	}

	public String modifyRole() throws SeeWorldException {
		userLogic.modifyUserRole(model);
		return SUCCESS;
	}
	
	//跳转到注册页面的Action
	public String preRegisterAction(){
		return TO_REGISTER_PAGE;
	}
	
	//跳转到个人中心最新动态的Action
	public String personalNewAction(){
		personalCenterType = PERSONAL_NEW;
		return TO_PERSONAL_CENTER;
	}
	
	//跳转到个人中心我的收藏的Action
	public String personalFavorityAction(){
		personalCenterType = PERSONAL_FAVORITE;
		return TO_PERSONAL_CENTER;
	}
	
	//跳转到个人中心我的评论的Action
	public String personalCommentAction(){
		personalCenterType = PERSONAL_COMMENT;
		return TO_PERSONAL_CENTER;
	}
	
	//跳转到个人中心个人设置的Action
	public String personalConfigAction(){
		personalCenterType = PERSONAL_CONFIG;
		return TO_PERSONAL_CENTER;
	}
	
	//跳转到个人中心历史观看的Action
	public String personalHistoryAction(){
		personalCenterType = PERSONAL_HISTORY;
		return TO_PERSONAL_CENTER;
	}
	
	//个人设置页面修改个人资料（不包括头像密码）
	public String modifyInfoAction() {
		personalCenterType = PERSONAL_CONFIG;
		try {
			userLogic.updateInfo(model);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TO_PERSONAL_CENTER;
	}

	public boolean isRequireIC() {
		try {
			requireIC = Boolean.valueOf(
					settingLogic.getStringConfigValue(Constant.IS_INVITED_REQUIRED))
					.booleanValue();
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requireIC;
	}

	public List<Academy> getAcademyList() throws SeeWorldException {
		if (academyList == null) {
			academyList = academyLogic.showAcademyList();
		}
		return academyList;
	}
	
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public List<UserVO> getList() {
		return list;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public UserVO getModel() {
		if (model == null) {
			model = new UserVO();
		}
		return this.model;
	}
	
	@Override
	public UserVO getUserModel(){
		if (userModel == null && session != null)
			userModel = (UserVO) session.get(Constant.CURRENT_USER_MODEL_IN_SESSION);
		if (userModel == null){
			EssentialUser eu = getCurrentUser();
			if (eu != null){
				User u = null;
				try {
					u = userLogic.findById(eu.getId());
				} catch (SeeWorldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (u != null){
					userModel = new UserVO(u);
					session.put(Constant.CURRENT_USER_MODEL_IN_SESSION, userModel);
				}
			}
		}
		return userModel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRequireIC(boolean requireIC) {
		this.requireIC = requireIC;
	}

	public void setAcademyLogic(IAcademyLogic academyLogic) {
		this.academyLogic = academyLogic;
	}
	
	public void setInvitationCodeLogic(InvitationCodeLogic invitationCodeLogic) {
		this.invitationCodeLogic = invitationCodeLogic;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
	public void setUserLogic(IUserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public String getPersonalCenterType() {
		return personalCenterType;
	}
}
