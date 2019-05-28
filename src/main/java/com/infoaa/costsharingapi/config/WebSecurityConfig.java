
package com.infoaa.costsharingapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.infoaa.sharing.etracker.security.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//public class WebSecurityConfig implements WebMvcConfigurer {
	
	@Autowired
	private UserDetailsService userDetailsService;


	@Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // Override this method 
    // to disable the default Spring Boot security.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//TODO: Add Context here to protect from unauthorized access
/*
		System.out.println("~~~3~~~~~~~~~~~~~~~~~~~~~~~~~~~~Inside the configure() " + userDetailsService.toString());
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/resources/**", "/registration", "/home.html", "/console").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	*/
	}

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/*
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		UserDetails user =
		     User.withUsername("testuser")
			.password("password")
			.roles("USER")
			.build();
	
		return new InMemoryUserDetailsManager(user);
	}
	*/
}
