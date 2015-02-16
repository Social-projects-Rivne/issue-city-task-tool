package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.models.CommentModel;

public interface CommentService {

	public void addComment(CommentModel comment);

	public void deleteComment(CommentModel comment);

	public void editComment(CommentModel comment);

	public List<CommentModel> getCommentsByIssueId(int issueId);
}
