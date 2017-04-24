package com.niit.blogbackend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USERDETAILS",schema="COLB_DB")
@Component
public class User extends BaseDomain {
	@Id
private String id;
private String name;
private String password;
private String address;
@Email
private String email;
private String mobile;
private char status;
private String reason;
private String role;
private char is_online;
@JsonIgnore
@OneToMany(mappedBy="request_sender", fetch=FetchType.EAGER)
private List<Friend> friends;

public List<Friend> getFriends() {
	return friends;
}
public void setFriends(List<Friend> friends) {
	this.friends = friends;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}

public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public char getIs_online() {
	return is_online;
}
public void setIs_online(char is_online) {
	this.is_online = is_online;
}

}
