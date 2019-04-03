package com.luizalabs.marty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.luizalabs.marty.filter.security.DefaultAuthenticationEntryPoint;
import com.luizalabs.marty.filter.security.JwtTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(new DefaultAuthenticationEntryPoint()).and()
				.authorizeRequests().antMatchers("/actuator/health","/actuator/info", "/swagger-resources/**", "/v2/api-docs/**", "/csrf/**",
						"/webjars/**", "/swagger-ui.html")
				.permitAll().anyRequest().authenticated();
		http.addFilter(new JwtTokenAuthenticationFilter(authenticationManager(), jwtConfig));

	}

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
}
