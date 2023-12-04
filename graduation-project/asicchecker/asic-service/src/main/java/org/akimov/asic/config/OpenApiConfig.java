package org.akimov.asic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("1") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Asic checker API")
                        .version(appVersion));
    }
}
