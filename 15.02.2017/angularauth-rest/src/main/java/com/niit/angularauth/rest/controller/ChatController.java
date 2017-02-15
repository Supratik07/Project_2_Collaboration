package com.niit.angularauth.rest.controller;

import java.util.Date;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.niit.angularauth.backend.model.PrivateMessage;

@Controller
public class ChatController {
	
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public void sendMessage(PrivateMessage privateMessage){
		privateMessage.setTime(new Date());
		System.out.println("Calling the method sendMessage");
		System.out.println("Message: "+privateMessage.getMessage());
		System.out.println("Username: "+privateMessage.getUsername());
		System.out.println("FriendName: "+privateMessage.getFriendName());
		
		simpMessagingTemplate.convertAndSend("/queue/message"+privateMessage.getUsername(),privateMessage);
		simpMessagingTemplate.convertAndSend("/queue/message"+privateMessage.getFriendName(),privateMessage);
		
		
	}

}
