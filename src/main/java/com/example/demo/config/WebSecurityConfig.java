package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserService userService;

	@Bean(name = "passwordEncoder")
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(bCryptPasswordEncoder());
		return auth;
	}

	// @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		http.authorizeHttpRequests((requests) -> requests
			.requestMatchers(mvcMatcherBuilder.pattern("/registration")).permitAll()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("/login")
			.permitAll()
		)
		.logout((logout) -> logout.permitAll());

	return http.build();
	}

	// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// 	auth.jdbcAuthentication()
	// 	.dataSource(dataSource)
	// 	.passwordEncoder(NoOpPasswordEncoder.getInstance())
	// 	.usersByUsernameQuery("select email, password from users where email=?")
	// 	.authoritiesByUsernameQuery("select u.email, ur.roles from users u inner join users_roles ur on u.id = ur.id where u.email=?");
	// }
	
}