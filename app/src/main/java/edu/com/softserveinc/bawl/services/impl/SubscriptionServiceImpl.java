package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.dao.UserDao;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import org.apache.commons.codec.digest.DigestUtils;
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

	@Autowired
	private UserDao userDao;

	@Override
	public SubscriptionModel createSubscription(int issueId, int userId) {
		SubscriptionModel existantSubscription = subscriptionDao.findByIssueIdAndUserId(issueId,userId);
		if (existantSubscription != null) {
			return existantSubscription;
		}
		return subscriptionDao.saveAndFlush(new SubscriptionModel(issueId, userId));
	}

	public boolean isValidSubscription(int userId, int issueId){
			if (subscriptionDao.findByIssueIdAndUserId(issueId,userId) == null){
				return false;
			}else{
				return true;
			}
	}

	@Override
	public String getHashSubscription(int subId){

		String email = getEmailFromSubId(subId); 		// id ---> userId --> email
		int issueId = getIssueIdFromSubId(subId);

		System.out.println("## getHashSubscription ID" + subId);
		System.out.println("## getHashSubscription from hash email" + email);
		System.out.println("## getHashSubscription from hash  issueID" + issueId);

		return DigestUtils.md5Hex(email + subId + issueId);
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

	@Override
	public int getIssueIdFromSubId(int id){
		return subscriptionDao.findOne(id).getIssueId();
	}

	@Override
	public String getEmailFromSubId(int id){
		return userDao.findOne(subscriptionDao.findOne(id).getUserId()).getEmail();
	}

	@Override
	public SubscriptionModel validateSubscription (int subId ) {

		SubscriptionModel existantSubscription = subscriptionDao.findOne(subId);
		existantSubscription.setIsValid(true);

		System.out.println("## getIssueId = "+subId);
		System.out.println("## existantSubscription = "+existantSubscription);
		System.out.println("## existantSubscription = "+existantSubscription);

		return subscriptionDao.saveAndFlush(existantSubscription);
	}

	@Override
	public int getSubscriptionId(int issueId, int userId){
		return (subscriptionDao.findByIssueIdAndUserId(issueId,userId)).getId();
	}


	@Override
	public void editSubscription(SubscriptionModel subscriptionModel) {
		subscriptionDao.saveAndFlush(subscriptionModel);
	}

	@Override
	public SubscriptionModel getById(int id){
		return subscriptionDao.findOne(id);
	}
}

