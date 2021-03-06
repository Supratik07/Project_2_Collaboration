package com.niit.angularauth.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="BLOG",schema="supratik01")
public class Blog {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)

	private long blogId;
	private String title;
	private String description;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="UserId")
	private User user;
	
	
	public long getBlogId() {
		return blogId;
	}


	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	

}
