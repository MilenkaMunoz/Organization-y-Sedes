package pe.edu.vallegrande.vgmsorganizations.application.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Configuración global de CORS para WebFlux - ACCESO UNIVERSAL
 * Esta configuración permite el acceso desde cualquier origen web
 * Configuración actualizada para máxima compatibilidad y acceso abierto
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permitir cualquier origen - Configuración abierta para acceso universal
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));

        // Permitir cualquier método HTTP
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));

        // Permitir cualquier header
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        corsConfiguration.setExposedHeaders(Arrays.asList(
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
                "Authorization", "Content-Disposition", "X-Total-Count",
                "X-Page-Number", "X-Page-Size"));

        // Nota: Al usar allowCredentials=true con origins="*", se deben usar
        // allowedOriginPatterns
        // Para acceso completamente abierto, se podría cambiar a allowCredentials=false
        corsConfiguration.setAllowCredentials(false);

        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}

