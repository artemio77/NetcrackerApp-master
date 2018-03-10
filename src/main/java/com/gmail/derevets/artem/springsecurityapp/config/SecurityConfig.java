package com.gmail.derevets.artem.springsecurityapp.config;


import com.gmail.derevets.artem.springsecurityapp.model.Role;
import com.gmail.derevets.artem.springsecurityapp.service.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.gmail.derevets.artem.springsecurityapp.service")
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserServiceImpl userService;

    @Override
    protected void configure(final AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userService);
    }

    @Autowired
    protected void registerGlobalAuthentication(AuthenticationManagerBuilder registry) throws Exception {
        registry
                .userDetailsService(userService);
    }

    @Bean
    public PlaintextPasswordEncoder gatPasswordEncoder() {
        return new PlaintextPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole(Role.USER.name(), Role.ADMIN.name(),Role.ACCOUNTANT.name())
                .antMatchers("/welcome").hasAnyRole(Role.USER.name(), Role.ADMIN.name(), Role.ACCOUNTANT.name())
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/accountant/**").hasAnyRole(Role.ADMIN.name(),Role.ACCOUNTANT.name())
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .csrf().disable();

    }

}


