package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.Job;

public interface JobDAO {
	
	

	boolean createAndUpdateJob(Job job);
	  boolean deleteJob(int id);
	  List<Job> selectAllJob();
	  List<Job> selectUnapprovedJob();
	  Job selectOneJob(int id);

}
