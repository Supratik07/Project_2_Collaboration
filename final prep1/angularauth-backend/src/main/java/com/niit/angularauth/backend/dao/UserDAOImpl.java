package com.niit.angularauth.backend.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.angularauth.backend.model.User;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDAOImpl implements UserDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public void addUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		
	}

	public void updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		
	}

	public void deleteUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(user);
		
	}

	public List<User> listUsers() {
		Session session=sessionFactory.getCurrentSession();
		List<User> users=session.createQuery("from User").getResultList();
		
		return users;
	}

	public User getUserByUserId(long userId) {
		
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.createQuery("from User where userId="+userId).getSingleResult();
		
		return user;
	}

	public User getUserByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.createQuery("from User where username='"+username+"'").getSingleResult();
		System.out.println(user);
		return user;
	}

	public boolean isExistingUser(User user) {
		User u=null;
		try {
		u=getUserByUsername(user.getUsername());
		}catch(NoResultException nre){
			
		}
		if(u!=null)
		{
			return true;
		}
		else
			return false;
	}

	public boolean authenticate(String username, String password) {
		Session session=sessionFactory.getCurrentSession();
		User user;
		boolean res=false;
		try{
		user=(User)session.createQuery("from User where username='"+username+"' and password='"+password+"'").getSingleResult();
		if(user!=null)
			res=true;
		else
			res=false;
		}catch(NoResultException nre)
		{
			System.out.println("Error:"+nre.getMessage());
		}
		
		return res;
	}
	
	

}
