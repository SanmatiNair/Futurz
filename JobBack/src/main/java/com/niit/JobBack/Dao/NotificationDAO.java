package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.Notification;

public interface NotificationDAO {
	
	
	boolean createandupdateNotification(Notification notification);
	boolean deleteNotification(int id);
	List<Notification> SelectAllNotification(String email);
	Notification selectNotification(int id);
	

}
