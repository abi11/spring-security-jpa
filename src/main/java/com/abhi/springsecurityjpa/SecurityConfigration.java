package com.abhi.springsecurityjpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abhi.springsecurityjpa.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//	super.configure(auth);
		auth.userDetailsService(userDetailsService)
		
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		//if we need to change schema, table names 
//		.usersByUsernameQuery("select username,password,enabled from users"
//				+ " where username= ?")
//		.authoritiesByUsernameQuery("select username,authority from authorities "
//				+ " where username= ?")
//	
//		//for default schema and database tables
////		.withDefaultSchema()
////		.withUser(
////				User.withUsername("user")
////				.password("pass")
////				.roles("USER")
////				)
////		.withUser(
////				User.withUsername("admin")
////				.password("pass")
////				.roles("ADMIN")
////				)
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("ADMIN","USER")
		.antMatchers("/").permitAll()
		.and()
		.formLogin();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
