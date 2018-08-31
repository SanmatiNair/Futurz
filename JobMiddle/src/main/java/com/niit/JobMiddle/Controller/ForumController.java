package com.niit.JobMiddle.Controller;

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

import com.niit.JobBack.Dao.ForumDAO;
import com.niit.JobBack.model.Forum;

@RestController
@RequestMapping(value="forum")
public class ForumController {
	
	@Autowired
	ForumDAO forumDAO;
	
	@GetMapping
	public ResponseEntity<List<Forum>> getAllForums()
	{
	List<Forum> forums = forumDAO.selectAllForum();
	if(forums.isEmpty())
	{
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.NO_CONTENT);
	}
	else
	{
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Forum> getOneForum(@PathVariable("id") int id)
	{
	Forum forum = forumDAO.selectOneForum(id);
	if(forum != null)
	{
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Forum>(forum, HttpStatus.NO_CONTENT);
	}
	}
	
	@PostMapping
	public ResponseEntity<Void> insertOrUpdateForum(@RequestBody Forum forum)
	{
	if(forumDAO.createAndUpdateForum(forum)) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}else {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteForum(@PathVariable("id") int id)
	{
	if(forumDAO.deleteForum(id)) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}else {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	}

}

