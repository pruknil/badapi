package com.guanbad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().and().authorizeRequests().antMatchers("/", "/assets/**").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("j_username").passwordParameter("j_password").permitAll().and().logout()
				.logoutUrl("/logout").permitAll();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("badapi").password("{noop}password").roles("USER");
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().antMatcher("/api/*").authorizeRequests().anyRequest().hasRole("USER").and()
					.httpBasic();
		}
	}
}
