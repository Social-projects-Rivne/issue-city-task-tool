package edu.com.softserveinc.bawl.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.SubscriptionModel;
 
public interface SubscriptionDao extends JpaRepository<SubscriptionModel, Integer> {
	
	Collection<SubscriptionModel> findByIssueId(int issueId);
	
	Collection<SubscriptionModel> findByEmail(String email);
	
	SubscriptionModel findByIssueIdAndEmail(int issueId, String email);
	
}