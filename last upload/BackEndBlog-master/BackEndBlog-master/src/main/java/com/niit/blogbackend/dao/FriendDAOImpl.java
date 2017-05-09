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

import com.niit.blogbackend.dao.FriendDAO;
import com.niit.blogbackend.model.Friend;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {

	private static final Logger log = LoggerFactory.getLogger(FriendDAOImpl.class);

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			log.error(" Unable to connect to db");
			e.printStackTrace();
		}
	}

	@Transactional
	public boolean save(Friend friend) {

		try {
			Friend friend2 = new Friend();
		    friend2.setUserID(friend.getFriendID());
			friend2.setFriendID(friend.getUserID());
			friend2.setStatus("A");
			sessionFactory.getCurrentSession().save(friend2);
			sessionFactory.getCurrentSession().update(friend);			
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	@Transactional
	public boolean addFriend(Friend friend) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	public boolean update(Friend friend) {

		try {
			log.debug("Starting of the method update");
			log.debug("user ID : " + friend.getUserID() + " Friend id :" + friend.getFriendID());
			sessionFactory.getCurrentSession().update(friend);
			log.debug("Successfully updated");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Not able to update the status");
			return false;
		}

	}


	
	/**
	 * get the friends who are already accepcted
	 */

	@Transactional
	public List<Friend> getMyFriends(String userID) {

		
		return sessionFactory.getCurrentSession().createQuery("from Friend where friendID="+ "'" + userID + "' and status ='" + "A'").list();

	

	}

	@Transactional
	public List<Friend> getNewFriendRequests(String friendID) {
		String hql = "from Friend where friendID=" + "'" + friendID + "' and status ='" + "N'";

		log.debug(hql);
		 return  sessionFactory.openSession().createQuery(hql).list();

	

	}
	
	
	@Transactional
	public List<Friend> getRequestsSendByMe(String userID) {
		String hql = "select friendID from Friend where userID=" + "'" + userID + "' and status ='" + "N'";

		log.debug(hql);
		return  sessionFactory.openSession().createQuery(hql).list();

	}


	@Transactional
	public Friend get(String userID, String friendID) {
		String hql = "from Friend where userID=" + "'" + userID + "' and friendID= '" + friendID + "'";

		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);

		return (Friend) query.uniqueResult();

	}
	@Transactional
	public Friend get(String userID)
	{
		String hql = "from Friend where userID=" + "'" + userID + "' and friendID= '" + userID + "'";

		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);

		return (Friend) query.uniqueResult();

	}
	@Transactional
	public boolean unFriend(String userID, String friendID){
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(get(userID, friendID));
			sessionFactory.getCurrentSession().delete(get(friendID, userID));
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}


	@Transactional
	public void setOnline(String friendID) {
		log.debug("Starting of the method setOnline");
		String hql = " UPDATE Friend	SET isOnline = 'Y' where friendID='" + friendID + "'";
		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the method setOnline");

	}

	@Transactional
	public void setOffLine(String friendID) {
		log.debug("Starting of the method setOffLine");
		String hql = " UPDATE Friend	SET isOnline = 'N' where friendID='" + friendID + "'";
		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the method setOffLine");

	}

}
