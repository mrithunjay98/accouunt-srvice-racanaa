package com.racanaa.services.account.config;

import com.racanaa.services.account.dto.converter.SearchCriteriaConverter;
import com.racanaa.services.account.dto.converter.SortCriteriaConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Config class for web server.
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
@CrossOrigin
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Adding CORS mapping to allow everything
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
                .allowedOrigins("*");
    }

    /**
     * Registering converters for SortDto and SearchDto
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SortCriteriaConverter());
        registry.addConverter(new SearchCriteriaConverter());
    }
}