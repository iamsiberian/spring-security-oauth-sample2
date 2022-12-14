package org.example.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.mvcMatcher("/messages/**")
				.authorizeRequests()
				.mvcMatchers("/messages/**").access("hasAuthority('SCOPE_message.read')")
				.and()
				.oauth2ResourceServer()
				.jwt();
		/*
		http
				.securityMatcher("/messages/**")
				.authorizeHttpRequests()
				.requestMatchers("/messages/**").hasAuthority("SCOPE_message.read")
				.and()
				.oauth2ResourceServer()
				.jwt();

		 */
		return http.build();
	}

}
