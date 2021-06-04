package com.fhkiel.oopproject.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * <strong>Class-Description:</strong><br/>
 * Entry Point for application
 * Start tomcat server with Rest-Api and serving frontend as single index.html
 * Server hosted on http://localhost:8080/
 */
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);


		System.out.println("Visit http://localhost:8080/");
	}

}
