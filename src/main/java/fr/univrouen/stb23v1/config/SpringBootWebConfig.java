package fr.univrouen.stb23v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Boot web configuration file.
 */
@Configuration
@EnableWebMvc
public class SpringBootWebConfig implements WebMvcConfigurer {

    /**
     * Enables the cross-origin on the API.
     *
     * @param registry The CORS URL registry.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE");
    }

}
