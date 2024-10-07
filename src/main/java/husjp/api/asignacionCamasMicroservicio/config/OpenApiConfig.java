package husjp.api.asignacionCamasMicroservicio.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi ejemploOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/ejemplo/**"};
        return GroupedOpenApi.builder()
                .group("ejemplo")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }
}
