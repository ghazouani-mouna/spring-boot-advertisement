package com.jmbg.advertisement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Create Users in Memory for Demo purpouse
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER")
            .and()
            .withUser("qualityUser").password("{noop}password").roles("USER", "QUALITY_USER");

    }

    /**
     * Securize endpoints with HTTP Basic Authentication
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic() //HTTP Basic authentication
            .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/advertisement").permitAll() // All users
                .antMatchers(HttpMethod.GET, "/api/advertisement/**").hasRole("QUALITY_USER") // Only users with ADMIN role
            .and()
            .csrf().disable()
            .formLogin().disable();
    }
}