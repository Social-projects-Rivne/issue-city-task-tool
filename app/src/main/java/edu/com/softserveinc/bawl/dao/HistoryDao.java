package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Illia on 10/5/2015.
 */
public interface HistoryDao  extends JpaRepository<HistoryModel, Integer> {

    String uniqueLastByDateHistories = "SELECT h.* FROM history AS h, ( " +
                    "    SELECT issue_id, MAX(id) AS ID " +
                    "    FROM history " +
                    "    GROUP BY issue_id " +
                    ") AS hMaxId " +
                    "WHERE hMaxId.id = h.id";

    String lastIssueByIssueID = "SELECT h.* FROM history AS h , ( " +
                    "    SELECT MAX(id) AS ID " +
                    "    FROM history as h2 " +
                    "    WHERE issue_id = :issueId " +
                    "    GROUP BY issue_id " +
                    ") AS hMaxId " +
                    "WHERE h.id = hMaxId.ID";

    List <HistoryModel> findByUserId (int userId);

    List <HistoryModel> findByIssueId (int issueId);

    @Query(value = uniqueLastByDateHistories, nativeQuery = true)
    List <HistoryModel> getUniqueLastByDateHistories();

    @Query(value = lastIssueByIssueID, nativeQuery = true)
    HistoryModel getLastByIssueIDHistory(@Param("issueId") int issueId);




}
