package com.niit.blogbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.blogbackend.dao.BlogDAO;
import com.niit.blogbackend.dao.UserDAO;
import com.niit.blogbackend.model.Blog;
import com.niit.blogbackend.model.User;

public class AppTest {
	
	
	

	public static void main(String args[])
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.blogbackend");
		context.refresh();
		
	}
}
