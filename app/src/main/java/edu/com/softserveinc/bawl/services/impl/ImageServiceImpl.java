package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{

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
}
