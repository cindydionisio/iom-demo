package com.example.iomdemo.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.example.iomdemo.util.FileExtensionEnum;

public interface FileUploadService {

	public abstract List<String> getFiles(String folder);
	public abstract String getContent(String folder, String fileName, String contentHeader);
	public abstract String saveFile(String folder, String fileName, String contentHeader, HttpServletRequest request);
	public abstract Boolean deleteFile(String folder, String fileName, String contentHeader);

	/*get extension based on content header - default is plain text*/
	public default FileExtensionEnum getExtension(String contentHeader) {
		FileExtensionEnum ext = FileExtensionEnum.get(contentHeader);
		if(ext == null) {
			return FileExtensionEnum.get("text/plain");
		}
		return ext;
	}

	public default String getFullFileDir(String baseDir, String folder, String fileName, String ext) {
		return baseDir + folder + "\\" + fileName + "." + ext;
	}
}
