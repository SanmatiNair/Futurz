package com.niit.JobBack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int friendid;
	
	private String fromid;
	
	private String toid;
	
	private String status;

	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public String getFromid() {
		return fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	public String getToid() {
		return toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
