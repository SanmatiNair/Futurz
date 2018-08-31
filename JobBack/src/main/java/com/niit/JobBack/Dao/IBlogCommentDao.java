package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.BlogComments;


public interface IBlogCommentDao 
{
	boolean CreateAndUpdateBlog(BlogComments blogcomment);
	boolean DeleteBlog(int id);
	List<BlogComments> SelectAllBlog(int id);
	BlogComments SelectOneBlog(int id);
}
