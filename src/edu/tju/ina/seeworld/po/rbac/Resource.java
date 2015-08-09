package edu.tju.ina.seeworld.po.rbac;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import edu.tju.ina.seeworld.po.resource.Multimedia;

/**
 * Resource entity. @author Uranus
 */

public class Resource implements java.io.Serializable {

	private String id;
	private String name;
	private Set<? extends Multimedia> multimedias = new HashSet<Multimedia>(0);
	private Set<Permission> permissions = new HashSet<Permission>(0);
	private Timestamp addTime;

	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(String id) {
		this.id = id;
	}

	/** full constructor */
	public Resource(String name, Set<? extends Multimedia> multimedias,
			Set<Permission> permissions) {
		this.name = name;
		this.multimedias = multimedias;
		this.permissions = permissions;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<? extends Multimedia> getMultimedias() {
		return this.multimedias;
	}

	public void setMultimedias(Set<? extends Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean equals(Object aObj) {
		if (aObj instanceof Resource) {
			return ((Resource) aObj).getName().equals(this.name);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return name+" "+id;
	}

	private static final long serialVersionUID = 4687790276945695028L;
}