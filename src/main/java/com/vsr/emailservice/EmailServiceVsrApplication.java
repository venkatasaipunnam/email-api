package com.vsr.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class EmailServiceVsrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceVsrApplication.class, args);
	}

}
