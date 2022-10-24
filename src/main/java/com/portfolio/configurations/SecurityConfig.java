package com.portfolio.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.withDefaultPasswordEncoder().username("Bolat").password("3Ja8p").roles("ADMIN")
				.build();
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("").roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> {
			try {
				authorize.mvcMatchers("/**").permitAll().mvcMatchers("/start/**").hasRole("ADMIN").and().formLogin()
						.permitAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return http.build();
	}

}
