package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.dao.UserDao;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.utils.MessageBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl.getMandrillMail;

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

	@Override
	public ResponseDTO SendApproved (int userId, int issueId) {
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		ResponseDTO responseDTO = new ResponseDTO();
		String subject = "Subscription email validation";

		int hash = (subscriptionDTO.getEmail()+subscriptionDTO.getId()).hashCode();
		//TODO that's not a pattern, that's only a link. Use getBaseURL(request) instead of localhost:8085
		String messagePattern = "http://localhost:8085/"+"#subscriptions"+issueId+"/valid/"+hash;
		try {
			UserModel userModel = userDao.findOne(userId);
			getMandrillMail().sendMessage(new MessageBuilder().setPattern(messagePattern)
					.setRecipient(userModel).setSubject(subject).build());
		} catch (Exception ex){
			responseDTO.setMessage("Mail hasn't been sent");
		}
		responseDTO.setMessage("Mail has been sent");
		return responseDTO;
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
