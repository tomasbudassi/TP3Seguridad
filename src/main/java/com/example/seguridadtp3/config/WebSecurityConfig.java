package com.example.seguridadtp3.config;

import com.example.seguridadtp3.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/login", "/tareas", "/index").permitAll()

                .antMatchers(HttpMethod.POST, "/registrar_tarea").hasAnyAuthority("admin")
                .antMatchers(HttpMethod.GET, "/obtener_tareas").hasAnyAuthority("user", "admin")
                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(new AuthFailureHandler());
        http.headers().frameOptions().disable();
    }
}