package com.niit.blogbackend.model;

import java.util.Date;

public class PrivateMessage extends Message {

	private String friendID;
	private Date time;

	public PrivateMessage() {
	}
	
	public PrivateMessage(Message original, Date time, String friendID) {
		super(original.getId(), original.getMessage(), original.getName());
		this.time = time;
		this.friendID = friendID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFriendID() {
		return friendID;
	}

	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}

}
