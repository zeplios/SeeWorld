package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import edu.tju.ina.seeworld.po.rbac.Role;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.vo.EssentialUser;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User{

	// Fields

	private String id;
	private Role role;
	private Specialty specialty;
	private Academy academy;
	private String username;
	private String password;
	private String realName;
	private String photoPath;
	private Timestamp addTime;
	private String email;
	private String uid;
	private Timestamp lastLoginTime;
	private int loginTimes;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Video> videos = new HashSet<Video>(0);
	/**
	 * 收到的留言
	 */
	private Set<Message> messagesReceived = new HashSet<Message>(0);
	/**
	 * 发出的留言
	 */
	private Set<Message> messagesSent = new HashSet<Message>(0);
	private Set<Movie> movies = new HashSet<Movie>(0);

	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Friend> friendsTheOther = new HashSet<Friend>(0);
	private Set<Friend> friendsOffer = new HashSet<Friend>(0);
	/**
	 * 加自己为好友的请求
	 */
	private Set<Request> friendRequestReceived = new HashSet<Request>(0);
	/**
	 * 自己发出的加别人为好友的请求
	 */
	private Set<Request> friendRequestSent = new HashSet<Request>(0);
	private Boolean enabled = true;
	private Set<InvitationCode> invitationCodes = new HashSet<InvitationCode>(0);
	/**
	 * 自己推荐给别人的
	 */
	private Set<Recommendation> recommendationsSent = new HashSet<Recommendation>(
			0);
	/**
	 * 别人推荐给自己的
	 */
	private Set<Recommendation> recommendationsReceived = new HashSet<Recommendation>(
			0);

	/**
	 * 用户状态
	 */
	private Set<Status> status = new HashSet<Status>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	public User(EssentialUser eUser) {
		this.username = eUser.getUsername();
		this.uid = eUser.getUid();
		this.id=eUser.getId();
	}
	
	/** minimal constructor */
	public User(String userName, String password, String realName,
			String email, String uid) {
		this.username = userName;
		this.password = password;
		this.email = email;
		this.uid = uid;
		this.realName = realName;
	}

	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Specialty getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public Academy getAcademy() {
		return this.academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public Set<Message> getMessagesReceived() {
		return this.messagesReceived;
	}

	public void setMessagesReceived(Set<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public Set<Message> getMessagesSent() {
		return this.messagesSent;
	}

	public void setMessagesSent(Set<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public Set<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	/**
	 * 返回该用用户所有好友的集合
	 * 
	 * @return HashSet<User>
	 */
	public Set<User> getAllFriends() {
		Set<User> friendsSet = new HashSet<User>(0);
		for (Friend f : getFriendsTheOther()) {
			friendsSet.add(f.getUserOffer());
		}
		for (Friend f : getFriendsOffer()) {
			friendsSet.add(f.getUserTheOther());
		}
		return friendsSet;
	}

	public Set<Friend> getFriendsTheOther() {
		return friendsTheOther;
	}

	public void setFriendsTheOther(Set<Friend> friendsTheOther) {
		this.friendsTheOther = friendsTheOther;
	}

	public Set<Friend> getFriendsOffer() {
		return friendsOffer;
	}

	public void setFriendsOffer(Set<Friend> friendsOffer) {
		this.friendsOffer = friendsOffer;
	}

	public Set<Recommendation> getRecommendationsSent() {
		return recommendationsSent;
	}

	public void setRecommendationsSent(Set<Recommendation> recommendationsSent) {
		this.recommendationsSent = recommendationsSent;
	}

	public Set<Recommendation> getRecommendationsReceived() {
		return recommendationsReceived;
	}

	public void setRecommendationsReceived(
			Set<Recommendation> recommendationsReceived) {
		this.recommendationsReceived = recommendationsReceived;
	}

	public Set<Request> getFriendRequestReceived() {
		return this.friendRequestReceived;
	}

	public void setFriendRequestReceived(Set<Request> friendRequestReceived) {
		this.friendRequestReceived = friendRequestReceived;
	}

	public Set<Request> getFriendRequestSent() {
		return this.friendRequestSent;
	}

	public void setFriendRequestSent(Set<Request> friendRequestSent) {
		this.friendRequestSent = friendRequestSent;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<InvitationCode> getInvitationCodes() {
		return invitationCodes;
	}

	public void setInvitationCodes(Set<InvitationCode> invitationCodes) {
		this.invitationCodes = invitationCodes;
	}

	public Set<Status> getStatus() {
		return status;
	}

	public void setStatus(Set<Status> status) {
		this.status = status;
	}


	@Override
	public boolean equals(Object instance) {
		if (instance instanceof User) {
			return this.id.equals(((User) instance).id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "\tUser::name:" + username + " id:" + id + " " + " email:"
				+ email;
	}

}