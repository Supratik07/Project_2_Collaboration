package com.niit.blogbackend.dao;

import java.util.List;

import com.niit.blogbackend.model.Friend;
import com.niit.blogbackend.model.User;

public interface FriendDAO {

	public boolean save(Friend friend);
	public boolean update(Friend friend);
	public List<Friend> getMyFriends(String userID);
	public List<Friend> getNewFriendRequests(String friendID);
	public List<Friend> getRequestsSendByMe(String userID);
	public Friend get(String userID, String friendID);
	public Friend get(String userID);
	public boolean unFriend(String userID, String friendID);
	public boolean addFriend(Friend friend);
	public void setOnline(String friendID);
	public void setOffLine(String friendID);
}
