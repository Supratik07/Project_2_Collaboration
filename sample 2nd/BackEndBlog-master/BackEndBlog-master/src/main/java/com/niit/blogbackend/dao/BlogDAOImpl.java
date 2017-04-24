package com.niit.blogbackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbackend.dao.BlogDAO;
import com.niit.blogbackend.model.Blog;


//  tomorrow MT2

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO{

	private static final Logger log = LoggerFactory.getLogger(BlogDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public BlogDAOImpl()
	{
		
	}

	public BlogDAOImpl(SessionFactory sessionFactory) {
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			log.error(" Unable to connect to db");
			e.printStackTrace();
		}
	}

	@Transactional
	public List<Blog> getAllBlogs() {
		log.debug("->->Starting of the method list");
		String hql = "from Blog where status = 'A'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Blog> getAllBlogs = (List<Blog>) query.list();

		return getAllBlogs;
	}

	@Transactional
	public List<Blog> getAllNewBlogs() {
		log.debug("->->Starting of the method list");
		String hql = "from Blog where status = 'N'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Blog> getAllBlogs = (List<Blog>) query.list();

		return getAllBlogs;
	}

	@Transactional
	public boolean update(Blog blog) {
		log.debug("->->Starting of the method update");
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	private Integer getMaxId()
	{
		log.debug("->->Starting of the method getMaxId");
		
		String hql = "select max(id) from Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxID = (Integer) query.uniqueResult();
		log.debug("Max id :"+maxID);
		return maxID;

	}
	
	@Transactional
	public boolean save(Blog blog) {
		log.debug("->->Starting of the method save");
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}


	@Transactional
	public void delete(int id) {
		Blog blog = new Blog();
		blog.setId(id);
		sessionFactory.getCurrentSession().delete(blog);
	}

	@Transactional
	public Blog get(int id) {
		String hql = "from Blog where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		List<Blog> list = (List<Blog>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}


	@Transactional
	public List<Blog> getmyBlogs(String userID) {

		String hql = "from Blog where userID = '"+userID+ "' and status ='" + "A'";
		Query query=	sessionFactory. getCurrentSession().createQuery(hql);
		return query.list();
	}

}
