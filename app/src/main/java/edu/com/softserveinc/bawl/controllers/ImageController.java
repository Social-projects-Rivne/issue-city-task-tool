package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.ImageService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for image manipulation
 */
@RestController
@RequestMapping(value = "image")
public class ImageController {

  public static final Logger LOG = Logger.getLogger(ImageController.class);

  public static final String SUCCESS = "Success";
  public static final String ERROR = "Error";
  public static final String SUCCESS_UPDLOAD_AVATAR = "Success. Avatar has been loaded.";
  public static final String FAILURE_UPDLOAD_AVATAR = "Failure. Avatar hasn't been loaded.";
  public static final String SUCCESS_DELETE = "Success. Avatar has been removed.";
  public static final String FAILURE_DELETE = "Failed. Avatar hasn't been removed.";

  @Autowired
  private ImageService imageService;

  @Autowired
  private UserService userService;

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

  @RequestMapping(value = "avatar", method = RequestMethod.POST)
  @ResponseBody
  public ResponseDTO uploadAvatar(@RequestParam("file") MultipartFile file) {
    ResponseDTO responseDTO;
    if (!file.isEmpty() && imageService.hasCorrectType(file.getContentType()))  {
      UserModel user = getCurrentUser();
      System.out.println(file.getContentType());
      responseDTO =  imageService.loadAvatar(file, user);
      userService.editUser(user);
    } else {
      responseDTO = new ResponseDTO();
      responseDTO.setMessage(FAILURE_UPDLOAD_AVATAR);
    }
    return responseDTO;
  }

  @RequestMapping(value="avatar", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<byte[]> getOwnAvatar() {
    byte [] bytes = null;
    UserModel user = getCurrentUser();
    try {
      String fileName = user != null ? user.getAvatar() : "";
      bytes = imageService.getUserAvatarOrDefault(fileName);
    } catch (IOException e) {
      return new ResponseEntity<>(bytes, HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(bytes, HttpStatus.OK);
  }

  @RequestMapping(value="avatar", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseDTO deleteOwnAvatar() {
    UserModel user = getCurrentUser();
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      user.setAvatar("");
      userService.editUser(user);
      responseDTO.setMessage(SUCCESS_DELETE);
    } catch (Exception ex){
      responseDTO.setMessage(FAILURE_DELETE);
    }
    return responseDTO;
  }

  @RequestMapping(value="avatar/{fileName:.+}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<byte[]> getUserAvatar(@PathVariable String fileName) {
    byte [] bytes = null;
    try {
      bytes = imageService.getUserAvatarOrDefault(fileName);
    } catch (IOException e) {
      return new ResponseEntity<>(bytes, HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(bytes, HttpStatus.OK);
  }

  private UserModel getCurrentUser() {
    final String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
    return userService.getByLogin(currentUserLoginName);
  }

}
