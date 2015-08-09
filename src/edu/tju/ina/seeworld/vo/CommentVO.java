package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.util.DateUtil;

public class CommentVO {
	private int commentID;
	private String title;
	private String content;
	private String addTime;
	private SimpleUserVO user;
	private Integer targetId;
	private String targetName;//非数据库映射字段
	private Integer support;
	private Integer object;
	private String replycontext;
	private String resourceId;
	private String resourceName;//非数据库映射字段

	public CommentVO(Comment comment) {
		this.setAddTime(DateUtil.getFormattedDateString(comment.getAddTime())
				.substring(0, 19));
		this.setTitle(comment.getTitle());
		this.setCommentID(comment.getId());
		this.setContent(comment.getContent());
		this.setSupport(comment.getSupport());
		this.setObject(comment.getObject());
		this.setResourceId(comment.getResource().getId());
		this.setResourceName(comment.getResource().getName());
		this.setTargetId(comment.getTargetID());
		this.setUser(new SimpleUserVO(comment.getUser()));
		if (comment.getReplyTo() != null)
			replycontext = findReply(comment.getReplyTo())
					+ comment.getContent();
		else
			replycontext = comment.getContent();
	}

	private String findReply(Comment comment) {
		String context;
		if (comment.getReplyTo() != null) {
			context = "<div class='inner_wrap'>"
					+ findReply(comment.getReplyTo())
					+ comment.getUser().getUsername()
					+ ":("
					+ DateUtil.getFormattedDateString(comment.getAddTime())
							.substring(0, 19) + ")<br>&nbsp;&nbsp;"
					+ comment.getContent() + "</div>";
		} else {
			context = "<div class='inner_wrap'>"
					+ comment.getUser().getUsername()
					+ ":("
					+ DateUtil.getFormattedDateString(comment.getAddTime())
							.substring(0, 19) + ")<br>&nbsp;&nbsp;"
					+ comment.getContent() + "</div>";
		}
		return context;
	}

	public CommentVO() {

	}

	public int getCommentID() {
		return commentID;
	}

	public SimpleUserVO getUser() {
		return user;
	}

	public void setUser(SimpleUserVO user) {
		this.user = user;
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

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
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

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
