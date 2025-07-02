package it.uniroma3.siwpersonale.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve le risorse statiche dalla cartella uploads su Windows
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/Users/182935/Desktop/giochino/siwpersonale/uploads/");

         // Risorse statiche di default (css, js, immagini da static/)
    registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/");
    }
}
