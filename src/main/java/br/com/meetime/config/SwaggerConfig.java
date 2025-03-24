package br.com.meetime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HubSpot Integration API")
                        .version("1.0")
                        .description("API para integração com o HubSpot, permitindo autenticação via OAuth2 e gerenciamento de contatos."));
    }
}
