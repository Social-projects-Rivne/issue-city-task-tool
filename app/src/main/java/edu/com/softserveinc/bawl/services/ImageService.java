package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

  void cropImage();

  ResponseDTO loadAvatar(MultipartFile file, UserModel currentUser);

  byte[] getAvatar(String filePath) throws IOException;
}
