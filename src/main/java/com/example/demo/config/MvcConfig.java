package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/registration").setViewName("registration");
		registry.addViewController("/profile").setViewName("profile");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/cart").setViewName("cart");
		registry.addViewController("/adminpanel").setViewName("adminPanel");
	}

}
