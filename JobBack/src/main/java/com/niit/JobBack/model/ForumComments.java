package com.niit.JobBack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ForumComments {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int ForumCommentsId;
	int foid;
	@Column(columnDefinition="text")
	String Content;
	String PostedBy;
	String PostedOn;
	
	
	public int getForumCommentsId() {
		return ForumCommentsId;
	}
	public void setForumCommentsId(int forumCommentsId) {
		ForumCommentsId = forumCommentsId;
	}
	
	public int getFoid() {
		return foid;
	}
	public void setFoid(int foid) {
		this.foid = foid;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPostedBy() {
		return PostedBy;
	}
	public void setPostedBy(String postedBy) {
		PostedBy = postedBy;
	}
	public String getPostedOn() {
		return PostedOn;
	}
	public void setPostedOn(String postedOn) {
		PostedOn = postedOn;
	}
	

}
