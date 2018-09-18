package com.niit.JobBack.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.JobBack.model.Notification;
@Repository("notificationDAO")
@Transactional


public class NotificationDAOImpl implements NotificationDAO {
	
	@Autowired
	SessionFactory sf;

	public boolean createandupdateNotification(Notification notification) {
		
		try {
			sf.getCurrentSession().saveOrUpdate(notification);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteNotification(int id) {
		try {
			sf.getCurrentSession().delete(selectNotification(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Notification> SelectAllNotification(String email) {
		try {
			return sf.getCurrentSession().createQuery("from Notification where emailId='"+email+"' and viewed=false").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Notification selectNotification(int id) {
		try {
			return (Notification) sf.getCurrentSession().createQuery("from Notification where notificationId="+id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
}
	
}
