package com.niit.JobMiddle.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.JobBack.Dao.IBlogDao;
import com.niit.JobBack.model.Blog;
import com.niit.JobBack.model.LikeDislike;

@RestController
@RequestMapping(value="Blog")
public class BlogController 
{
	@Autowired
	IBlogDao blogdao;
	
	@GetMapping
	public ResponseEntity<List<Blog>> getAllBlogs()
	{
		System.out.println("hi");
		List<Blog> blog=blogdao.SelectAllBlog();
		if(blog.isEmpty())
		{
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
		}		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Blog> getOneBlogs(@PathVariable("id") int id)
	{
		System.out.println("hi"+id);
		Blog blog=blogdao.SelectOneBlog(id);
		if(blog!=null)
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@PostMapping
	public ResponseEntity<Void> InsertOrUpdateBlog(@RequestBody Blog blog)
	{
		blog.setCreatedOn((new Date()).toString());
		if(blogdao.CreateAndUpdateBlog(blog))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> DeleteBlog(@PathVariable("id") int id)
	{
		if(blogdao.DeleteBlog(id))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/ld/{id}")
	public ResponseEntity<LikeDislike> getBlogliks(@PathVariable("id") int id)
	{
		System.out.println("hi"+id);
		LikeDislike blog=blogdao.selectLikeDislike(id);
		if(blog!=null)
		{
			return new ResponseEntity<LikeDislike>(blog,HttpStatus.OK);
		}
		else
		{
			LikeDislike blog1=new LikeDislike();
			blog1.setBlogId(id);
			return new ResponseEntity<LikeDislike>(blog1,HttpStatus.FOUND);
		}		
	}
	
	@PostMapping("/ld")
	public ResponseEntity<Void> InsertOrUpdateBlogLike(@RequestBody LikeDislike like)
	{
		if(blogdao.updateLikeDislike(like))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
