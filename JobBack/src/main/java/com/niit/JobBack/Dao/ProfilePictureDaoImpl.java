package com.niit.JobBack.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.JobBack.model.ProfilePic;



@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {
	@Autowired
	private SessionFactory sf;

	public void uploadProfilePicture(ProfilePic profilePicture) {
		Session session = sf.getCurrentSession();
		session.saveOrUpdate(profilePicture);
	}

	public ProfilePic getProfilePicture(String email) {
		Session session = sf.getCurrentSession();
		ProfilePic profilePicture = (ProfilePic) session.get(ProfilePic.class, email);
		return profilePicture;
	}

}
