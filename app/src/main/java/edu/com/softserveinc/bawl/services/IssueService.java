package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import java.util.List;

import java.util.Optional;

public interface IssueService {

	IssueModel addIssue(String name, String description, String mapPointer, String attachments, String category,
			int priorityId);

	IssueModel addProblem(IssueModel problem, int userId);

	void editProblem(IssueModel problem, int userId);

	void deleteProblem(IssueModel problem, int userId);
	
	void deleteProblem(int issueId, int userId);

	void onCategoryDelete(int categoryId, CategoryModel otherCategory);

	List<IssueModel> loadIssuesList() ;

	Optional<IssueModel> getById(int issueId) ;


}
