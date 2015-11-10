package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    public static final Logger LOG = Logger.getLogger(SubscriptionServiceImpl.class);

	@Autowired
	private SubscriptionDao subscriptionDao;
	
	@Override
	public SubscriptionModel create(int issueId, String email) {
		SubscriptionModel existantSubscription = subscriptionDao.findByIssueIdAndEmail(issueId, email);
		if (existantSubscription != null) {
			return existantSubscription;
		}
		return subscriptionDao.saveAndFlush(new SubscriptionModel(issueId, email));
	}
	
	@Override
	public SubscriptionModel create(SubscriptionModel sub) {
		return subscriptionDao.saveAndFlush(sub);
	}

	@Override
	public SubscriptionModel read(int id) {
		return subscriptionDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		subscriptionDao.delete(id);
	}

	@Override
	public Collection <SubscriptionModel> listByIssueId(int issueId) {
		return subscriptionDao.findByIssueId(issueId);
	}
}
