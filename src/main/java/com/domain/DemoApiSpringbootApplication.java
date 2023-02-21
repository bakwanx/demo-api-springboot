package com.domain;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApiSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiSpringbootApplication.class, args);
	}

	//dibuatkan bean supaya ModelMapper dapat di inject menggunakan
	//autowired di seluruh class yang dibutuhkan
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
