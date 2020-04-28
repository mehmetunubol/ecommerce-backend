package com.mmu.base.cms;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication

public class CmsApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CmsApplication.class);

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		LOGGER.info("Application is started..");
		SpringApplication.run(CmsApplication.class, args);
	}
}