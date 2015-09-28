package edu.com.softserveinc.bawl.services;

import java.util.Collection;

import edu.com.softserveinc.bawl.models.SubscriptionModel;

public interface SubscriptionService {
	
	public SubscriptionModel create(int issueId, String email);
	
	public SubscriptionModel create(SubscriptionModel subscription);
	
	public SubscriptionModel read(int id);
	
	public void delete(int id);
	
	public Collection<SubscriptionModel> listByIssueId(int issueId);
	

}
