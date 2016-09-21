package ua.service.implementation;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.service.FileWriter;

@Service
public class FileWriterImpl implements FileWriter{

	@Override
	public String write(Folder folder, MultipartFile file, int id) {
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			int index = fileName.lastIndexOf(".");
			String extension = fileName.substring(index);
			File pathToHome = new File(System.getProperty("catalina.home"));
			File pathToFolder = new File(pathToHome, "images" + File.separator + folder.name().toLowerCase());
			if(!pathToFolder.exists()){
				pathToFolder.mkdirs();
			}
			File pathToFile = new File(pathToFolder,String.valueOf(id)+extension);
				try {
					file.transferTo(pathToFile);
					return extension;
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		return null;
	}
	
	

}