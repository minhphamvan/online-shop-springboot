package com.shopspringboot.controller.other;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class ShowImageController {

	@GetMapping("/show-image")
	public void showImage(HttpServletResponse response, @RequestParam("image") String image) {

		final String UPLOAD_FOLDER = "D:\\JavaSpring\\class-spring08\\Save for project shop-springboot\\Image\\";

		File file = new File(UPLOAD_FOLDER + File.separator + image);
		
		if (file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
