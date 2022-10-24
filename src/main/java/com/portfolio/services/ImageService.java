package com.portfolio.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Service
@Data
public class ImageService {
	@Value("${app.upload.dir:${user.home}}")
	private String upploadDir = "/upload";

	public File uploadImage(MultipartFile file) {
		String path = upploadDir + file.getName();
		Path copied = Paths.get(path);
		File image = new File(path);
		try {
			Files.copy(file.getInputStream(), copied, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return image;
	}

}
