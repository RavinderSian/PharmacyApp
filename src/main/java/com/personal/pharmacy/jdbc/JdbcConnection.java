package com.personal.pharmacy.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcConnection implements CommandLineRunner {

	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.update(
			      "INSERT INTO dfgfd VALUES (?, ?)", 1, "Test");
		log.info("------------------");
		
	}
	
}
