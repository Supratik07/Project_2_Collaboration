package com.niit.angularauth.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB", schema="supratik01")
public class Job {
	
          @Id
          @GeneratedValue(strategy=GenerationType.SEQUENCE)
	
	      private long jobId;
          private String title;
          private String description;
          private Date dateTime;
          private String qualification;
          private String status;
		public long getJobId() {
			return jobId;
		}
		public void setJobId(long jobId) {
			this.jobId = jobId;
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
          
          
}
