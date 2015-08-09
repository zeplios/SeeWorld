package edu.tju.ina.seeworld.vo;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import edu.tju.ina.seeworld.po.user.User;

public class EssentialUser implements UserDetails {

	private static final long serialVersionUID = -3369448632273314162L;
	private String id;
	private String role;
	private String username;
	private String password;
	private String uid;
	private boolean banned;
	private String photoPath;

	public EssentialUser(User user) {
		this.id = user.getId();
		this.role = user.getRole().getName();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.uid = user.getUid();
		this.banned = user.isEnabled();
		this.photoPath = user.getPhotoPath();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public GrantedAuthority[] getAuthorities() {
		GrantedAuthorityImpl gai = new GrantedAuthorityImpl(this.role);
		return new GrantedAuthorityImpl[] { gai };
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
}
