package com.niit.JobMiddle.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.JobBack.Dao.CustomerDAO;
import com.niit.JobBack.model.Customer;
import com.niit.JobBack.model.MyError;

@RestController
@RequestMapping(value = "Login")
public class LoginController {
	@Autowired
	CustomerDAO customerdao;

	@PostMapping
	public ResponseEntity<?> InsertOrUpdateCustomer(@RequestBody Customer customer) 
	{
		Customer exisitingcustomer = customerdao.showcustomer(customer.getEmailId());
		if (exisitingcustomer == null) 
		{
			MyError error = new MyError();
			error.setErrormessage("invalid emailid pls register");
			return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
		} else {
			System.out.println(exisitingcustomer.getPassword());
			if (customer.getPassword().equals(exisitingcustomer.getPassword())) {
				return new ResponseEntity<Customer>(exisitingcustomer, HttpStatus.OK);
			} else {
				MyError error = new MyError();
				error.setErrormessage("invalid password");
				return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
			}
		}
	}

}
