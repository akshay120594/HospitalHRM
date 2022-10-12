package com.jbk.hospitalhrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class HospitalHrmProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(HospitalHrmProject1Application.class, args);
	}
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		return new CommonsMultipartResolver();
		
	}

}
