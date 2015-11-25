package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CommentModel;

import java.util.List;

public interface CommentService {

	void deleteComment(CommentModel comment);

	CommentModel saveComment(CommentModel comment);

	List<CommentModel> getCommentsByIssueId(int issueId);
}
