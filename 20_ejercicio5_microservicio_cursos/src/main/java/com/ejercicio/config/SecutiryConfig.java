package com.ejercicio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecutiryConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication
		.inMemoryAuthentication()
		.withUser("user")
			.password("{noop}user") 
			.roles(Roles.USER.name())
			.and()
		.withUser("admin")
			.password("{noop}admin") 
			.roles(Roles.ADMIN.name());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/curso").hasRole(Roles.ADMIN.name())
		.antMatchers(HttpMethod.PUT, "/curso").hasAnyRole(Roles.ADMIN.name())
		.antMatchers(HttpMethod.DELETE, "/curso").hasAnyRole(Roles.ADMIN.name())
		.antMatchers(HttpMethod.GET, "/**").authenticated() 
		.and()
		.httpBasic();
	}
}
