package com.niit.JobBack.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.JobBack.model.Forum;

@Repository("forumDAO")
@Transactional

public class ForumDAOImpl implements ForumDAO {
	


	@Autowired
	SessionFactory sf;

	public boolean createAndUpdateForum(Forum forum) {
		try {
			sf.getCurrentSession().saveOrUpdate(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteForum(int id) {
		try {
			sf.getCurrentSession().delete(selectOneForum(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Forum> selectAllForum() {
		try {
			return sf.getCurrentSession().createQuery("from Forum order by foid desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Forum selectOneForum(int id) {
		try {
			return (Forum)sf.getCurrentSession().createQuery("from Forum where foid="+id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}

