package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;


/**
 * Request entity. @author MyEclipse Persistence Tools
 */

public class Request  implements java.io.Serializable {


    // Fields    

	private Integer id;
     private User sender;
     private User reciever;
     private Timestamp addTime;


    // Constructors

    /** default constructor */
    public Request() {
    }

    
    /** full constructor */
    public Request(User sender, User reciever, Timestamp addTime) {
        this.sender = sender;
        this.reciever = reciever;
        this.addTime = addTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return this.sender;
    }
    
    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return this.reciever;
    }
    
    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public Timestamp getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
   
	private static final long serialVersionUID = 4036046224998451066L;
}