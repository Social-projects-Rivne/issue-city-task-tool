package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;

import java.util.List;

public interface IssueService {
	
	void addProblem(IssueModel problem, int userId);

	void editProblem(IssueModel problem, int userId);

	void deleteProblem(IssueModel problem, int userId);
	
	void deleteProblem(int issueId, int userId);

	void onCategoryDelete(int categoryId, CategoryModel otherCategory);

	List<IssueModel> loadIssuesList() ;

	IssueModel getById(int issueId) ;


}
