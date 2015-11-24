package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.dao.UserDao;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.utils.MD5Util;
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
	public ResponseDTO SendApproved (int userId, int issueId) {

		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		UserServiceImpl userService = new UserServiceImpl();
		ResponseDTO responseDTO = new ResponseDTO();

		String name =  "User name";
		String subject = "Sibscription email validation";

		int hash = (subscriptionDTO.getEmail()+subscriptionDTO.getId()).hashCode();
		String messagePattern = "http://localhost:8085/"+"#subscriptions"+issueId+"/valid/"+hash;

		String email = userService.getById(issueId).getEmail();

		MandrillMailServiceImpl.getMandrillMail().simpleEmailSender(email,name,subject,messagePattern);

		responseDTO.setMessage("Mail has been sent");

		return responseDTO;
	}

	public String getHashSubscription(int subId){

		String email = ""; // ѕќиск емейла по id ---> userId --> email
		int issueId = getIssueIdFromSubId(subId);

		String Newhash = MD5Util.md5Apache(email + subId + issueId);
		return Newhash;
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
//		return subscriptionDao.findBySubId(id).getIssueId();
		return 0;
	}

}

