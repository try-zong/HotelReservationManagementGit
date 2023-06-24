package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.entity.Constants;
@Configuration
public class ImgUploadConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\sts-springBoot-workpace\\HotelReservationManagementGit\\src\\main\\resources\\static\\img\\" );
	}
}
