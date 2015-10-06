package edu.com.softserveinc.bawl.services;

import java.util.List;

import edu.com.softserveinc.bawl.models.IssueModel;


public interface IssueService {
	
	public void addProblem(IssueModel problem, int userId);

	public void editProblem(IssueModel problem, int userId);

	public void deleteProblem(IssueModel problem, int userId);
	
	public void deleteProblem(int issueId, int userId);

	
	
}
