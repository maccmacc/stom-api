package com.stom.app.ctrls;

import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stom.app.service.FileService;

@RestController
public class FileRestController {

	/*
	private static final String BASE_PATH = "/images";
	private static final String FILENAME = "/{filename:.+}";
	*/
	@Autowired
	private FileService fileService;
			
	@RequestMapping(value = "images/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename, HttpServletRequest request) throws IOException {
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("upload-dir");
			Resource file = this.fileService.findFile(filename, rootPath);
			return ResponseEntity.ok()
					.contentLength(file.contentLength())
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(file.getInputStream()));
		} catch(IOException e) {
			return ResponseEntity.badRequest().body("Ne postoji fajl "+e.getMessage());
		}
	}
	
	@RequestMapping(value = "images", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("upload-dir");
			fileService.createFile(file, rootPath);
			final URI locationURI = new URI(request.getRequestURL().toString()+"/").resolve(file.getOriginalFilename());
			return ResponseEntity.created(locationURI).body("Uspesan upload "+file.getOriginalFilename());
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Neuspesan upload "+file.getOriginalFilename()+" => "+e.getMessage());
		}
	}
	
	@RequestMapping(value="images/{filename:.+}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteFile(@PathVariable String filename, HttpServletRequest request) {
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("upload-dir");
			fileService.deleteFile(filename, rootPath);
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Successfully delete "+filename);
		} catch(IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete "+filename+" => "+e.getMessage());
		}
	}
}
