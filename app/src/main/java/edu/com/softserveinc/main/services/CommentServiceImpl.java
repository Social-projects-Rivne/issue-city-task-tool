package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.CommentService;
import edu.com.softserveinc.main.models.CommentModel;

public class CommentServiceImpl implements CommentService {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addComment(CommentModel comment) {
		new DaoImpl().addInDB(comment);
	}

	@Override
	public void deleteComment(CommentModel comment) {
		new DaoImpl().deleteFromDB(comment);
	}

	@Override
	public void editComment(CommentModel comment) {
		new DaoImpl().editInDB(comment);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getCommentsByIssueId(int issueId) {
		return sessionFactory.getCurrentSession().createQuery("FROM CommentModel WHERE issueId='"
			+ issueId+"'").list();
	}
}
