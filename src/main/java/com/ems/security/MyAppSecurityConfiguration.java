package com.ems.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
/*
 * @EnableGlobalMethodSecurity: To enable method level seucurity
 */
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MyAppSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BCryptPasswordEncoder  encoder;
	
	@Autowired
	DataSource  dataSource;
	
	@Bean
	public  BCryptPasswordEncoder  pwdEncoder( ) {
		return  new  BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/*
		 * The below configuration says that delete url of
		 * application can be accessed to a user with role
		 * MANAGER and other urls of application are accessible
		 * to any user.
		 */
		.antMatchers("/delete**").hasAnyRole("MANAGER", "ADMIN")
		.anyRequest().authenticated()
		.and()
		/*
		 * httpBasic() configures HTTP Basic Authentication
		 * Spring security responds to browser with message
		 * authentication is required and browser will open
		 * a dialog box to accept user credentials.
		 */
	//	.httpBasic()
		
		/*
		 * formLogin() configures HTTP Form authentication
		 * Spring security provides a default login page to accept
		 * user credentials when a user makes a request to access 
		 * a protected resource.
		 */
		.formLogin()
		.and()
		/*
		 * For customized error page, instead default error page created
		 * by spring boot.
		 */
		.exceptionHandling().accessDeniedPage("/WEB-INF/views/accessDenied.jsp")

		.and()
		.csrf().disable();
		
		/*
		 * Restrict the sessions for an authenticated user to only one
		 */
		http.sessionManagement().maximumSessions(1);
		
		/*
		 * logout() :  invalidates user session
		 * logoutUrl() :  represents  an application url to initiate logout
		 * logoutSuccessUrl(): represents an application url to whom request is redirected on successful logout
		 */
		http.logout().logoutUrl("/logoutMe").logoutSuccessUrl("/loggedOut").permitAll();
		
		/*
		 * Enable https channel to serve all urls of the application
		 */
		http.requiresChannel().anyRequest().requiresSecure();
	}
	
	@Autowired
    public  void  configureGloabl(AuthenticationManagerBuilder  builder) throws Exception {
		/*
		 * AuthenticationManagerBuilder is a helper class to build
		 * AuthenticationManager object.
		 * AuthenticationManager object stores user credentials with roles
		 * and spring security filters uses AuthenticationManager object
		 * in Authentication and Authorization process    	
		 */
		
		/*
		 * AuthenticationManager object can be built with in-memory user details
		 * or with user-details from a database.
		 */
		
		/*
		 * The below code builds AuthenticationManager object with in-memory
		 * user-details 
		 */
		
		/*
		builder.inMemoryAuthentication()
		.withUser("Payal").password(encoder.encode("pa@123")).roles("MANAGER")
		.and()
		.withUser("shekher").password(encoder.encode("123456")).roles("ADMIN");
		*/
		
		/*
		 * The below code builds  AuthenticationManager object with user-details
		 * from Database
		 */
		builder.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password,active from users where username=?")
		.authoritiesByUsernameQuery("select username,authority  from authorities where username=?")
		.passwordEncoder(encoder);
		
		
    }

}
