package ru.lenoblgis.introduse.sergey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import ru.lenoblgis.introduse.sergey.services.EventService;
import ru.lenoblgis.introduse.sergey.services.OwnerService;
import ru.lenoblgis.introduse.sergey.services.PassportService;

@Configuration
@EnableWebMvc
@ComponentScan("ru.lenoblgis.introduse.sergey.controllers")
public class WebAppConfig extends WebMvcConfigurerAdapter {
 
    // Позволяет видеть все ресурсы в папке pages, такие как картинки, стили и т.п.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
    }
 
    // а этот бин инициализирует View нашего проекта
    // альтернатива в mvc-dispatcher-servlet.xml
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
 
        return resolver;
    }
    
    /**
     * Получение бина сервиса работы с организациями
     * @return - сервис рабыты с организациями
     */
    @Bean
    OwnerService getOwnerService() {
        return new OwnerService();
    }
    
    /**
     * Получение бина сервиса работы с паспортами полей
     * @return - срвис работы с паспортами полей
     */
    @Bean
    PassportService getPassportService(){
    	return new PassportService();
    }
    
    /**
     * Получение бина сервиса работы с журналом событий
     * @return - срвис работы с журналом событий
     */
    @Bean
    EventService getEventService(){
    	return new EventService();
    }
 
}
