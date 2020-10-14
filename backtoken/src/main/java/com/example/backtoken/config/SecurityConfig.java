package com.example.backtoken.config;

import com.example.backtoken.service.CustomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomDetailService customDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable().authorizeRequests()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/events/**").permitAll()
                .antMatchers("/invites/**").permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAthorizationFilter(authenticationManager(), customDetailService));
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
