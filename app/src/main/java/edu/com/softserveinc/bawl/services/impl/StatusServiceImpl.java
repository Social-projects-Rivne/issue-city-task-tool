package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.dto.StatusDTO;
import edu.com.softserveinc.bawl.services.StatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

	@Override
	public List<StatusDTO> loadStatusList() {
		return DTOAssembler.getStatusDtos();
	}
}
