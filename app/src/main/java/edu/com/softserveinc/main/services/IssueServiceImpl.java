package edu.com.softserveinc.main.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import edu.com.softserveinc.main.dao.IssueDao;
import edu.com.softserveinc.main.models.IssueModel;

public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueDao issueDao; 
	
	@Override
	public void addProblem(IssueModel problem) {
		issueDao.saveAndFlush(problem);
	
	}

	
	@Override
	public void editProblem(IssueModel problem) {
		issueDao.saveAndFlush(problem);	
	}
	

	@Override
	public void deleteProblem(IssueModel problem) {
		problem.setStatusId(4);
		issueDao.saveAndFlush(problem);
	}
	
	
	@Override
	public IssueModel getByID(int id) {
		return issueDao.findOne(id);
	}
	

	public List<IssueModel> loadIsssueList() {
		return issueDao.findAll();
	}

}
