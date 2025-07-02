package it.uniroma3.siwpersonale.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // mappo /uploads/** su folder uploads (che contiene la cartella images)
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/Users/182935/Desktop/giochino/siwpersonale/uploads/");
    }
}
