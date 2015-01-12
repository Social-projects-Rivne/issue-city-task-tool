package edu.com.softserveinc.main.dao.problems;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.implementation.ProblemService;
import edu.com.softserveinc.main.models.ProblemModel;

/**
 * Class needs for working with problems
 * 
 * @author nazar
 *
 */
public class ProblemServiceImpl implements ProblemService {

	/**
	 * Add new problem
	 * @param problem
	 */
	@Override
	public void addProblemm(ProblemModel problem) {
		new DaoImpl().addInDB(problem);
		
	}
	/**
	 * Edit existing problem
	 * @param problem
	 */
	@Override
	public void editProblemm(ProblemModel problem) {
		new DaoImpl().editInDB(problem);		
	}
	/**
	 * Delete existing problem 
	 * @param problem
	 */
	@Override
	public void deletteProblemm(ProblemModel problem) {
		new DaoImpl().deleteFromDB(problem);
		
	}

}
