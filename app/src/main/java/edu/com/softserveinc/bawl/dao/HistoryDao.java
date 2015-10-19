package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Illia on 10/5/2015.
 */
public interface HistoryDao  extends JpaRepository<HistoryModel, Integer> {

    List <HistoryModel> findByUserId (int userId);
    List <HistoryModel> findByIssueId (int issueId);


}
