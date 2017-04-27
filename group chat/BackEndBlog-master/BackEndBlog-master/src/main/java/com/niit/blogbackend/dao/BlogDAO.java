package com.niit.blogbackend.dao;

import java.util.List;

import com.niit.blogbackend.model.Blog;

public interface BlogDAO {

	public Blog get(int id);

	public List<Blog> getAllBlogs();
	
	public List<Blog> getAllNewBlogs();

	public List<Blog> getmyBlogs(String userID);

	public boolean update(Blog blog);

	public boolean save(Blog blog);
}
