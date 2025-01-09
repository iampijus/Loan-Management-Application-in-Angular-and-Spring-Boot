package com.pijus.loanmgmtsystem.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR = "C:\\Learning\\Project\\loan-mgmt-system-backend\\src\\main\\resources\\static\\loan-documents";

	public boolean uploadFile(MultipartFile myFile) {

		boolean isUploaded = false;

		try {

			Files.copy(myFile.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + myFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			isUploaded = true;

			return isUploaded;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUploaded;
	}
}
