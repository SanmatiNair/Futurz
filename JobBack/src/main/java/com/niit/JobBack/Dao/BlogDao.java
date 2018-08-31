package com.niit.JobBack.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.JobBack.model.Blog;

@Repository
@Transactional
public class BlogDao implements IBlogDao {
	@Autowired
	SessionFactory sf;

	public boolean CreateAndUpdateBlog(Blog blog) {
		try {
			sf.getCurrentSession().saveOrUpdate(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Blog> SelectAllBlog() {
		try {
			return sf.getCurrentSession().createQuery("from Blog where status="+true+" order by BlogId desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Blog> SelectAllInvalidBlog() {
		try {
			return sf.getCurrentSession().createQuery("from Blog where status="+false).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public Blog SelectOneBlog(int id) {

		try {
			return (Blog) sf.getCurrentSession().createQuery("from Blog where BlogId=" + id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean DeleteBlog(int id) {
		try {
			sf.getCurrentSession().delete(SelectOneBlog(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}}
}
