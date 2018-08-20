package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.Forum;

public interface ForumDAO {
	
	boolean createAndUpdateForum(Forum forum);
	boolean deleteForum(int id);
	List<Forum> selectAllForum();
	Forum selectOneForum(int id);

}
