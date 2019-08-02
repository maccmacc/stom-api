package com.stom.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	//private static String UPLOAD_ROOT = "upload-dir";
	
	@Autowired
	private ResourceLoader resourceLoader;

	public Resource findFile(String filename, String path) {		
		return resourceLoader.getResource("file:"+path+"/"+filename);
	}

	public void createFile(MultipartFile file, String path) throws IOException {
		if (!file.isEmpty()) {
			if (!Paths.get(path).toFile().exists())
				Files.createDirectory(Paths.get(path));
			Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
		}		
	}

	public void deleteFile(String filename, String path) throws IOException {
		Files.deleteIfExists(Paths.get(path, filename));
	}

}
