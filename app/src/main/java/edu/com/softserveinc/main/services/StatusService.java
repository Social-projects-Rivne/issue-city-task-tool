package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.models.StatusModel;

public interface StatusService {

	public void addStatus(StatusModel status);
	
	public StatusModel getStatusByName(String name);
	
	public List<StatusModel> loadStatusList();

}
