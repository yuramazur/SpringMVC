package ua.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		PRODUCT,COUNTRY;
	}
	String write(Folder folder,MultipartFile file,int id);
}
