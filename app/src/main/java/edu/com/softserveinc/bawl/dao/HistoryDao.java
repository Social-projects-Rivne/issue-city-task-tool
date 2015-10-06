package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Illia on 10/5/2015.
 */
public interface HistoryDao  extends JpaRepository<HistoryModel, Integer> {
}
