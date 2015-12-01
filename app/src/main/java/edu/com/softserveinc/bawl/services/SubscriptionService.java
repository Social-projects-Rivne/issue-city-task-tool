package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.SubscriptionModel;

import java.util.Collection;

public interface SubscriptionService {

	public SubscriptionModel createSubscription(int issueId, int userId);
	
	public SubscriptionModel read(int id);
	
	public void delete(int id);
	
	public Collection<SubscriptionModel> listByIssueId(int issueId);

	public boolean isValidSubscription(int userId, int issueId);

	public int getIssueIdFromSubId(int id);

	public String getHashSubscription(int subId);

	public String getEmailFromSubId(int id);

	public SubscriptionModel validateSubscription (int subId);

	public SubscriptionModel UnSubscription (int subId);

	public int getSubscriptionId(int issueId, int userId);

	public void editSubscription(SubscriptionModel subscriptionModel);

	public SubscriptionModel getById(int id);

}
