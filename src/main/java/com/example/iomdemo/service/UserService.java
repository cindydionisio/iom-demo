package com.example.iomdemo.service;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	public abstract Boolean addUser (String username, HttpServletRequest request);

}
