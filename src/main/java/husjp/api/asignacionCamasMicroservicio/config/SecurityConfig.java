package husjp.api.asignacionCamasMicroservicio.config;

import husjp.api.asignacionCamasMicroservicio.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                        authorizeRequests.requestMatchers(AUTH_WHITLIST).permitAll();
                        //ejemplo para proteger un endpoint
                        authorizeRequests.requestMatchers(HttpMethod.GET, "ejemplo/prueba").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.POST, "versionSolicitudCama").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "versionSolicitudCama/active").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "titulosFormacionAcademica/especialidad").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "medidasAislamiento").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "diagnostico/{idOrName}").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.PUT, "solicitudCama/cancelar/{idSolicitudCama}").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "bloque-servicio").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "bloque-servicio/servicios/{idBloque}").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.POST, "asignacionVersionSolicitudCama").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "cama/{idServicio}").hasAnyRole("ADMIN");
                        authorizeRequests.requestMatchers(HttpMethod.GET, "servicio/{idBloqueServicio}").hasAnyRole("ADMIN");
                        //SOLICITUD CAMILLERO
                        authorizeRequests.requestMatchers(HttpMethod.POST, "solicitudCamillero").hasAnyRole("ADMIN");
                        //notifcacion
                        authorizeRequests.requestMatchers("/ws-notifications/**", "/topic/**").permitAll();
                        authorizeRequests.requestMatchers("/ws/**").permitAll();
                        authorizeRequests.anyRequest().authenticated();
                    }
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //123456 PASSWORD
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withUsername("admin")
//                .password("$2a$12$VjeG91WALEUJd6ARRMzZWeEU0kAdf2flxgqLO2oR9a25Y/9i1GcNi")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITLIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
}
