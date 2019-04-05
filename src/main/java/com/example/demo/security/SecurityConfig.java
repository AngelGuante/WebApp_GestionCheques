package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService userDetailService;
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailService).passwordEncoder(encoder());
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/","/css/**","/images/**","/js/**").permitAll()
		.antMatchers("/admin/**").permitAll()
		.antMatchers("/vista/**").permitAll().antMatchers("/form")
		.permitAll()
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/login")
		.permitAll();
		
	}



	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
  
}
