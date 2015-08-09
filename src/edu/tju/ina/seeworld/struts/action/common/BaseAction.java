package edu.tju.ina.seeworld.struts.action.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;

import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.EssentialUser;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.UserVO;

public class BaseAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	protected static final String TO_MAIN_PAGE = "tomainpage";			/*用来跳转到首页的result*/
	protected static final String LISTMULTIMEDIA = "listmultimedia";	/*跳到overview页*/
	protected static final String VIEWMULTIMEDIA = "viewmultimedia";	/*跳到filteredBrowser页*/
	protected static final String MOREMULTIMEDIA = "moremultimedia";	/*跳到more页*/
	protected static final String PLAYMULTIMEDIA = "playmultimedia";	/*跳到view页*/
	protected static final String LIST = "list";
	protected static final String LIST_REDIRECT = "list_redirect";
	protected static final String ADD = "add";						/*经过preAddAction跳转到后台addxxx页的result*/
	protected static final String UPDATE = "update";				/*跳转到后台更新页的result*/
	protected static final String SEARCHRESULT = "searchresult";	/*跳转到前台搜索页的result*/
	
	protected Map<String, Object> session;
	
	protected String currentUsername = null;	/*显示的用户名*/
	protected UserVO userModel;
	
	private String basePath;
	private HttpServletRequest request;
	protected Pagination page;

	public String getBasePath() {
		request = ServletActionContext.getRequest();
		basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/";
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setPage(Pagination page) {
		if (page == null) {
			this.page = new Pagination();
		} else {
			this.page = page;
		}
	}

	@JSON(serialize = false)
	public Pagination getPage() {
		if (page == null) {
			this.page = new Pagination();
		}
		return page;
	}
	
	public Boolean isLoggedIn() {
		if (getCurrentUsername() != null)
			return true;
		return false;
	}

	public String getCurrentUsername() {
		UserVO u = getUserModel();
		if (u != null)
			currentUsername = u.getUserName();
		return currentUsername;
	}
	
	/**
	 * override in UserAction
	 * maybe this is strange. In this way, BaseAction could have no logic.  
	 * @return
	 */
	public UserVO getUserModel(){
		if (userModel == null && session != null)
			userModel = (UserVO) session.get(Constant.CURRENT_USER_MODEL_IN_SESSION);
		return userModel;
	}
	
	public EssentialUser getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth == null) {
			return null;
		}
		Object obj = auth.getPrincipal();
		if (obj == null) {
			return null;
		}
		return (EssentialUser) obj;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
