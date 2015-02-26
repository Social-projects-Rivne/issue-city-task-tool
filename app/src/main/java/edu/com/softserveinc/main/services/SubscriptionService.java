package edu.com.softserveinc.main.services;

import java.util.Collection;

import edu.com.softserveinc.main.models.SubscriptionModel;

public interface SubscriptionService {
	
	public SubscriptionModel create(int issueId, String email);
	
	public SubscriptionModel create(SubscriptionModel subscription);
	
	public void delete(int issueId, String email);
	
	public void delete(String email);
	
	public Collection<SubscriptionModel> listByIssueId(int issueId);
	

}
