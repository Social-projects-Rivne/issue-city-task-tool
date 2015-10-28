package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.IssueStatus;
import edu.com.softserveinc.bawl.services.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class HistoryServiceImpl implements HistoryService {

    public static final Logger LOG = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private IssueDao issueDao;

    @Override
    public void addHistory(HistoryModel history) {
        historyDao.saveAndFlush(history);
    }

    @Override
    public void editHistory(HistoryModel history) {
        historyDao.saveAndFlush(history);
    }

    @Override
    public void deleteHistory(HistoryModel history) {
        history.setStatus(IssueStatus.DELETED);
        historyDao.saveAndFlush(history);
    }

    @Override
    public List<HistoryModel> getHistoriesByUserID(final int userId) {
        return historyDao.findByUserId(userId);
    }

    @Override
    public List<HistoryModel> getHistoriesByIssueID(int issueId) {
        return historyDao.findByIssueId(issueId);
    }

    @Override
    public List<IssueModel> getLastUniqueIssues() {
        List<HistoryModel> uniqueHistories = historyDao.getUniqueLastByDateHistories();
        return getIssueModelsFromHistoryModels(uniqueHistories);
    }

    private  List<IssueModel> getIssueModelsFromHistoryModels (List<HistoryModel> histories) {
        List<IssueModel> issues = new ArrayList<>();
        histories.forEach(historyModel -> {
            issues.add(historyModel.getIssue());
        });
        return issues;
    }

   @Override
    public IssueModel getLastIssueByIssueID(int issueId) {
        HistoryModel lastAddedHistoryModel = historyDao.getLastByIssueIDHistory(issueId);
        final IssueModel issueModel = lastAddedHistoryModel.getIssue();
        if (null != issueModel) {
            issueModel.setStatus(lastAddedHistoryModel.getStatus());
        }
        return issueModel;
    }


}
