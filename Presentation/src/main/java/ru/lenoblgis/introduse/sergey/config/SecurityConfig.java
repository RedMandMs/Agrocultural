package ru.lenoblgis.introduse.sergey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import ru.lenoblgis.introduse.sergey.domen.user.UserRole;

public class SecurityConfig extends WebSecurityConfigurerAdapter{

	SQLServerDataSource ds = null;
	
	public SecurityConfig() {
		super();
		beforeConstruct();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.jdbcAuthentication()
				.dataSource(ds);
		//TODO:
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/registration", "/login", "/hello").permitAll()
				.antMatchers("admin/**").hasRole(UserRole.ADMIN.getName())
				.antMatchers("organization/**", "passport/**").authenticated()
				.anyRequest().authenticated()
					.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/hello")
				.and()
			.formLogin()
				.loginPage("/authorization")
				.permitAll();
			
	}
	
	public void beforeConstruct(){
		ds = new SQLServerDataSource();
		ds.setPortNumber(1433);
		ds.setHostNameInCertificate("localhost");
		ds.setDatabaseName("passport_agricultural");
		ds.setUser("adminAgricultural");
		ds.setPassword("admin123");
	}
	
	
}
