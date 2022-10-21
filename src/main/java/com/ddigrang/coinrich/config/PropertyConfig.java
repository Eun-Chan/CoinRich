package com.ddigrang.coinrich.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

@Configuration
public class PropertyConfig {

    @Bean
    public PropertiesFactoryBean propertiesFactoryBean() throws Exception {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("application.yml");

        propertiesFactoryBean.setLocation(classPathResource);
        propertiesFactoryBean.setFileEncoding(StandardCharsets.UTF_8.toString());

        return propertiesFactoryBean;
    }
}
