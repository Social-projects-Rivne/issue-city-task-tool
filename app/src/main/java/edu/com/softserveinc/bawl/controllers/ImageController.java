package edu.com.softserveinc.bawl.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.log4j.Logger;

@Controller
public class ImageController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(ImageController.class);
	
	@RequestMapping(value = "crop-image", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> crop(@RequestBody Map<String, String> request) {
		
		BufferedImage srcImg = null;
		File srcFile = new File("**resources/img/avatar.png");
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
		
		return new HashMap<String, String>();
	}
	
}
