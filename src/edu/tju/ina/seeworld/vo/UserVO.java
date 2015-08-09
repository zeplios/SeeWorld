package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.rbac.Role;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.DateUtil;

public class UserVO extends SimpleUserVO {
	private String password;
	private Integer academy_id;
	private Integer specialty_id;
	private String academy_name;
	private String specialty_name;
	private String email;
	private String uid;
	private String roleId;
	private String roleName;
	private Boolean enabled;
	private Integer loginTimes;
	private String lastLoginTime;

	public UserVO() {
	}

	public UserVO(User u) {
		super(u);
		this.uid = u.getUid();
		this.password = u.getPassword();
		this.email = u.getEmail();
		Role r = u.getRole();
		this.roleId = r.getId();
		this.roleName= r.getName();
		this.enabled = u.isEnabled();
		this.loginTimes = u.getLoginTimes();
		this.lastLoginTime = DateUtil.getFormattedDateString(
				u.getLastLoginTime()).substring(0, 10);
		this.academy_id = u.getAcademy().getId();
		this.specialty_id = u.getSpecialty().getId();
		this.academy_name = u.getAcademy().getName();
		this.specialty_name = u.getSpecialty().getName();
	}

	public String getAcademy_name() {
		return academy_name;
	}

	public void setAcademy_name(String academyName) {
		academy_name = academyName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSpecialty_name() {
		return specialty_name;
	}

	public void setSpecialty_name(String specialtyName) {
		specialty_name = specialtyName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAcademy_id() {
		return academy_id;
	}

	public void setAcademy_id(Integer academy_id) {
		this.academy_id = academy_id;
	}

	public Integer getSpecialty_id() {
		return specialty_id;
	}

	public void setSpecialty_id(Integer specialty_id) {
		this.specialty_id = specialty_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
