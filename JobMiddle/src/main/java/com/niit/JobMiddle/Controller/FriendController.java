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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.JobBack.Dao.FriendDao;
import com.niit.JobBack.model.Customer;
import com.niit.JobBack.model.Friend;

@RestController
@RequestMapping("/Friend")
public class FriendController {

	@Autowired
	FriendDao frienddao;

	@GetMapping("/suggest")
	public ResponseEntity<List<Customer>> getSuggestion(@RequestParam("email") String email) 
	{
		List<Customer> customer=frienddao.friendSuggetion(email);
		if(customer.isEmpty())
			return new ResponseEntity<List<Customer>>(customer,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<Customer>> getPending(@RequestParam("email") String email) 
	{
		List<Customer> customer=frienddao.selectAllPendingFriend(email);
		if(customer.isEmpty())
			return new ResponseEntity<List<Customer>>(customer,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getFriend(@RequestParam("email") String email) 
	{
		List<Customer> customer=frienddao.selectAllFriend(email);
		if(customer.isEmpty())
			return new ResponseEntity<List<Customer>>(customer,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Void> addFriend(@RequestBody Friend friend) 
	{
		if(frienddao.createAndUpdateFriend(friend))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/update")
	public ResponseEntity<Void> appFriend(@RequestParam("ToEmail") String toemail,@RequestParam("FromEmail") String fromemail) 
	{
		Friend myfriend=frienddao.selectFriend(toemail,fromemail);
		myfriend.setStatus("Accepted");
		if(frienddao.createAndUpdateFriend(myfriend))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/rdelete")
	public ResponseEntity<Void> delFriend(@RequestParam("ToEmail") String toemail,@RequestParam("FromEmail") String fromemail) 
	{
		
		if(frienddao.deleteFriend(toemail,fromemail))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
