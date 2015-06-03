package ru.lenoblgis.introduse.sergey.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import ru.lenoblgis.introduse.sergey.domen.user.UserRole;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource ds;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication().dataSource(ds)
		.usersByUsernameQuery(
			"select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from users where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
			  .formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error").defaultSuccessUrl("/organization/company/47")
			  .usernameParameter("user_login").passwordParameter("user_password")
			.and()
			  .logout().logoutSuccessUrl("/login?logout")
			.and()
			  .exceptionHandling().accessDeniedPage("/403")
			.and()
			  .csrf();
	}
	
	
}
