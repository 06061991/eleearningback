package com.esprit.springjwt;

import com.esprit.springjwt.config.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ElearningApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(ElearningApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {


	}	}
