package com.example.iomdemo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.iomdemo.service.FileUploadService;

@RestController
@RequestMapping(value = "/iomdemo/fileupload")
public class FileUploadController {

	@Autowired
	FileUploadService fileUpload;

	/**
	 * @param folder - required; string; can't be null
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/list/{folder}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFiles(@PathVariable("folder") String folder) {
	      return new ResponseEntity<>(fileUpload.getFiles(folder), HttpStatus.OK);
	}

	/**
	 * @param folder - required; string; can't be null
	 * @oaram filename - required; string; name of the file
	 * @param content-type - required; part of the request header; sets what kind of file extension; added this because adding file extension
	 * to the url is not a good practice.
	 * @return ResponseEntity 200 - OK, 400 - if file not exists
	 */
	@RequestMapping(value = "/file/{folder}/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFileContent(@PathVariable("folder") String folder, @PathVariable("fileName") String fileName,
			@RequestHeader(name = "Content-Type") String contentHeader) {
		String response = fileUpload.getContent(folder, fileName, contentHeader);
		if(response != null && response.length() > 0) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + fileName + "\"").body(response);
		}
		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	   }

	/**
	 * @param folder - required; string; can't be null
	 * @oaram filename - required; string; name of the file
	 * @param content-type - required; part of the request header; sets what kind of file extension; added this because adding file extension
	 * to the url is not a good practice.
	 * @return ResponseEntity 200 - OK, 400 - if file not exists
	 */
	@RequestMapping(value = "/file/{folder}/{fileName}", method = RequestMethod.POST)
	public ResponseEntity<Object> saveFile(@PathVariable("folder") String folder, @PathVariable("fileName") String fileName,
			@RequestHeader(name = "Content-Type") String contentHeader, HttpServletRequest  httprequest) {
		String file = fileUpload.saveFile(folder, fileName, contentHeader, httprequest);
		if(file != null) {
			return new ResponseEntity<>(file, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param folder - required; string; can't be null
	 * @oaram filename - required; string; name of the file
	 * @param content-type - required; part of the request header; sets what kind of file extension; added this because adding file extension
	 * to the url is not a good practice.
	 * @return ResponseEntity 200 - OK, 400 - if file not exists
	 */
	@RequestMapping(value = "/file/{folder}/{fileName}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFile(@PathVariable("folder") String folder, @PathVariable("fileName") String fileName, @RequestHeader(name = "Content-Type") String contentHeader) {
		if(fileUpload.deleteFile(folder, fileName, contentHeader)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
