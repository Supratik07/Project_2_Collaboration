package com.niit.blogbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="c_job",schema="supratik01")
@Component
public class Job extends BaseDomain{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private Date dateTime;
	private String qualification;
	private char status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char c) {
		this.status = c;
	}
	
}
