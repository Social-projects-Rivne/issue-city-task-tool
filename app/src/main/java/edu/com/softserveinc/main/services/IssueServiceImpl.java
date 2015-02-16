package edu.com.softserveinc.main.services;

import java.util.List;


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

	DaoImpl dao = new DaoImpl();
	
	/**
	 * Add new problem
	 * @param problem
	 */
	@Override
	public void addProblemm(IssueModel problem) {
		dao.addInDB(problem);
		
	}
	/**
	 * Edit existing problem
	 * @param problem
	 */
	@Override
	public void editProblemm(IssueModel problem) {
		dao.editInDB(problem);	
	}
	/**
	 * change status of issue on "deleted"
	 * @param problem
	 */
	@Override
	public void deletteProblemm(IssueModel problem) {
		problem.setStatusId(4);
		this.editProblemm(problem);
		//dao.deleteFromDB(problem);
	}
	
	/**
	 * Load issue by id
	 * @param id 
	 * @return IssueModel
	 */
	@Override
	public IssueModel getByID(int id) {
		return (IssueModel) dao.getById(id, new IssueModel());
	}
	/**
	 * Load all issues from DB
	 * @return List of issues
	 */
	//add it to interface
	@SuppressWarnings("rawtypes")
	public List loadIsssueList() {
		return dao.getAll(new IssueModel());
	}
	//TODO: add method for get all categories !!! 

}
