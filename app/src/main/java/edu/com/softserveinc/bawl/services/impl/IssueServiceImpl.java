package edu.com.softserveinc.bawl.services.impl;

import java.util.Comparator;
import java.util.List;

import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
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

	final static int STATUS_DELETED = 4;

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(IssueServiceImpl.class);

	@Autowired
	private IssueDao issueDao;

	@Autowired
	private HistoryDao historyDao;
	
	@Override
	public void addProblem(IssueModel problem, int userId) {
		issueDao.saveAndFlush(problem);
		saveToHistory(problem, userId);
	}
	
	@Override
	public void editProblem(IssueModel problem, int userId) {
		issueDao.saveAndFlush(problem);
		saveToHistory(problem, userId);

	}
	
	@Override
	public void deleteProblem(IssueModel problem, int userId) {
		saveToHistory(problem, userId);
	}
		
	@Override
	public void deleteProblem(int issueId, int userId) {
		HistoryModel historyModel = new HistoryModel();
		historyModel.setStatusId(STATUS_DELETED);
	}


	private void saveToHistory(IssueModel problem,int userId){
		HistoryModel historyModel = new HistoryModel();
		historyModel.setStatusId(problem.getStatusId());
		historyModel.setIssueId(problem.getId());
		historyModel.setUserId (userId);
		historyDao.saveAndFlush(historyModel);
	}

}
