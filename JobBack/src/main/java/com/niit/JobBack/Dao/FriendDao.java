package com.niit.JobBack.Dao;

import java.util.List;

import com.niit.JobBack.model.Customer;
import com.niit.JobBack.model.Friend;

public interface FriendDao 
{
	boolean createAndUpdateFriend(Friend friend);
	List<Customer> selectAllFriend(String email);
	List<Customer> selectAllPendingFriend(String email);
	List<Customer> friendSuggetion(String email);
	Friend selectFriend(String ToEmail,String FromEmail);
	boolean deleteFriend(String ToEmail,String FromEmail);
}
