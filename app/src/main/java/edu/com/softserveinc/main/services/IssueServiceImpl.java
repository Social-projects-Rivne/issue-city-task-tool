package edu.com.softserveinc.main.services;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.IssueService;
import edu.com.softserveinc.main.models.IssueModel;

/**
 * Class needs for working with problems
 * 
 * @author nazar
 *
 */
public class IssueServiceImpl implements IssueService {

	/**
	 * Add new problem
	 * @param problem
	 */
	@Override
	public void addProblemm(IssueModel problem) {
		new DaoImpl().addInDB(problem);
		
	}
	/**
	 * Edit existing problem
	 * @param problem
	 */
	@Override
	public void editProblemm(IssueModel problem) {
		new DaoImpl().editInDB(problem);		
	}
	/**
	 * Delete existing problem 
	 * @param problem
	 */
	@Override
	public void deletteProblemm(IssueModel problem) {
		new DaoImpl().deleteFromDB(problem);
		
	}

}
