package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;

import java.util.Collection;

public interface SubscriptionService {
	
//	public SubscriptionModel create(int issueId, String email);

	public SubscriptionModel createSubscription(int issueId, int userId);
	
	//public SubscriptionModel create(SubscriptionModel subscription);
	
	public SubscriptionModel read(int id);
	
	public void delete(int id);
	
	public Collection<SubscriptionModel> listByIssueId(int issueId);

	public ResponseDTO SendApproved (int userId, int issueId);

	//SubscriptionModel createSubscription(SubscriptionModel subscriptionModel);

}
