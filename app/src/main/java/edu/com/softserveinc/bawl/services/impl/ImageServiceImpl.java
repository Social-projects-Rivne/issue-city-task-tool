package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.ImageService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static edu.com.softserveinc.bawl.controllers.ImageController.FAILURE_UPDLOAD_AVATAR;
import static edu.com.softserveinc.bawl.controllers.ImageController.SUCCESS_UPDLOAD_AVATAR;
import static org.apache.commons.io.FilenameUtils.getExtension;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{

  public static final Logger LOG = Logger.getLogger(ImageServiceImpl.class);
  public static final  String PATH_LOCAL_AVATAR = "resources/img/avatar/";
  public static final  String GET_AVATAR_URL = "image/avatar/";
  public static final String BASE_URL = System.getProperty("catalina.home") + "/";
  public static final String NO_AVATAR_PNG = "no_avatar.png";
  public static final String PATH_WEB_APP = "webapps/ROOT/";

  @Override
  public void cropImage() {
    BufferedImage srcImg = null;
    File srcFile = new File("src/main/webapp/resources/img/avatar.png");
    int x = 0;//Integer.parseInt(request.get("x"));
    int y = 0;//Integer.parseInt(request.get("y"));
    int width = 25;//Integer.parseInt(request.get("width"));
    int height = 25;//Integer.parseInt(request.get("height"));

    try {
      srcImg = ImageIO.read(srcFile);
    } catch (IOException e) {
      System.out.println("File Open Error");
    }

    BufferedImage destImg = srcImg.getSubimage(x, y, width, height);

    try {
      ImageIO.write(destImg, "jpg", srcFile);
    } catch (IOException e) {
      System.out.println("File Save Error");
    }
  }

  @Override
  public ResponseDTO loadAvatar(MultipartFile imgFile, UserModel user) {
    String fileName = DigestUtils.md5Hex(user.getEmail()) + "." + getExtension(imgFile.getOriginalFilename());
    File serverFile = createUserAvatarFile(fileName);
    ResponseDTO responseDTO = writeFile(serverFile, imgFile);
    user.setAvatar(fileName);
    return responseDTO;
  }

  @Override
  public byte[] getUserAvatarOrDefault(String filePath) throws IOException {
    String path = null;
    if ( !StringUtils.isEmpty(filePath)){
      path = BASE_URL + PATH_LOCAL_AVATAR + filePath;
    } else {
      path = BASE_URL + PATH_WEB_APP + PATH_LOCAL_AVATAR + NO_AVATAR_PNG;
    }
    return FileUtils.readFileToByteArray(FileUtils.getFile(path));
  }

  private File createUserAvatarFile(String fileName){
    File dir = new File(BASE_URL + PATH_LOCAL_AVATAR);
    if (!dir.exists())
      dir.mkdirs();
    File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
    return serverFile;
  }

  private ResponseDTO writeFile(File serverFile, MultipartFile imgFile){
    ResponseDTO response = new ResponseDTO();
    try (FileOutputStream stream = new FileOutputStream(serverFile)) {
      byte[] bytes = imgFile.getBytes();
      stream.write(bytes);
      response.setMessage(SUCCESS_UPDLOAD_AVATAR);
    } catch (Exception e) {
      response.setMessage(FAILURE_UPDLOAD_AVATAR);
    }
    return response;
  }

}
