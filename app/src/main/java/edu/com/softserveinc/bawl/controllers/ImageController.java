package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.services.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for image manipulation
 */
@RestController
@RequestMapping(value = "image")
public class ImageController {

  public static final Logger LOG = Logger.getLogger(ImageController.class);

  public static final String SUCCESS = "Success";
  public static final String ERROR = "Error";

  @Autowired
  private ImageService imageService;

  /**
   * Crops image
   *
   * @return operation status
   */
  @RequestMapping(value = "crop", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<ResponseDTO> crop() {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      imageService.cropImage();
      return new ResponseEntity(responseDTO.withMessage(SUCCESS), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(responseDTO.withMessage(ERROR), HttpStatus.NOT_ACCEPTABLE);

    }
  }

}
