package com.niit.JobBack.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.JobBack.model.Customer;


@Repository("CustomerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sf;

	public boolean addCustomer(Customer customer) 
	{
		try {
			sf.getCurrentSession().saveOrUpdate(customer);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}
	}


	public boolean deleteCustomer(String emailId) {
		try {
			sf.getCurrentSession().delete(showcustomer(emailId));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}

	}

	public Customer showcustomer(String emailId) {
		try {
			Customer customer = (Customer) sf.getCurrentSession()
					.createQuery("from Customer where emailId= '" + emailId + "'").uniqueResult();
			return customer;
		} catch (Exception e) {
			Customer customer = null;
			System.out.println(e.getMessage());
			return customer;
		}
	}

	public List<Customer> showallcustomer() {
		try
		{
			
			ArrayList<Customer> customer=(ArrayList<Customer>)sf.getCurrentSession().createQuery("from Customer").list();
		return customer;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
}
