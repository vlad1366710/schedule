package com.schedule.schedule.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@Configuration
public class SwaggerConfig {
    // Дополнительные настройки, если необходимо
}
