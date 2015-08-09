package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.User;

/**
 * Multimedia entity. @author Uranus
 */

public class Multimedia implements java.io.Serializable {

	// Fields
	private Integer id;

	private Resource resource;
	private User user;
	private String title;
	private String alias;
	private String publishedYear;
	private String image;

	private String introduction;
	private Timestamp addTime;
	private Integer commentsCount;
	private Integer clickCount;
	private Integer collectedCount;
	private Integer recommendedCount;
	private AreaAndCountry areaAndCountry;

	private Boolean deleted;
	/**
	 * 是否审核通过
	 */
	private boolean status = false;
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<KeyWordRelationship> keyWordRelationships = new HashSet<KeyWordRelationship>(
			0);

	// Constructors

	/** default constructor */
	public Multimedia() {
	}

	/** minimal constructor */
	public Multimedia(Resource resourceType,
			String title, String publishedYear, Timestamp addTime) {
		this.resource = resourceType;

		this.title = title;
		this.publishedYear = publishedYear;

		this.addTime = addTime;
		this.deleted = false;
		this.status = false;
	}

	/** full constructor */
	public Multimedia(Resource resourceType,
			String title, String alias, String publishedYear, String image,
			String introduction, Timestamp addTime, Integer clickCount, Integer commentsCount, 
			Integer collectedCount, AreaAndCountry areaAndCountry,
			Boolean deleted, Boolean status) {
		this.resource = resourceType;

		this.title = title;
		this.alias = alias;
		this.publishedYear = publishedYear;
		this.image = image;
		this.areaAndCountry = areaAndCountry;
		this.introduction = introduction;

		this.addTime = addTime;
		this.commentsCount = commentsCount;
		this.clickCount = clickCount;
		this.collectedCount = collectedCount;
		this.deleted = deleted;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPublishedYear() {
		return this.publishedYear;
	}

	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commendsCount) {
		this.commentsCount = commendsCount;
	}

	public Integer getClickCount() {
		if (this.clickCount == null) {
			this.clickCount = 0;
		}
		return this.clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getCollectedCount() {
		if (this.collectedCount == null) {
			this.collectedCount = 0;
		}
		return this.collectedCount;
	}

	public void setCollectedCount(Integer collectedCount) {
		this.collectedCount = collectedCount;
	}

	public Integer getRecommendedCount() {
		if (this.recommendedCount == null) {
			this.recommendedCount = 0;
		}
		return recommendedCount;
	}

	public void setRecommendedCount(Integer recommendedCount) {
		this.recommendedCount = recommendedCount;
	}

	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<KeyWordRelationship> getKeyWordRelationships() {
		return this.keyWordRelationships;
	}

	public void setKeyWordRelationships(
			Set<KeyWordRelationship> keyWordRelationShips) {
		this.keyWordRelationships = keyWordRelationShips;
	}

	public AreaAndCountry getAreaAndCountry() {
		return areaAndCountry;
	}

	public void setAreaAndCountry(AreaAndCountry areaAndCountry) {
		this.areaAndCountry = areaAndCountry;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String toString() {
		/**
		 * if this return null, freemarker will throw an exception, 
		 * this sentence is copied from Internet:
		 * Value stack had an object with overridden toString method that returned null.
		 */
		return (this.title != null) ? this.title : "";
	}

	private static final long serialVersionUID = -5551629115837960600L;
}