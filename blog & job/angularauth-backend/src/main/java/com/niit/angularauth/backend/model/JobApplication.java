package com.niit.angularauth.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOBAPPLICATION",schema="supratik01")
public class JobApplication {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long jobApplicatinId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="jobId")
	private Job job;
	
	private Date dateApplied;
	private String remarks;
	private String status;
	public long getJobApplicatinId() {
		return jobApplicatinId;
	}
	public void setJobApplicatinId(long jobApplicatinId) {
		this.jobApplicatinId = jobApplicatinId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
