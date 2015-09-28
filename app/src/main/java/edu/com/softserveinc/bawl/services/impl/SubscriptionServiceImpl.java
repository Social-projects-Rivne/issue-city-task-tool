package edu.com.softserveinc.bawl.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionDao dao;
	
	@Override
	public SubscriptionModel create(int issueId, String email) {
		SubscriptionModel existantSubscription = dao.findByIssueIdAndEmail(issueId, email);
		if (existantSubscription != null) {
			return existantSubscription;
		}
		return dao.saveAndFlush(new SubscriptionModel(issueId, email));
	}
	
	@Override
	public SubscriptionModel create(SubscriptionModel sub) {
		return dao.saveAndFlush(sub);
	}

	@Override
	public SubscriptionModel read(int id) {
		return dao.findOne(id);
	}
	
	@Override
	public void delete(int id) {
		dao.delete(id);

	}

	@Override
	public Collection<SubscriptionModel> listByIssueId(int issueId) {
		return dao.findByIssueId(issueId);
	}

}
