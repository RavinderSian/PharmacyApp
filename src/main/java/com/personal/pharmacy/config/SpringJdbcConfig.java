package com.personal.pharmacy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
public class SpringJdbcConfig {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Bean
	public SimpleJdbcInsert simpleJdbcInsert() {
        
        return new SimpleJdbcInsert(jdbcTemplate);

	}
	
}

