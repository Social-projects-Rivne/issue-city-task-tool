package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.StatusDTO;
import edu.com.softserveinc.bawl.services.StatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for status management
 */
@RestController
@RequestMapping(value = "statuses")
public class StatusController {

  public static final Logger LOG = Logger.getLogger(StatusController.class);

  @Autowired
  private StatusService statusService;

  /**
   * Returns all statuses
   * @return statuses
   */
  @RequestMapping("all")
  @ResponseBody
  public ResponseEntity<List<StatusDTO>> getStatuses() {
    return new ResponseEntity(statusService.loadStatusList(), HttpStatus.OK);
  }

}
