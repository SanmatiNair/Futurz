package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.Blog;
import com.niit.JobBack.model.LikeDislike;

public interface IBlogDao 
{
	boolean CreateAndUpdateBlog(Blog blog);
	boolean DeleteBlog(int id);
	List<Blog> SelectAllBlog();
	List<Blog> SelectAllInvalidBlog();
	List<Blog> MyBlogs(String email);
	Blog SelectOneBlog(int id);	
	boolean updateLikeDislike(LikeDislike ld);
	LikeDislike selectLikeDislike(int id);
}
