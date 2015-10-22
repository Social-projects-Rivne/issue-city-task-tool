package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.query.HistoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Illia on 10/5/2015.
 */
public interface HistoryDao  extends JpaRepository<HistoryModel, Integer> {

    /*String query = "SELECT h.* FROM history AS h, (SELECT issue_id, date, MAX(id) AS ID FROM history GROUP BY issue_id, date) AS hMaxId,(SELECT issue_id, MAX(date) AS MaxDate FROM history GROUP BY issue_id) AS hMaxDate WHERE hMaxId.issue_id = hMaxDate.issue_id AND hMaxId.date = hMaxDate.MaxDate AND h.id = hMaxId.id";
    String query2 = "SELECT h.* FROM history AS h,(\n" +
               "    SELECT issue_id, date, MAX(id) AS ID\n" +
               "    FROM history\n" +
               "    GROUP BY issue_id, date\n" +
               ") AS hMaxId,\n" +
               "(\n" +
               "    SELECT issue_id, MAX(date) AS MaxDate\n" +
               "    FROM history\n" +
               "    GROUP BY issue_id\n" +
               ") AS hMaxDate\n" +
               "WHERE hMaxId.issue_id = hMaxDate.issue_id\n" +
              "AND hMaxId.date = hMaxDate.MaxDate\n" +
              "AND h.id = hMaxId.id";*/
    List <HistoryModel> findByUserId (int userId);
    List <HistoryModel> findByIssueId (int issueId);

    @Query(value = HistoryQuery.uniqueLastByDateHistories, nativeQuery = true)
    List <HistoryModel> getUniqueLastByDateHistories();

    @Query(value = HistoryQuery.lastIssueByIssueID, nativeQuery = true)
    HistoryModel getLastByIssueIDHistory(@Param("issueId") int issueId);




}
