package com.niit.blogbackend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.blogbackend.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public List<User> getAll() {
		@SuppressWarnings("unchecked")
		List<User> getAll = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return getAll;
	}

	@Transactional
	public User getById(String id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}


	@Transactional
	public boolean save(User user) {
		
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	public User authenticate(String id, String password) {
		
		String hql = "from User where id = '" + id + "' and password = '" + password +"'";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) query.list();
		if(list!=null &&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Transactional
	public List<User> notMyFriends(String userID) {
		String hql="from User where id not in( SELECT friendID from "+"Friend where userID='"+userID+""+"' OR friendID='"+ userID+"')";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	/*@Transactional
	public void setOnline(String userID) {
	
		String hql = " UPDATE User	SET is_online = 'Y' where userID='" + userID + "'";
	
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();

	}

	@Transactional
	public void setOffLine(String userID) {

		String hql = " UPDATE User	SET is_online = 'N' where userID='" + userID + "'";

		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();


	}*/
}
