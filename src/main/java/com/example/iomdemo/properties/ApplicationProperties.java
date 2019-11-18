package com.example.iomdemo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class ApplicationProperties {

	@Value("${app.rootdir}")
	private String rootdir;

	@Value("${app.adminuser}")
	private String adminuser;

	@Value("${app.adminpassword}")
	private String adminpassword;

	@Value("${app.usercredentials}")
	private String usercredentials;


	public String getRootDirectory() {
		return this.rootdir;
	}

	public String getAdminUserName() {
		return this.adminuser;
	}

	public String getAdminPassword() {
		return this.adminpassword;
	}

	public String getUserCredDir() {
		return this.usercredentials;
	}

}
