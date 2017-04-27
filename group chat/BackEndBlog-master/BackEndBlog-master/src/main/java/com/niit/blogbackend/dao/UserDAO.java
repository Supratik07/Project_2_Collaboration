package com.niit.blogbackend.dao;

import java.util.List;

import com.niit.blogbackend.model.User;

public interface UserDAO {
	public List<User> getAll();

	public List<User> notMyFriends(String userID);

	public User getById(String id);

	public User authenticate(String id, String password);

	public boolean save(User user);

	public boolean update(User user);

	/*public void setOnline(String userID);

	public void setOffLine(String userID);*/
}
