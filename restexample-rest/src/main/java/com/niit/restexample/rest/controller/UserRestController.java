package com.niit.restexample.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.restexample.backend.dao.UserDAO;
import com.niit.restexample.backend.model.User;

@RestController
public class UserRestController {

	@Autowired
	UserDAO userDAO;
	
	//retrieve all users
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> getAllUsers()
	{
		return userDAO.listUsers();
	}
	
	//retrive a specific user
	
	@RequestMapping(value="/user/{username}",method=RequestMethod.GET)
	public User getUser(@PathVariable("username") String username)
	{
		return userDAO.getUserByUsername(username);
	}
	
	
	//create a new user
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void createUser(@RequestBody User user)
	{
		userDAO.addUser(user);
	}
	
	
	//update an existing user
		@RequestMapping(value="/user/{username}",method=RequestMethod.PUT)
		public void updateUser(@RequestBody User user,@PathVariable("username") String username)
		{
			User u=userDAO.getUserByUsername(username);
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setCity(user.getCity());
			u.setMobile(user.getMobile());
			u.setRole(user.getRole());
			
			userDAO.updateUser(u);
		}
	
	
	
	//delete an existing user
	@RequestMapping(value="/user/{username}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable("username") String username)
	{
		userDAO.deleteUser(userDAO.getUserByUsername(username));
	}
	
	@RequestMapping(value="/user/",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		List<User> users=userDAO.ListUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
      @RequestMapping(value="/user/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)




}