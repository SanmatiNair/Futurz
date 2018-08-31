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

import com.niit.JobBack.Dao.IBlogCommentDao;
import com.niit.JobBack.Dao.IBlogDao;
import com.niit.JobBack.model.Blog;
import com.niit.JobBack.model.BlogComments;

@RestController
@RequestMapping(value="BlogComment")
public class BlogCommentsController 
{
	@Autowired
	IBlogCommentDao blogcommentsdao;
	
	@GetMapping("/{blogid}")
	public ResponseEntity<List<BlogComments>> getAllBlogs(@PathVariable("blogid") int id)
	{
		System.out.println("hi");
		List<BlogComments> blog=blogcommentsdao.SelectAllBlog(id);
		if(blog.isEmpty())
		{
			return new ResponseEntity<List<BlogComments>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<BlogComments>>(blog,HttpStatus.OK);
		}		
	}
		
	@PostMapping
	public ResponseEntity<Void> InsertOrUpdateBlog(@RequestBody BlogComments blogcomment)
	{
		blogcomment.setPostedOn((new Date()).toString());
		if(blogcommentsdao.CreateAndUpdateBlog(blogcomment))
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
		if(blogcommentsdao.DeleteBlog(id))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
