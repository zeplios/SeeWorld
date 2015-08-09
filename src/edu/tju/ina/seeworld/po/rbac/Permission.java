package edu.tju.ina.seeworld.po.rbac;

import java.util.HashSet;
import java.util.Set;

/**
 * Permission entity. @author Uranus
 */

public class Permission implements java.io.Serializable {

	// Fields
	private String id;
	private Resource resource;
	private Operation operation;
	private String name;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors
	/** default constructor */
	public Permission() {
	}

	/** minimal constructor */
	public Permission(Resource resource, Operation operation, String name) {
		this.resource = resource;
		this.operation = operation;
		this.name = name;
	}

	/** full constructor */
	public Permission(Resource resource, Operation operation, String name,
			Set<Role> roles) {
		this.resource = resource;
		this.operation = operation;
		this.name = name;
		this.roles = roles;
	}

	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override public String toString(){
		return name;
	}
	
	private static final long serialVersionUID = 251442152665110158L;
}