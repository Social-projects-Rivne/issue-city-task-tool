package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;

import java.util.List;

public interface HistoryService {

    public void addHistory(HistoryModel history);

    public void editHistory(HistoryModel history);

    public void deleteHistory(HistoryModel history);

    public List<HistoryModel> getHistoriesByUserID(int userId);

    public List<HistoryModel> getHistoriesByIssueID(int issueId);

    public List<IssueModel> getLastUniqueIssues();

    public IssueModel getLastIssueByIssueID(int issueId);


}
