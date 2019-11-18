package com.example.iomdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.iomdemo.service.UserService;

@RestController
@RequestMapping(value = "/iomdemo/user-mngmt")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * @param username - required; string; can't be null
	 * @param httprequest - required; contains the password
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/user/{username}", method = RequestMethod.POST)
	public ResponseEntity<Object> addUser(@PathVariable("username") String username, HttpServletRequest  httprequest) {
		if(userService.addUser(username, httprequest)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
