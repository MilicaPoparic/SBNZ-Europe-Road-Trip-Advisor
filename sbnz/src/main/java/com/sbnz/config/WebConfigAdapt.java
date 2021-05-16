package com.sbnz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@SuppressWarnings("deprecation")
public class WebConfigAdapt extends WebMvcConfigurerAdapter{
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(false);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
	    matcher.setUseSuffixPatternMatch(false);
	}

}
