package com.niit.JobBack.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.JobBack.model.ForumComments;

@Repository
@Transactional
public class ForumCommentDao implements IForumCommentDao {
	@Autowired
	SessionFactory sf;

	public boolean CreateAndUpdateForum(ForumComments forumcomment) {
		try {
			sf.getCurrentSession().saveOrUpdate(forumcomment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean DeleteForum(int id) {
		try {
			sf.getCurrentSession().delete(SelectOneForum(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ForumComments> SelectAllForum(int id) {
		try {
			return sf.getCurrentSession().createQuery("from forumcomment where id="+id+" order by ForumCommentsId desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ForumComments SelectOneForum(int id) {
		try {
			return(ForumComments)sf.getCurrentSession().createQuery("from forumcomment where ForumCommentsId="+id).uniqueResult();
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
