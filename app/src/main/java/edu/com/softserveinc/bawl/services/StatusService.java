package edu.com.softserveinc.bawl.services;

import java.util.List;

import edu.com.softserveinc.bawl.models.StatusModel;

public interface StatusService {

	public void addStatus(StatusModel status);
	
	public StatusModel getStatusByName(String name);
	
	public List<StatusModel> loadStatusList();

}
