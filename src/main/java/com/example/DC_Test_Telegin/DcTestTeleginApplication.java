package com.example.DC_Test_Telegin;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DcTestTeleginApplication {
	@Bean
	ModelMapper modelMapper(){return new ModelMapper();};

	public static void main(String[] args) {
		SpringApplication.run(DcTestTeleginApplication.class, args);
	}

}
