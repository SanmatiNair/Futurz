package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.ForumComments;


public interface IForumCommentDao 
{
	boolean CreateAndUpdateForum(ForumComments forumcomment);
	boolean DeleteForum(int id);
	List<ForumComments> SelectAllForum(int id);
	ForumComments SelectOneForum(int id);
}
