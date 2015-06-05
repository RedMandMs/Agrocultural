package ru.lenoblgis.introduse.sergey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
/*	@Autowired
	DataSource ds;*/

/*	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication().dataSource(ds)
		.usersByUsernameQuery(
			"select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from users where username=?");
	}*/
	
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("password").roles("USER", "ADMIN");
	}*/

/*	@Override
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
	}*/
	
	@Autowired
    private UserDetailsService userDetailsService;
 
    // ������������ ���� ���������� UserDetailsService 
    // � ����� PasswordEncoder ��� ���������� ������ � ������ SHA1
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // �������� ������ �� CSRF ����
        http.csrf()
                .disable()
                // ��������� ������� ��������
                // �� ������� ����� ����������� ������ � �������� � ��������� ������
                .authorizeRequests()
                .antMatchers("/resources/**", "/**").permitAll()
                .anyRequest().permitAll()
                .and();
 
        http.formLogin()
                // ��������� �������� � ������ ������
                .loginPage("/login")
                // ��������� action � ����� ������
                .loginProcessingUrl("/j_spring_security_check")
                // ��������� URL ��� ��������� ������
                .failureUrl("/login?error")
                // ��������� ��������� ������ � ������ � ����� ������
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // ���� ������ � ����� ������ ����
                .permitAll();
 
        http.logout()
                // ��������� ������ ������ ����
                .permitAll()
                // ��������� URL �������
                .logoutUrl("/logout")
                // ��������� URL ��� ������� �������
                .logoutSuccessUrl("/login?logout")
                // ������ �� �������� ������� ������
                .invalidateHttpSession(true);
 
    }
	
    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }
}
