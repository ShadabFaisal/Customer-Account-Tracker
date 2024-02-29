package com.cts.customer_account_tracker.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable();
		return httpSecurity.build();
	}


}
