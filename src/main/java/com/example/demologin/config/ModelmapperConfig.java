package com.example.demologin.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelmapperConfig {
    @Bean
    public ModelMapper modelMapperConfig() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        /*Need some modify of configuration heres
         */
        return modelMapper;
    }
}
