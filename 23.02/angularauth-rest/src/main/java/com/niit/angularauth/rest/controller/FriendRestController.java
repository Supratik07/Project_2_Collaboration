package com.niit.angularauth.rest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.angularauth.backend.dao.FriendDAO;
import com.niit.angularauth.backend.dao.UserDAO;
import com.niit.angularauth.backend.model.Friend;
import com.niit.angularauth.backend.model.User;

@RestController
public class FriendRestController {
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpSession session;
	
	@GetMapping(value="/user/friend")
    public ResponseEntity<List<User>> listAllUsersExceptCurrentUser(HttpSession session){
		long loggedInUserId=(Long)session.getAttribute("loggedInUserId");
		List<User> users=userDAO.listUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
	}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	
}
	
	@GetMapping(value="/addFriend/{friendID}")
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") long friendID ){
		long loggedInUserID=(Long) session.getAttribute("loggedInUserId");
		Friend friend=new Friend();
		friend.setUser(userDAO.getUserByUserId(loggedInUserID));
		friend.setFriend(userDAO.getUserByUserId(friendID));
		friend.setStatus("New");
		friend.setOnline(false);
		
		friendDAO.addFriend(friend);
		return new ResponseEntity<Friend>(friend , HttpStatus.OK);
		
		
	}
	@GetMapping(value="/getMyFriendRequests/")
	public ResponseEntity<List<Friend>> getMyFriendRequests(){
		long loggedInUserID=(Long) session.getAttribute("loggedInUserID");
		List<Friend> myFriendRequests=friendDAO.listNewFriendRequests(21);
		return new ResponseEntity<List<Friend>>(myFriendRequests,HttpStatus.OK);
		
		
	}
	
	@GetMapping(value="/acceptFriend/{friendId}")
	public ResponseEntity<Friend> acceptFriendFriendRequests(@PathVariable("friendID") long friendID){
		long loggedInUserID=(Long) session.getAttribute("loggeInUserID");
		Friend friend=friendDAO.getFriend(loggedInUserID, friendID);
		Friend friend2=friendDAO.getFriend( friendID,loggedInUserID);
		friend2.setStatus("Accepted");
		friend.setStatus("Accepted");
		friendDAO.updateFriend(friend);
		friendDAO.updateFriend(friend2);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/rejectFriend/{friendId}")
	public ResponseEntity<Friend> rejecttFriendFriendRequests(@PathVariable("friendID") long friendID){
		long loggedInUserID=(Long) session.getAttribute("loggeInUserID");
		Friend friend=friendDAO.getFriend(21, friendID);
		friend.setStatus("Rejected");
		friendDAO.updateFriend(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@GetMapping(value="/myFriends")
	public ResponseEntity<List<Friend>> getMyFriends(){
		long loggedInUserId=(Long) session.getAttribute("loggedInUserID");
		List<Friend> myFriends=friendDAO.listMyFriends(loggedInUserId);
		if(myFriends.isEmpty()){
			return new ResponseEntity<List<Friend>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
		
	}

}
