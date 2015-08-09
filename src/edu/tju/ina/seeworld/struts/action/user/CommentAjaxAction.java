package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.ICommentLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.EssentialUser;
import edu.tju.ina.seeworld.vo.Pagination;

public class CommentAjaxAction extends AjaxAction {

	private static final long serialVersionUID = -7003313584247629724L;
	private int commentID;
	private String title;
	private String content;
	private String userId;
	private String userName;
	private Integer targetId;
	private Integer support;
	private Integer object;
	private String replycontext;
	private String resourceId;
	private ICommentLogic commentLogic;
	private Integer replyTo;
	private SettingLogic settingLogic;
	/**
	 * 在添加评论成功之后，返回刚刚添加的评论对象
	 */
	private JSONObject result;
	
	/**
	 * 用于后台管理中评论列表的获取
	 * @return
	 */
	public String findAllCommentsByPage() {
		page = getPage();

		try {
			if (len == null) {
				len = commentLogic.getCommentSum();
			}
			page.setLen(len);
			page.setPagelist();
			int offset = page.getStart();
			int pageSize = page.getPagesize();
			System.out.println(pageSize);
			resultList = new JSONArray();
			resultList.addAll(commentLogic.findAllCommentsByPage(offset, pageSize));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageJSON = JSONObject.fromObject(page);
		return Action.SUCCESS;
	}

	/**
	 * 返回某一资源的评论
	 * 
	 * @return
	 */
	public String findCommentsByPage() {
		page = getPage();

		try {
			if (len == null) {
				len = commentLogic.getCommentSum(targetId, resourceId);
			}
			page.setLen(len);
			page.setPagelist();
			int offset = page.getStart();
			int pageSize = page.getPagesize();
			System.out.println(pageSize);
			resultList = new JSONArray();
			resultList.addAll(commentLogic.findCommentsByPage(targetId,
					resourceId, offset, pageSize));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageJSON = JSONObject.fromObject(page);
		return Action.SUCCESS;
	}

	public String addComment() {
		try {
			EssentialUser eUser = getCurrentUser();
			User user = new User();
			user.setId(eUser.getId());
			user.setUsername(eUser.getUsername());
			user.setPhotoPath(eUser.getPhotoPath());
			result = JSONObject.fromObject(commentLogic.addComment(user,
					targetId, resourceId, title, content, replyTo));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 管理员将某评论状态修改为“该评论内容已经被删除”
	 * @return
	 */
	public String disableComment() {
		try {
			EssentialUser u = getCurrentUser();
			boolean mark = u.getRole().equals("ROLE_ADMIN");
			Comment c = commentLogic.findById(commentID);
			if (c != null) {
				mark = mark || c.getUser().getId().equals(u.getId());
				if (mark) {
					c.setContent(getText("seeworld.messages.comments.deletedcontent"));
					commentLogic.updateComment(c);
				} else {
					throw new SeeWorldException("不合法的删除操作");
				}
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}
	
	/**
	 * 用户或管理员彻底删除某评论
	 * @return
	 */
	public String deleteComment() {
		try {
			EssentialUser u = getCurrentUser();
			boolean mark = u.getRole().equals("ROLE_ADMIN");
			Comment c = commentLogic.findById(commentID);
			if (c != null) {
				mark = mark || c.getUser().getId().equals(u.getId());
				if (mark) {
					commentLogic.deleteComment(c);
				} else {
					throw new SeeWorldException("不合法的删除操作");
				}
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	

	/**
	 * 返回某个用户的评论
	 * 
	 * @return
	 */
	public String findPersonalCommentsByPage() {
		page = getPage();
		try {
			if (len == null) {
				len = commentLogic.getCommentSum(userId);
			}
			page.setLen(len);
			page.setPagelist();
			resultList = new JSONArray();
			resultList.addAll(commentLogic.viewPersonalCommentByPage(userId,
					page.getStart(), page.getPagesize()));
			pageJSON = JSONObject.fromObject(page);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	

	public String support() {
		try {
			// 若用户未登录将报错
			getCurrentUser();
			support = commentLogic.support(commentID);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String object() {
		try {
			// 若用户未登录将报错
			getCurrentUser();
			object = commentLogic.object(commentID);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	@JSON(serialize = false)
	public Pagination getPage() {
		this.page = new Pagination();
		page.setCurrentpage(getCurrentPage());
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.COMMENTS_RECOMMENDATION_PER_PAGE));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getObject() {
		return object;
	}

	public void setObject(Integer object) {
		this.object = object;
	}

	public String getReplycontext() {
		return replycontext;
	}

	public void setReplycontext(String replycontext) {
		this.replycontext = replycontext;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getReplyTo() {
		return replyTo;
	}

	public void setCommentLogic(ICommentLogic commentLogic) {
		this.commentLogic = commentLogic;
	}

	public void setReplyTo(Integer replyTo) {
		this.replyTo = replyTo;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
}