package com.niit.blogbackend.model;

public class Message {

	  private String message;
	  private int id;
	  private String name;
	  public Message() {
	    
	  }
	  
	  public Message(int id, String message,String name ) {
	    this.id = id;
	    this.message = message;
	    this.name=name;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
	}