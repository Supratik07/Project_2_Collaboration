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
		/*UserDAO userDAO=(UserDAO) context.getBean("userDAO");
		User user=(User) context.getBean("user");
		
		user.setId("sou1");
		user.setName("Sourav");
		user.setEmail("souravictory@gmail.com");
		user.setPassword("sourav123");
		user.setMobile("9051249493");
		user.setReason("");
		user.setRole("ADMIN");
		user.setStatus('1');
		user.setIs_online('Y');
		user.setAddress("Birati");
		userDAO.save(user);*/
	}
}
