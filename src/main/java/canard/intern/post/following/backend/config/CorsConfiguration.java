package canard.intern.post.following.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //annoté Configuration => sera scanné au démarrage du projet et sera chargé
public class CorsConfiguration {

    @Bean //charge automatiquement au démarrage d'une application
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //permet de rentrer dans toutes les routes du controller REST
                        // on parle de chemin relatif à notre application
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "DELETE"); //permet de lister les méthodes
            }
        };
    }
}
