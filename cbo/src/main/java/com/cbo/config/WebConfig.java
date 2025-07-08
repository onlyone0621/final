package com.cbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/profileImage/**")
        .addResourceLocations("file:///C:/upload/profileImage/");
		
		
		// 게시글 이미지 맵핑
        registry.addResourceHandler("/postImages/**")
                .addResourceLocations("file:///C:/upload/postImages/");
   
	}
}