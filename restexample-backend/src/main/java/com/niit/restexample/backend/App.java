package com.niit.restexample.backend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.niit.restexample.backend.config.AppContextConfig;
import com.niit.restexample.backend.dao.UserDAO;
import com.niit.restexample.backend.model.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       AbstractApplicationContext context=new AnnotationConfigApplicationContext(AppContextConfig.class);
       
       UserDAO userDAO=(UserDAO)context.getBean("userDAO");
       
       // create a new User
       
       User user=new User();
       user.setUsername("Supratik");
       user.setPassword("1234");
       user.setCity("KOL");
       user.setMobile("9830319859");
       user.setRole("User");
       
       userDAO.addUser(user);
    }
}
