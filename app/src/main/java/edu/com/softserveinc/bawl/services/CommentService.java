package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CommentModel;

import java.util.List;

public interface CommentService {

	void addComment(CommentModel comment);

	void deleteComment(CommentModel comment);

	void editComment(CommentModel comment);

	List<CommentModel> getCommentsByIssueId(int issueId);
}
