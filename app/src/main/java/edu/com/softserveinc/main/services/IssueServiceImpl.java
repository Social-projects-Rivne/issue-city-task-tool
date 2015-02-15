package edu.com.softserveinc.main.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
	 * change status of issue on "deleted"
	 * @param problem
	 */
	@Override
	public void deletteProblemm(IssueModel problem) {
		problem.setStatusId(4);
		this.editProblemm(problem);
		//new DaoImpl().deleteFromDB(problem);
	}
	@Override
	public IssueModel getByID(int id) {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		IssueModel issue = (IssueModel)session.get(IssueModel.class, id);
		session.close();
		return issue;
	}
	
	//TODO: add method for get all categories !!! 

}
