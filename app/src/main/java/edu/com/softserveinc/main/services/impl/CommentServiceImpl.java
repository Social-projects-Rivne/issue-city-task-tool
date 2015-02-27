package edu.com.softserveinc.main.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.main.dao.CommentDao;
import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.services.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    private CommentDao commentDao;
	
	@Override
	public void addComment(CommentModel comment) {
		commentDao.saveAndFlush(comment);
	}

	@Override
	public void deleteComment(CommentModel comment) {
		commentDao.delete(comment);
	}

	@Override
	public void editComment(CommentModel comment) {
		commentDao.saveAndFlush(comment);
	}

	@Override
	public List<CommentModel> getCommentsByIssueId(int issueId) {
		return commentDao.findByIssueId(issueId);
	}
}
