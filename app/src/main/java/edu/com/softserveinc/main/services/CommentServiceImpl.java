package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.models.CommentModel;

public class CommentServiceImpl implements CommentService {
	
	DaoImpl dao = new DaoImpl();
	
	@Override
	public void addComment(CommentModel comment) {
		dao.addInDB(comment);
	}

	@Override
	public void deleteComment(CommentModel comment) {
		dao.deleteFromDB(comment);
	}

	@Override
	public void editComment(CommentModel comment) {
		dao.editInDB(comment);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getCommentsByIssueId(int issueId) {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("FROM CommentModel WHERE issueId=" + issueId).list();
	}
}
