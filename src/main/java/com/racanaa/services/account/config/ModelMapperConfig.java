package com.racanaa.services.account.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapperConfig for model to dto conversion and vice versa
 *
 * @author Manohar
 * @see ModelMapper
 * @since 20/Sep/2023
 */
@Configuration
public class ModelMapperConfig {
    /**
     * Bean to initialize model mapper on spring boot startup
     *
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
