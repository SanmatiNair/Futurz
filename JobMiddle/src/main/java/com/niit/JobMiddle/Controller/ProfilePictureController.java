package com.niit.JobMiddle.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.JobBack.Dao.ProfilePictureDao;
import com.niit.JobBack.model.ProfilePic;

@RestController
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;

	@RequestMapping(value = "/uploadprofilepicture", method = RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession ss) {
		String email=ss.getAttribute("mailid").toString();
		System.out.println(email);
		ProfilePic profilePicture = new ProfilePic();
		profilePicture.setEmail(email);
		profilePicture.setImage(image.getBytes());
		profilePictureDao.uploadProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePic>(profilePicture, HttpStatus.OK);
	}

	@RequestMapping(value = "/getimage", method = RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@RequestParam String email) {

		ProfilePic profilePicture = profilePictureDao.getProfilePicture(email);
		if (profilePicture == null)// No image
			return null;
		else
			return profilePicture.getImage();
	}
}