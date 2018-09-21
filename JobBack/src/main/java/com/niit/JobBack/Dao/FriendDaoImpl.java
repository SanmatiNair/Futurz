package com.niit.JobBack.Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.JobBack.model.Customer;
import com.niit.JobBack.model.Friend;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	SessionFactory sf;
	@Autowired
	CustomerDAO custdao;

	public boolean createAndUpdateFriend(Friend friend) {
		try {
			sf.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Customer> selectAllFriend(String email) {
		ArrayList<Customer> customer = new ArrayList<Customer>();
		try {
			List<String> emaillist = sf.getCurrentSession()
					.createSQLQuery("SELECT toid FROM FRIEND where FromId='" + email
							+ "' and status='Accepted' union SELECT fromid FROM FRIEND where toid='" + email
							+ "' and status='Accepted' union SELECT toid FROM FRIEND where toid='" + email + "'")
					.list();
			Iterator<String> emailiterator = emaillist.listIterator();
			while (emailiterator.hasNext()) {

				String s = emailiterator.next();
				if (!s.equals(email))
					customer.add(custdao.showcustomer(s));
			}
			return customer;
		} catch (Exception e) {
			return customer;
		}
	}

	public List<Customer> selectAllPendingFriend(String email) {
		ArrayList<Customer> customer = new ArrayList<Customer>();
		try {
			List<String> emaillist = sf.getCurrentSession()
					.createSQLQuery("SELECT fromid FROM FRIEND where toid='" + email+ "'and status='Pending' ").list();
			Iterator<String> emailiterator = emaillist.listIterator();
			while (emailiterator.hasNext()) {
				String s = emailiterator.next();
				if (!s.equals(email))
					customer.add(custdao.showcustomer(s));
			}
			return customer;

		} catch (Exception e) {
			return customer;
		}
	}

	public List<Customer> friendSuggetion(String email) {
		List<Customer> customer = new ArrayList<Customer>();
		try {
			List<String> emaillist = sf.getCurrentSession().createSQLQuery(
					"select emailId from customer where emailid!='admin@gmail.com' minus (SELECT toid FROM FRIEND where FromId='"
							+ email + "' union SELECT fromid FROM FRIEND where toid='" + email
							+ "' union SELECT toid FROM FRIEND where toid='" + email + "')")
					.list();
			System.out.println(emaillist);
			Iterator<String> emailiterator = emaillist.listIterator();
			while (emailiterator.hasNext()) {
				String s = emailiterator.next();
				if (!s.equals(email))
					customer.add(custdao.showcustomer(s));
			}
			return customer;

		} catch (Exception e) {
			return customer;
		}
	}

	public Friend selectFriend(String ToEmail,String FromEmail) 
	{
		try
		{
			return (Friend)sf.getCurrentSession().createQuery("from Friend where toid ='"+ToEmail+"' and fromid='"+FromEmail+"'").uniqueResult();
		}
		catch (Exception e) {
			return null;
		}
	}

	public boolean deleteFriend(String ToEmail,String FromEmail) 
	{
		try
		{
			sf.getCurrentSession().delete(selectFriend(ToEmail,FromEmail));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	
	}

}
