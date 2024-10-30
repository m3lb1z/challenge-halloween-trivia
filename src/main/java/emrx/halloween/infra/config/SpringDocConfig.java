package emrx.halloween.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Halloween Quiz API")
                .description("API Rest de la aplicación Challenge Halloween, " +
                        "que contiene las funcionalidades de un quiz sobre Halloween de mitos y leyendas.")
                .contact(new Contact()
                        .name("Equipo de desarrollo")
                        .email("monster@halloween.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://challenge-halloween.com/api/licencia"))
                .version("1.0.0")
            );
    }
}