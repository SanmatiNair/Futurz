package com.niit.JobBack.Dao;


import java.util.List;

import com.niit.JobBack.model.Customer;


public interface CustomerDAO {
	
	public boolean addCustomer(Customer customer);
	public boolean deleteCustomer(String emailId);
	public Customer showcustomer(String emailId);
    public List<Customer> showallcustomer();
}
