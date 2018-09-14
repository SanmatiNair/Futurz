package com.niit.JobBack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blog {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int BlogId;
	private String BlogTitle;
	@Column(columnDefinition="text")
	private String BlogDescription;
	private String Author;
	private String Authoremail;
	private	String CreatedOn;
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBlogId() {
		return BlogId;
	}

	public void setBlogId(int blogId) {
		BlogId = blogId;
	}

	

	public String getBlogTitle() {
		return BlogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		BlogTitle = blogTitle;
	}

	public String getBlogDescription() {
		return BlogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		BlogDescription = blogDescription;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(String createdOn) {
		CreatedOn = createdOn;
	}

	public String getAuthoremail() {
		return Authoremail;
	}

	public void setAuthoremail(String authoremail) {
		Authoremail = authoremail;
	}
	
	
	

}
