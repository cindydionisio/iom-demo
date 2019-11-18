package com.example.iomdemo.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iomdemo.properties.ApplicationProperties;
import com.example.iomdemo.service.FileUploadService;
import com.example.iomdemo.util.FileExtensionEnum;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Autowired
	ApplicationProperties applicationProperties;

	@Override
	public List<String> getFiles(String folder) {
		List<String> fileList = new ArrayList<>();
		try (Stream<Path> stream = Files.list(Paths.get(applicationProperties.getRootDirectory() + folder))) {
			stream.forEach( file -> fileList.add(file.toString().replace(applicationProperties.getRootDirectory()+folder+"\\", ""))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileList;

	}

	@Override
	public String getContent(String folder, String fileName, String contentHeader) {
		StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(this.getFullFileDir(applicationProperties.getRootDirectory(), folder, fileName, this.getExtension(contentHeader).toString())), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
	}

	@Override
	public String saveFile(String folder, String fileName, String contentHeader, HttpServletRequest request) {
		if(request.getContentLength() > 0) {
			try(BufferedReader reader = request.getReader()) {
				String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				String file = this.getFullFileDir(applicationProperties.getRootDirectory(), folder, fileName, this.getExtension(contentHeader).toString());
				Files.write(Paths.get(file), body.getBytes());
				return folder + "\\" + fileName + "." + this.getExtension(contentHeader).toString();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Boolean deleteFile(String folder, String fileName, String contentHeader) {
		try {
			Files.delete(Paths.get(this.getFullFileDir(applicationProperties.getRootDirectory(), folder, fileName, this.getExtension(contentHeader).toString())));
			return Boolean.TRUE;
		} catch (IOException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

}
