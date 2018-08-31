package com.niit.JobBack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlogComments {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int BlogCommentsId;
	int blogid;
	@Column(columnDefinition="text")
	String Comment;
	String Authorname;
	String PostedOn;
	public int getBlogCommentsId() {
		return BlogCommentsId;
	}
	public void setBlogCommentsId(int blogCommentsId) {
		BlogCommentsId = blogCommentsId;
	}
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getAuthorname() {
		return Authorname;
	}
	public void setAuthorname(String authorname) {
		Authorname = authorname;
	}
	public String getPostedOn() {
		return PostedOn;
	}
	public void setPostedOn(String postedOn) {
		PostedOn = postedOn;
	}
	
	
		
	

}
