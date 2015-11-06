package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.dto.StatusDTO;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/statuses")
public class StatusController {

	public static final Logger LOG=Logger.getLogger(StatusController.class);

	@RequestMapping("/all")
	public @ResponseBody List<StatusDTO> getStatuses() {
		return DTOAssembler.getStatusDtos();
	}
		
}
