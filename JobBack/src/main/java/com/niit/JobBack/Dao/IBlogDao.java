package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.Blog;

public interface IBlogDao 
{
	boolean CreateAndUpdateBlog(Blog blog);
	boolean DeleteBlog(int id);
	List<Blog> SelectAllBlog();
	List<Blog> SelectAllInvalidBlog();
	Blog SelectOneBlog(int id);	
}
