package org.crama.tropicalgarden.security;

import org.crama.tropicalgarden.users.UserController;
import org.crama.tropicalgarden.users.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
            	.antMatchers("/api/user/register",
            			"/api/user/activate",
            			"/api/user/reset-password",
            			"/api/user/reset-password-link",
            			"/api/user/change-password").permitAll()
            	.antMatchers("/api/admin/**",
            			"/api/surfing/approve/**").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and().httpBasic().realmName("DeveloperStack").authenticationEntryPoint(authEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
            
    }
	
	@Bean
	protected UserDetailsService userDetailsService() {
	    return new UserDetailsServiceImpl();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
    	
      logger.info("Creating password encoder bean");
      
      return new BCryptPasswordEncoder();
    }
	
}
