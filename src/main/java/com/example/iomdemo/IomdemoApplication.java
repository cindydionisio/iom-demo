package com.example.iomdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Lazy(true) //set this to true so the beans will be initialize as needed only

public class IomdemoApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(IomdemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(IomdemoApplication.class, args);
	}

}
