package edu.tju.ina.seeworld.po.rbac;

import java.util.HashSet;
import java.util.Set;

/**
 * Operation entity. @author MyEclipse Persistence Tools
 */

public class Operation implements java.io.Serializable {

	private static final long serialVersionUID = -926069898952576794L;
	private Integer id;
	private String name;
	private String desc;
	private Set<Permission> permissions = new HashSet<Permission>(0);

	/** default constructor */
	public Operation() {
	}

	/** minimal constructor */
	public Operation(String name) {
		this.name = name;
	}

	/** full constructor */
	public Operation(String name, String desc, Set<Permission> permissions) {
		this.name = name;
		this.desc = desc;
		this.permissions = permissions;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return name;
	}
}