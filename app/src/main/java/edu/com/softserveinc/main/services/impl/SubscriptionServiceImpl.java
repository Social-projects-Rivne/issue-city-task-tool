package edu.com.softserveinc.main.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.main.dao.SubscriptionDao;
import edu.com.softserveinc.main.models.SubscriptionModel;
import edu.com.softserveinc.main.services.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionDao dao;
	
	@Override
	@Transactional
	public SubscriptionModel create(int issueId, String email) {
		SubscriptionModel existantSubscription = dao.findByIssueIdAndEmail(issueId, email);
		if (existantSubscription != null) {
			return existantSubscription;
		}
		return dao.saveAndFlush(new SubscriptionModel(issueId, email));
	}
	
	@Override
	@Transactional
	public SubscriptionModel create(SubscriptionModel sub) {
		return dao.saveAndFlush(sub);
	}

	@Override
	@Transactional
	public SubscriptionModel read(int id) {
		return dao.findOne(id);
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);

	}

	@Override
	@Transactional
	public void delete(String email) {
		dao.delete(dao.findByEmail(email));

	}

	@Override
	@Transactional
	public Collection<SubscriptionModel> listByIssueId(int issueId) {
		return dao.findByIssueId(issueId);
	}

}
