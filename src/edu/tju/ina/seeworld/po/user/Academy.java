package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Academy entity. @author Uranus
 */

public class Academy  implements java.io.Serializable {


    // Fields    
	 private Integer id;
     private String name;
     private Timestamp addTime;
     private Set<Specialty> specialties = new HashSet<Specialty>(0);
     private Set<User> users = new HashSet<User>(0);


    // Constructors

    /** default constructor */
    public Academy() {
    }

	/** minimal constructor */
    public Academy(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Academy(String name, Timestamp addTime, Set<Specialty> specialties, Set<User> users) {
        this.name = name;
        this.addTime = addTime;
        this.specialties = specialties;
        this.users = users;
    }

   
    // Property accessors

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

    public Timestamp getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Set<Specialty> getSpecialties() {
        return this.specialties;
    }
    
    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
   
	private static final long serialVersionUID = -8626890664817936905L;
}