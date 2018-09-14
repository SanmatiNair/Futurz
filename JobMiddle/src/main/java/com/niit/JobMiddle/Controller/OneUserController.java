package com.niit.JobMiddle.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.JobBack.Dao.IBlogDao;
import com.niit.JobBack.model.Blog;

@RestController
@RequestMapping(value = "/mydata")
public class OneUserController {

	@Autowired
	IBlogDao blogDAO;


	@GetMapping("/blog")
	public ResponseEntity<List<Blog>> getmyBlog(@RequestParam("email") String email)
	{
		
		System.out.println("welcome"+email);

		List<Blog> b = blogDAO.MyBlogs(email);
		if (b.isEmpty()) {
			System.out.println("hi");
			return new ResponseEntity<List<Blog>>(b, HttpStatus.NO_CONTENT);

		} else {
			System.out.println("bye");

			return new ResponseEntity<List<Blog>>(b, HttpStatus.OK);
		}
	}

	@DeleteMapping("blog/{blogid}")
	public ResponseEntity<Void> deleteBlog(@PathVariable("blogid") int blogid)
	{
	if(blogDAO.DeleteBlog(blogid))
	{
		return new ResponseEntity<Void>(HttpStatus.OK);
	}else {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	}
}