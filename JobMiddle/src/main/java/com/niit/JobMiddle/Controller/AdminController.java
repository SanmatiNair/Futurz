package com.niit.JobMiddle.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.JobBack.Dao.IBlogDao;
import com.niit.JobBack.Dao.JobDAO;
import com.niit.JobBack.Dao.NotificationDAO;
import com.niit.JobBack.model.Blog;
import com.niit.JobBack.model.Job;
import com.niit.JobBack.model.Notification;

@RestController
@RequestMapping(value="Admin")
public class AdminController 
{
	@Autowired
	IBlogDao blogdao;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	NotificationDAO notdao;
	
	@GetMapping(value="/Blog")
	public ResponseEntity<List<Blog>> getAllBlogs()
	{
		System.out.println("hi");
		List<Blog> blog=blogdao.SelectAllInvalidBlog();
		if(blog.isEmpty())
		{
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
		}		
	}
	
	
	@DeleteMapping(value="/Blog/{id}")
	public ResponseEntity<?> deleteBlog(@PathVariable("id") int id)
	{
		Blog blog = blogdao.SelectOneBlog(id);
		
		if(blogdao.DeleteBlog(id))
		{
			Notification n=new Notification();
			n.setBlogTitle(blog.getBlogTitle());
			n.setEmailId(blog.getAuthoremail());
			n.setStatus("Rejected");
			n.setViewed(false);
			notdao.createandupdateNotification(n);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}		
	}
	
	
	
	
	@PutMapping(value="/BlogApprove/{id}")
	public ResponseEntity<?> updateBlog(@PathVariable("id") int id)
	{
		Blog blog=blogdao.SelectOneBlog(id);
		blog.setStatus(true);
		
		if(blogdao.CreateAndUpdateBlog(blog))
		{
			Notification n=new Notification();
			n.setBlogTitle(blog.getBlogTitle());
			n.setEmailId(blog.getAuthoremail());
			n.setStatus("Approved");
			n.setViewed(false);
			notdao.createandupdateNotification(n);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}		
	}
	
	
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getAlljobs()
	{
	List<Job> jobs = jobDAO.selectUnapprovedJob();
			if(jobs.isEmpty())
	{
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.NO_CONTENT);
	}
	else
	{
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	}
	
	@DeleteMapping("job/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable("id") int id)
	{
		Job b=jobDAO.selectOneJob(id);
		Notification n=new Notification();
		n.setBlogTitle(b.getJobTitle());
		n.setEmailId(b.getEmail());
		n.setStatus("Rejected");
		n.setViewed(false);
		notdao.createandupdateNotification(n);
	if(jobDAO.deleteJob(id)) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}else {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	}
	
	@PutMapping("jobapprove/{id}")
	public ResponseEntity<Void> updateJob(@PathVariable("id") int id)
	{
		Job job = jobDAO.selectOneJob(id);
		job.setStatus(true);
		
	if(jobDAO.createAndUpdateJob(job)) {
		Notification n=new Notification();
		n.setBlogTitle(job.getJobTitle());
		n.setEmailId(job.getEmail());
		n.setStatus("Approved");
		n.setViewed(false);
		notdao.createandupdateNotification(n);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}else {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	}

	
}
