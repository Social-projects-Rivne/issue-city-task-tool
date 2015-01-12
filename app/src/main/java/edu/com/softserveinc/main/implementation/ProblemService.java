package edu.com.softserveinc.main.implementation;

import edu.com.softserveinc.main.models.ProblemModel;

/**
 * Interface needs for working with problems
 * 
 * @author nazar
 *
 */
public interface ProblemService {
	
	/**
	 * Add existing problem
	 * 
	 * @param problem
	 */
	public void addProblemm(ProblemModel problem);

	/**
	 * Edit existing problem
	 * 
	 * @param problem
	 */
	public void editProblemm(ProblemModel problem);

	/**
	 * Delete existing problem
	 * 
	 * @param problem
	 */
	public void deletteProblemm(ProblemModel problem);

}
