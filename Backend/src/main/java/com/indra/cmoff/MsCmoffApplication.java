package com.indra.cmoff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;






//@SpringBootApplication
@Component
@ComponentScan({"com.*","com.indra.cmoff.service","com.indra.cmoff.service.impl"})
@EnableJpaRepositories("com.*")
@SpringBootApplication(scanBasePackages = {"com.*", "com.indra.cmoff","com.*","com.indra.cmoff.repository.custom","com.indra.cmoff.service",
		"com.indra.cmoff.service.impl", "com.indra.cmoff.repository","com.indra.cmoff.repository.custom"})
@EntityScan("com.indra.cmoff.model")
@EnableAutoConfiguration
public class MsCmoffApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MsCmoffApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MsCmoffApplication.class);
	}
}
