package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.SubscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
 
public interface SubscriptionDao extends JpaRepository<SubscriptionModel, Integer> {
	
	Collection<SubscriptionModel> findByIssueId(int issueId);
	
	// Collection<SubscriptionModel> findByEmail(String email);
	
	//	SubscriptionModel findByIssueIdAndEmail(int issueId, String email);

	SubscriptionModel findByIssueIdAndUserId(int issueId, int userId);
	
}