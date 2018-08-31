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

import com.niit.JobBack.Dao.IForumCommentDao;
import com.niit.JobBack.model.ForumComments;


@RestController
@RequestMapping(value="ForumComment")
public class FourmCommentsController 
{
	@Autowired
	IForumCommentDao cforumDAO;
	
	@GetMapping("/{foid}")
	public ResponseEntity<List<ForumComments>> getAllBlogs(@PathVariable("foid") int id)
	{
		System.out.println("hi");
		List<ForumComments> blog=cforumDAO.SelectAllForum(id);
		if(blog.isEmpty())
		{
			return new ResponseEntity<List<ForumComments>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<ForumComments>>(blog,HttpStatus.OK);
		}		
	}
		
	@PostMapping
	public ResponseEntity<Void> InsertOrUpdateBlog(@RequestBody ForumComments blogcomment)
	{
		blogcomment.setPostedOn((new Date()).toString());
		if(cforumDAO.CreateAndUpdateForum(blogcomment))
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
		if(cforumDAO.DeleteForum(id))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
