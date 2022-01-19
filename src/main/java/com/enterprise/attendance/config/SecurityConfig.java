//package com.enterprise.com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class SecurityConfig extends WebMvcConfigurerAdapter {
//	@Autowired
//	public void configureGlobal(Authentication auth) {
//		auth.inMemoryAuthentication().withUser("user") // #1
//				.password("password").roles("USER").and().withUser("admin") // #2
//				.password("password").roles("ADMIN", "USER");
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**"); // #3
//	}
//
//	@Override
//	protected void configure(HttpEntity http) throws Exception {
//		http.authorizeUrls().antMatchers("/signup", "/about").permitAll() // #4
//				.antMatchers("/admin/**").hasRole("ADMIN") // #6
//				.anyRequest().authenticated() // 7
//				.and().formLogin() // #8
//				.loginUrl("/login") // #9
//				.permitAll(); // #5
//	}
//}
