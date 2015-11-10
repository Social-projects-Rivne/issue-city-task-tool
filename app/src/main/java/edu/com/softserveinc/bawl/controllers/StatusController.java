package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.StatusDTO;
import edu.com.softserveinc.bawl.services.StatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/statuses")
public class StatusController {

	public static final Logger LOG=Logger.getLogger(StatusController.class);

	@Autowired
	StatusService statusService;

	@RequestMapping("/all")
	public @ResponseBody List<StatusDTO> getStatuses() {
		return statusService.loadStatusList();
	}
		
}
