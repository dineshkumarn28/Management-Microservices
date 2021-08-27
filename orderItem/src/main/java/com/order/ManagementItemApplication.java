package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.*")
@SpringBootApplication
public class ManagementItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementItemApplication.class, args);
	}

}
