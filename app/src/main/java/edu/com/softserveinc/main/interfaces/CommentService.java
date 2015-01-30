package edu.com.softserveinc.main.interfaces;

import java.util.List;

import edu.com.softserveinc.main.models.CommentModel;

public interface CommentService {

	public void addComment(CommentModel comment);

	public void deleteComment(CommentModel comment);

	public void editComment(CommentModel comment);

	@SuppressWarnings("rawtypes")
	public List getCommentsByIssueId(int issueId);
}
