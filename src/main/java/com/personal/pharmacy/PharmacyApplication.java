package com.personal.pharmacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import junit.framework.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PharmacyApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
		
	}
	
	public void test() {
		jdbcTemplate.update(
			      "INSERT INTO employees (first_name) VALUES (?)", "Bill");
		log.info("------------------");
	}

	@Override
	public void run(String... args) throws Exception {
		test();
		
	}

}
