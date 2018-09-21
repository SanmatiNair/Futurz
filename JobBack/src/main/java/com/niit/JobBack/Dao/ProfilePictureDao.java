package com.niit.JobBack.Dao;

import com.niit.JobBack.model.ProfilePic;

public interface ProfilePictureDao {
	void uploadProfilePicture(ProfilePic profilePicture);

	ProfilePic getProfilePicture(String email);
}
