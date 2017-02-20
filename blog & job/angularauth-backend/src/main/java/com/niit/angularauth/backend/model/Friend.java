package com.niit.angularauth.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FRIEND", schema="supratik01")

public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long Id;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="friendId")
	private User friend;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	private String status;
	private boolean isOnline;
}
