package com.niit.JobBack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LikeDislike 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int dummyid;
	int BlogId;
	int likecount;
	int dislikecount;
	public int getDummyid() {
		return dummyid;
	}
	public void setDummyid(int dummyid) {
		this.dummyid = dummyid;
	}
	public int getBlogId() {
		return BlogId;
	}
	public void setBlogId(int blogId) {
		BlogId = blogId;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getDislikecount() {
		return dislikecount;
	}
	public void setDislikecount(int dislikecount) {
		this.dislikecount = dislikecount;
	}
	
	
	
	
	
	
	

}
