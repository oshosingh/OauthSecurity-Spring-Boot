package com.example.Client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	
	//Method for adding our pages to the MVC
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/")
				.setViewName("forward:/index");      //when root is hit Index page will be rendered.
		
		registry.addViewController("/index");
		registry.addViewController("/secure");		//when authenticated Secure page will be rendered.
		
	}

	//method for registering the path of the files
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//path where the static files will be present
		registry.addResourceHandler("resources/**").addResourceLocations("resources");
	}
	
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
	
	
	// Method for injecting the resources we created here or else spring boot will not know it
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	

}
