package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.dto.pojo.StatusDTO;

import java.util.List;

public interface StatusService {

	public List<StatusDTO> loadStatusList();

}
