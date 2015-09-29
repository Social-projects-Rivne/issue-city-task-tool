package edu.com.softserveinc.bawl.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.services.IssueService;

import org.apache.log4j.Logger;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(IssueServiceImpl.class);

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
	public void deleteProblem(int id) {
		IssueModel issue = issueDao.findOne(id);
		issue.setStatusId(4);
		issueDao.saveAndFlush(issue);
	}
		
	@Override
	public IssueModel getByID(int id) {
		return issueDao.findOne(id);
	}
	
	@Override
	public List<IssueModel> loadIsssueList() {
		return issueDao.findAll();
	}

}
