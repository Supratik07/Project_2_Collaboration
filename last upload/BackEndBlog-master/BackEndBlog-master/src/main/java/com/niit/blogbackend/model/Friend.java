package com.niit.blogbackend.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.stereotype.Component;


@Entity
@Table(name="C_FRIEND")
@Component
public class Friend extends BaseDomain{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="user_id")
	private String userID;
	@Column(name="friend_id")
	private String friendID;
	
	//New, accepted, rejected
	private String status;
	
	@Column(name="is_online")
	private char  isOnline;
	
	@Column(name = "LAST_SEEN_TIME")
	private Date lastSeenTime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",nullable=false,insertable=false,updatable=false)
	private User request_sender;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="friend_id",nullable=false,insertable=false,updatable=false)
	private User request_acceptor;
	
	
	public Date getLastSeenTime() {
		return lastSeenTime;
	}
	public void setLastSeenTime(Date lastSeenTime) {
		this.lastSeenTime = lastSeenTime;
	}
	public User getRequest_sender() {
		return request_sender;
	}
	public void setRequest_sender(User request_sender) {
		this.request_sender = request_sender;
	}
	public User getRequest_acceptor() {
		return request_acceptor;
	}
	public void setRequest_acceptor(User request_acceptor) {
		this.request_acceptor = request_acceptor;
	}
	public char getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFriendID() {
		return friendID;
	}
	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}


}
	

