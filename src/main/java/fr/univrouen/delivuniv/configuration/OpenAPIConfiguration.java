package fr.univrouen.delivuniv.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI OpenAPIConfig() {
        return new OpenAPI()
                .info(new Info().title("Delivery Person API").version("1.0"));
    }
}
