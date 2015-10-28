package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.StatusDTO;
import edu.com.softserveinc.bawl.services.StatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StatusController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(StatusController.class);
	
	@Autowired
	private StatusService service;
	
	@RequestMapping("get-statuses")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public @ResponseBody List<StatusDTO> getStatuses() {
		return service.loadStatusList();
	}
		
}
