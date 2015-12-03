package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.SubscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SubscriptionDao extends JpaRepository <SubscriptionModel, Integer>  {

	List<SubscriptionModel> findByIssueId(int issueId);

	SubscriptionModel findByIssueIdAndUserId(int issueId, int userId);

	Collection <SubscriptionModel> findByIssueIdAndIsValid(int issueId, int isValid);

}