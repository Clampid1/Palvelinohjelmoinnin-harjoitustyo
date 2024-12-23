package com.example.Harjoitustyo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	// Asetettu html tiedostojen nimiä tiettyihin osoitteisiin.
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/data").setViewName("data");
		registry.addViewController("/login").setViewName("login");
        registry.addViewController("/new").setViewName("new");
	}

}
