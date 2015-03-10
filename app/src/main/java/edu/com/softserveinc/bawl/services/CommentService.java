package edu.com.softserveinc.bawl.services;

import java.util.List;

import edu.com.softserveinc.bawl.models.CommentModel;

public interface CommentService {

	public void addComment(CommentModel comment);

	public void deleteComment(CommentModel comment);

	public void editComment(CommentModel comment);

	public List<CommentModel> getCommentsByIssueId(int issueId);
}
