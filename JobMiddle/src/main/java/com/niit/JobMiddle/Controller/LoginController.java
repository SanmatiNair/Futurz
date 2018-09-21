package com.niit.JobMiddle.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.JobBack.Dao.CustomerDAO;
import com.niit.JobBack.Dao.NotificationDAO;
import com.niit.JobBack.model.Customer;
import com.niit.JobBack.model.MyError;
import com.niit.JobBack.model.Notification;

@RestController
@RequestMapping(value = "Login")
public class LoginController {
	@Autowired
	CustomerDAO customerdao;

	@Autowired
	NotificationDAO notdao;

	@PostMapping
	public ResponseEntity<?> InsertOrUpdateCustomer(@RequestBody Customer customer,HttpSession session) {
		Customer exisitingcustomer = customerdao.showcustomer(customer.getEmailId());
		if (exisitingcustomer == null) {
			MyError error = new MyError();
			error.setErrormessage("invalid emailid pls register");
			return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
		} else {
			System.out.println(exisitingcustomer.getPassword());
			if (customer.getPassword().equals(exisitingcustomer.getPassword())) {
				exisitingcustomer.setOnlinestatus(true);
				customerdao.addCustomer(exisitingcustomer);
				session.setAttribute("mailid",exisitingcustomer.getEmailId());
				return new ResponseEntity<Customer>(exisitingcustomer, HttpStatus.OK);
			} else {
				MyError error = new MyError();
				error.setErrormessage("invalid password");
				return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
			}
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logoutUpdateCustomer(@RequestParam("email") String email,HttpSession ss) {
		System.out.println(email);
		Customer exisitingcustomer = customerdao.showcustomer(email);
		System.out.println(exisitingcustomer.getName());
		exisitingcustomer.setOnlinestatus(false);
		if (customerdao.addCustomer(exisitingcustomer)) {
			ss.removeAttribute("mailid");
			ss.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/notify")
	public ResponseEntity<List<Notification>> getOneBlog(@RequestParam("email") String email) {
		List<Notification> n = notdao.SelectAllNotification(email);
		if (n.isEmpty()) {
			return new ResponseEntity<List<Notification>>(n, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Notification>>(n, HttpStatus.OK);
		}
	}

	@GetMapping("/notify/{id}")
	public ResponseEntity<Void> updatenotify(@PathVariable("id") int id) {
		Notification note = notdao.selectNotification(id);
		note.setViewed(true);
		if (!notdao.createandupdateNotification(note)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
