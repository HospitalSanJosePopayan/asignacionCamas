package husjp.api.asignacionCamasMicroservicio.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi diagnosticoOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/diagnostico/**"};
        return GroupedOpenApi.builder()
                .group("diagnosticos")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("diagnosticos").version(appVersion)
                        .description("diagnostico cie10 ")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi versionSolilcitudCamaOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/versionSolicitudCama/**"};
        return GroupedOpenApi.builder()
                .group("versionSolicitudCama")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi solilcitudCamaOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/solicitudCama/**"};
        return GroupedOpenApi.builder()
                .group("solicitudCama")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi camaOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/cama/**"};
        return GroupedOpenApi.builder()
                .group("cama")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi bloqueServicioOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/bloque-servicio/**"};
        return GroupedOpenApi.builder()
                .group("bloque-servicio")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi servicioOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/servicio/**"};
        return GroupedOpenApi.builder()
                .group("servicio").displayName("SERVICIO")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi titulosFormacionAcademicaOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/titulosFormacionAcademica/**"};
        return GroupedOpenApi.builder()
                .group("titulosFormacionAcademica")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi solicitudCamilleroOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/solicitudCamillero/**"};
        return GroupedOpenApi.builder()
                .group("solicitudCamillero")
                .displayName("SOLICITUD CAMILLERO")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("EJEMPLO microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi medidasDeAislamientoOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/medidasAislamiento/**"};
        return GroupedOpenApi.builder()
                .group("medidasAislamiento")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("medidasAislamiento microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi respuestaVersionSolicitudCamaOpenApi(@Value("2.3.0") String appVersion){
        String [] paths = {"/asignacionVersionSolicitudCama/**"};
        return GroupedOpenApi.builder()
                .group("asignacionVersionSolicitudCama")
                .displayName("ASIGNACION DE CAMA")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("medidasAislamiento microservicio").version(appVersion)
                        .description("Ejemplo de como se debe documentar el microservicio")))
                .pathsToMatch(paths)
                .build();
    }

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
