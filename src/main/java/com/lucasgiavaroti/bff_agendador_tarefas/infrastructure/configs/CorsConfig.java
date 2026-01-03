package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // para todos os endpoints da nossa aplicação
                        .allowedOrigins("*") // origens permitidas
                        .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE") // métodos permitidos
                        .allowedHeaders("*") // cabeçalhos permitidos
                        .allowCredentials(true) // permitir o envio de cookies e tokens pelo authorization
                        .maxAge(3600);
            }
        };
    }

}
