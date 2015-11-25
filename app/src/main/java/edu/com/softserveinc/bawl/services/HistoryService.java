package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;

import java.util.List;

public interface HistoryService {

    void addHistory(HistoryModel history);

    void editHistory(HistoryModel history);

    void deleteHistory(HistoryModel history);

    List<HistoryModel> getHistoriesByUserID(int userId);

    List<HistoryModel> getHistoriesByIssueID(int issueId);

    List<IssueModel> getLastUniqueIssues();

    IssueModel getLastIssueByIssueID(int issueId);


}
