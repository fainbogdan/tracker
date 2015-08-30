package com.tracker.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
     
    private String token;
   
    @OneToOne
    @JoinColumn(name="username")
    private User user;
	private boolean verified;
 
    public VerificationToken() {
        super();
    }
    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.setVerified(false);
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
    
}
