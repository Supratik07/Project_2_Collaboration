package com.niit.blogbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="c_jobapplication",schema="supratik01")
@Component
public class JobApplication extends BaseDomain {

	@Id
	private Long jobID;
	private String UserID;
	private String Remarks;
	private char Status;
	private Date DateApplied;
	
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public Long getJobID() {
		return jobID;
	}
	public void setJobID(Long jobID2) {
		this.jobID = jobID2;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public char getStatus() {
		return Status;
	}
	public void setStatus(char c) {
		Status = c;
	}
	public Date getDateApplied() {
		return DateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		DateApplied = dateApplied;
	}
	
}
