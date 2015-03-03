package edu.com.softserveinc.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.StatusModel;
import edu.com.softserveinc.main.services.StatusService;

@Controller
public class StatusController {
	
	@Autowired
	private StatusService service;
	
	@RequestMapping("get-statuses")
	public @ResponseBody List<StatusModel> getStatuses() {
		return service.loadStatusList();
	}
		
}
