package com.niit.blogbackend.dao;

import java.util.List;

import com.niit.blogbackend.model.Job;
import com.niit.blogbackend.model.JobApplication;


public interface JobDAO {
	public List<Job> getAllOpenedJobs();
	public Job getJobDetails(Long id);
	public JobApplication getJobApplication(String userID, Long jobID);
	public JobApplication getJobApplication(Long jobID);
	public boolean updateJob(Job job);
	public boolean updateJob(JobApplication jobApplication);
	public boolean save(JobApplication jobApplied);
	public boolean save(Job job);
	public List<Job> getMyAppliedJobs(String userID);
	public List<JobApplication> getAllNewAppliedJobs();
}
