package com.niit.JobBack.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.JobBack.model.Blog;
import com.niit.JobBack.model.BlogComments;

@Repository
@Transactional
public class BlogCommentDao implements IBlogCommentDao {
	@Autowired
	SessionFactory sf;

	public boolean CreateAndUpdateBlog(BlogComments blogcomment) {
		try {
			sf.getCurrentSession().saveOrUpdate(blogcomment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean DeleteBlog(int id) {
		try {
			sf.getCurrentSession().delete(SelectOneBlog(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public List<BlogComments> SelectAllBlog(int id) {
		try {
			return sf.getCurrentSession().createQuery("from BlogComments where blogid="+id+" order by BlogCommentsId desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BlogComments SelectOneBlog(int id) {
		try {
			return(BlogComments)sf.getCurrentSession().createQuery("from BlogComments where BlogCommentsId="+id).uniqueResult();
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
