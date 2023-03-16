package demo;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)


public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	public void config_global(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("oussama").password("{noop}1234").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("visit").password("{noop}1234").roles("VISITEUR");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
    		.authorizeRequests()
    			.anyRequest()
    				.authenticated()
    					.and()
    		.formLogin()
        		.loginPage("/login")
        		.permitAll()
        	.defaultSuccessUrl("/index") ;	
	} 
	
	
}
