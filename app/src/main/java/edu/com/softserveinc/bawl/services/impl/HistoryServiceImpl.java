package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.services.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Illia on 10/4/2015.
 */
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    public static final Logger LOG=Logger.getLogger(CommentServiceImpl.class);

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
        history.setStatusId(4);
        historyDao.saveAndFlush(history);
    }

    @Override
    public List<HistoryModel> getHistoriesByUserID(final int userId) {

        List<HistoryModel> historiesDb = historyDao.findAll();
        List<HistoryModel> findHistories = new ArrayList<HistoryModel>();

        for(HistoryModel model: historiesDb){
            if (model.getUserId() == userId){
                findHistories.add(model);
            }
        }
        return  findHistories;

    }

    @Override
    public List<HistoryModel> getHistoriesByIssueID(int issueId) {
        List<HistoryModel> historiesDb = historyDao.findAll();
        List<HistoryModel> findHistories = new ArrayList<HistoryModel>();

        for(HistoryModel model: historiesDb){
            if (model.getIssueId() == issueId){
                findHistories.add(model);
            }
        }
        return  findHistories;
    }

    @Override
    public List<IssueModel> getLastUniqueIssues() {

        List<HistoryModel> uniqueHistories = new ArrayList<HistoryModel>();
        List<HistoryModel> histories =  historyDao.findAll();
        for(HistoryModel searchModel : histories){
            HistoryModel uniqueModel = searchModel;

            if ( isNewIssueId(uniqueModel, uniqueHistories)) {
                for (HistoryModel currentModel : histories) {
                    if (searchModel.getIssueId() == currentModel.getIssueId() &&
                            uniqueModel.getDate().before(currentModel.getDate())) { //check last date with current IssueId
                        uniqueModel = currentModel;
                    }
                }
                uniqueHistories.add(uniqueModel);
            }
        }

        List<IssueModel> issues = new ArrayList<IssueModel>();
        for(HistoryModel historyModel : uniqueHistories){
            IssueModel issueModel = issueDao.findOne(historyModel.getIssueId());
            issueModel.setStatusId (historyModel.getStatusId());
            issues.add(issueModel);
        }
        return issues;
    }

    private Boolean isNewIssueId (HistoryModel uniqueModel, List<HistoryModel> histories ) {

        for (HistoryModel curModel : histories){
            if(curModel.getIssueId() == uniqueModel.getIssueId()){
                return false;
            }
        }
        return true;
    }

    @Override
    public IssueModel getLastIssueByIssueID(int issueId) {

        List<HistoryModel> histories =  historyDao.findAll();
        IssueModel issueModel = null;
        HistoryModel lastAddedHistoryModel;
        if (histories.size() != 0) {

            lastAddedHistoryModel = histories.get(0);
            for (HistoryModel currentModel : histories) {
                if (currentModel.getIssueId() == issueId &&
                        lastAddedHistoryModel.getDate().before(currentModel.getDate())) { //check last date with current IssueId
                    lastAddedHistoryModel = currentModel;
                }
            }

            issueModel = issueDao.findOne(lastAddedHistoryModel.getIssueId());
            issueModel.setStatusId(lastAddedHistoryModel.getStatusId());

        }
        return issueModel;
    }


}
