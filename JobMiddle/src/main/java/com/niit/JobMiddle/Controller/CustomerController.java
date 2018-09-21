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

import com.niit.JobBack.Dao.CustomerDAO;
import com.niit.JobBack.model.Customer;

@RestController
@RequestMapping(value = "Customer")
public class CustomerController {
	@Autowired
	CustomerDAO Customerdao;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> Customer = Customerdao.showallcustomer();
		if (Customer.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Customer>>(Customer, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Void> InsertOrUpdateCustomer(@RequestBody Customer customer) {
		if (Customerdao.addCustomer(customer)) 
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
