package edu.com.softserveinc.main.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.models.StatusModel;
import edu.com.softserveinc.main.services.StatusService;
import edu.com.softserveinc.main.dao.StatusDao;

public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusDao statusDao; 
	
	@Override
	public void addStatus(StatusModel status) {
		statusDao.saveAndFlush(status);
	}
	
	public StatusModel getStatusByName(String name){
		return statusDao.findByName(name);
	}
	
	@Override
	public List<StatusModel> loadStatusList() {
		return statusDao.findAll();
	}
}
