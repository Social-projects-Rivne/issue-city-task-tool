package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.models.IssueModel;


public interface IssueService {
	
	public void addProblem(IssueModel problem);

	public void editProblem(IssueModel problem);

	public void deleteProblem(IssueModel problem);

	public IssueModel getByID(int id);
	
	public List<IssueModel> loadIsssueList();

	
	
}
