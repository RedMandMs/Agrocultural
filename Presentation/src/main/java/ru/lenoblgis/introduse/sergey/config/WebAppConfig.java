package ru.lenoblgis.introduse.sergey.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.services.EventService;
import ru.lenoblgis.introduse.sergey.services.OwnerService;
import ru.lenoblgis.introduse.sergey.services.PassportService;
import ru.lenoblgis.introduse.sergey.services.UserDetailsServiceImpl;
import ru.lenoblgis.introduse.sergey.services.UserService;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Configuration
@EnableWebMvc
@ComponentScan({"ru.lenoblgis.introduse.sergey.controllers", "ru.lenoblgis.introduse.sergey.data.dao"})
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
     * Получение бина DAO
     * @return - DAO
     */
    @Bean
    DAO getDAO() {
        return new DAO();
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
    
    /**
     * Получение бина сервиса работы с пользователями
     * @return - срвис работы с пользователями
     */
    @Bean
    UserService getUserService(){
    	return new UserService();
    }
    
    @Bean
	public DataSource getDataSource() {
    	SQLServerDataSource ds = new SQLServerDataSource();
		ds.setPortNumber(1433);
		ds.setHostNameInCertificate("localhost");
		ds.setDatabaseName("passport_agricultural");
		ds.setUser("adminAgricultural");
		ds.setPassword("admin123");
		return ds;
    }
    
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }
 
}
