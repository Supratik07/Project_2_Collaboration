package com.niit.blogbackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbackend.model.Job;
import com.niit.blogbackend.model.JobApplication;

@Repository("JobDAO")
public class JobDAOImpl implements JobDAO{

	private static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			log.error(" Unable to connect to db");
			e.printStackTrace();
		}
	}

	@Transactional
	public List<Job> getAllOpenedJobs() {
		log.debug("Starting of method getAllOpendJobs");
		String hql = "from Job where status ='"+"V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Starting of method getAllOpendJobs");
		return query.list();
		//return null;
	}
	
	@Transactional
	public List<JobApplication> getAllNewAppliedJobs() {
		log.debug("Starting of method getAllAppliedJobs");
		String hql = "from JobApplication where status ='"+"N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		//return null;
	}

	@Transactional
	public Job getJobDetails(Long id) {
		return  (Job)sessionFactory.getCurrentSession().get(Job.class, id);
	
	}
	
	@Transactional
	public JobApplication getJobApplication(String userID, Long jobID) {
		
		log.debug("Starting of the method getJobApplication");
		String hql = "from JobApplication where userID ='"+ userID + "' and jobID='"+jobID + "'";
		log.debug("hql" + hql);
		return (JobApplication) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		
	
	}
	
	@Transactional
	public JobApplication getJobApplication(Long jobID) {
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, jobID);
	
	}

	@Transactional
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		}
	
	@Transactional
	public boolean updateJob(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		}
	
	@Transactional
	public boolean save(JobApplication jobApplied) {
		log.debug("->->Starting of the save job application");
		try {
			sessionFactory.getCurrentSession().save(jobApplied);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean save(Job job) {
		log.debug("->->Starting of the save Job");
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Transactional
	public List<Job> getMyAppliedJobs(String userID) {
		log.debug("Starting of method getMyAppliedJobs");
		String hql = "from JobApplication where userID ='"+ userID +"'"+ "' status ='"+"A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of method getMyAppliedJobs");
		return query.list();
		
	}

	

}
