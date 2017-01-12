package com.niit.restexample.backend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.restexample.backend.model.User;

@Repository("userDAO")
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

	public User getUserByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.createQuery("from User where username='"+username+"'").getSingleResult();
		
		return user;
	}

	public List<User> listUsers() {
		Session session=sessionFactory.getCurrentSession();
		List<User> users=session.createQuery("from User").getResultList();
		
		return users;
	}
	
	

}
