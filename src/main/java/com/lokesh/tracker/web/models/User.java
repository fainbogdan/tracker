package com.lokesh.tracker.web.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lokesh.tracker.web.validations.Email;
import com.lokesh.tracker.web.validations.PasswordMatch;

@Entity
@Table(name="users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
@PasswordMatch(password = "password", repassword = "repassword",message = "The password fields must match")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Size(min=5,max=15)
	@Column(name="username", unique=true)
	private String username;
	
	@NotBlank
	private String first_name;
	@NotBlank
	private String last_name;
	@NotBlank
	@JsonIgnore
	private String password;
	
	@NotBlank
	@Transient
	@JsonIgnore
	private String repassword;
	
	@Email(message="use company email address")
	private String email;
	@NotBlank
	private String phone;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;
	
	@OneToMany(mappedBy="creator")
	@JsonIgnore
	private Collection<Event> events=new ArrayList<Event>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@JsonIgnore
	private Collection<Role> roles=new ArrayList<Role>();
	
	private boolean enabled;
	
	private String grp;
	private String dpt;
	
	@ManyToMany(mappedBy="watchers")
	@JsonIgnore
	private Collection<Event> watchingEvents=new ArrayList<Event>();

	public User(){
		
	}
	
	public User(User user) {
		this.username=user.username;
		this.first_name=user.first_name;
		this.last_name=user.last_name;
		this.password=user.password;
		this.email=user.email;
		this.phone=user.phone;
		this.enabled=user.enabled;
		this.roles=user.roles;
		this.grp=user.grp;
		this.dpt=user.dpt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at() {
		this.created_at = new Date();
	}

	public Date getUpdated_at() {
		return updated_at;
	}
	
	public void setUpdated_at() {
		this.updated_at = new Date();
	}
	
	public Collection<Event> getEvents() {
		return events;
	}

	public void setEvents(Collection<Event> events) {
		this.events = events;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public String getDpt() {
		return dpt;
	}

	public void setDpt(String dpt) {
		this.dpt = dpt;
	}

	public Collection<Event> getWatchingEvents() {
		return watchingEvents;
	}

	public void setWatchingEvents(Collection<Event> watchingEvents) {
		this.watchingEvents = watchingEvents;
	}

	public String fullname()
	{
		return getFirst_name()+" "+getLast_name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
