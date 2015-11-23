package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.CommentDao;
import edu.com.softserveinc.bawl.models.CommentModel;
import edu.com.softserveinc.bawl.services.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  public static final Logger LOG = Logger.getLogger(CommentServiceImpl.class);

  @Autowired
  private CommentDao commentDao;


  @Override
  public void deleteComment(CommentModel comment) {
    commentDao.delete(comment);
  }

  @Override
  public CommentModel saveComment(CommentModel comment) {
    return commentDao.saveAndFlush(comment);
  }

  @Override
  public List<CommentModel> getCommentsByIssueId(int issueId) {
    return commentDao.findByIssueId(issueId);
  }
}
