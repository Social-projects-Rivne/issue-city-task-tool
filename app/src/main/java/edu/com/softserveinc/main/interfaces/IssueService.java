package edu.com.softserveinc.main.interfaces;

import edu.com.softserveinc.main.models.IssueModel;

/**
 * Interface needs for working with problems
 * 
 * @author nazar
 *
 */
public interface IssueService {
	
	/**
	 * Add existing problem
	 * 
	 * @param problem
	 */
	public void addProblemm(IssueModel problem);

	/**
	 * Edit existing problem
	 * 
	 * @param problem
	 */
	public void editProblemm(IssueModel problem);

	/**
	 * Delete existing problem
	 * 
	 * @param problem
	 */
	public void deleteProblemm(IssueModel problem);

	/**
	 * It gets issues by id
	 * 
	 * @param id
	 * @return IssueModel
	 */
	public IssueModel getByID(int id);

	
	
}
