package edu.com.softserveinc.bawl.services;

import java.util.List;

import edu.com.softserveinc.bawl.models.IssueModel;


public interface IssueService {
	
	public void addProblem(IssueModel problem);

	public void editProblem(IssueModel problem);

	public void deleteProblem(IssueModel problem);
	
	public void deleteProblem(int id);

	public IssueModel getByID(int id);
	
	public List<IssueModel> loadIsssueList();

	
	
}
