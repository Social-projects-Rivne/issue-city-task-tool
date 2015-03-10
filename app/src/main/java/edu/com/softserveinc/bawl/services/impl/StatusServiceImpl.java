package edu.com.softserveinc.bawl.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.bawl.dao.StatusDao;
import edu.com.softserveinc.bawl.models.StatusModel;
import edu.com.softserveinc.bawl.services.StatusService;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusDao statusDao; 
	
	@Override
	public void addStatus(StatusModel status) {
		statusDao.saveAndFlush(status);
	}
	
	@Override
	public StatusModel getStatusByName(String name){
		return statusDao.findByName(name);
	}
	
	@Override
	public List<StatusModel> loadStatusList() {
		return statusDao.findAll();
	}
}
