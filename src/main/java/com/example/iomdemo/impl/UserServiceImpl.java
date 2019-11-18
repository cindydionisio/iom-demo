package com.example.iomdemo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.iomdemo.IomdemoApplication;
import com.example.iomdemo.properties.ApplicationProperties;
import com.example.iomdemo.service.UserService;
import com.example.iomdemo.util.ApplicationUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	ApplicationProperties applicationProperties;


	@Override
	public Boolean addUser(String username, HttpServletRequest request) {
		 try {
			String password = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())).trim();
			String newUser = username + ":" + new BCryptPasswordEncoder().encode(password) + ",";

			File file = ApplicationUtil.getUserCredFile(applicationProperties.getUserCredDir());
			if(file != null) {
				FileWriter fr = new FileWriter(file, true);
				fr.write(newUser);
				fr.close();
			}
			return Boolean.TRUE;
		} catch (IOException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

}
