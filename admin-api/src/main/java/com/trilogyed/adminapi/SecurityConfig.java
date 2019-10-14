package com.trilogyed.adminapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception{

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic();

        /*


        httpSecurity
                .csrf().disable;
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

         */

        httpSecurity.authorizeRequests()
                .mvcMatchers("/login").authenticated()

                .mvcMatchers(HttpMethod.PUT,"/admin/inventory").hasAnyAuthority("EMPLOYEE","MANAGER","ADMIN")

                .mvcMatchers(HttpMethod.POST,"/admin/customers").hasAnyAuthority("TEAM_LEAD","MANAGER","ADMIN")

                .mvcMatchers(HttpMethod.POST,"/admin/inventory","/admin/invoices",
                        "/admin/level-ups","/admin/products").hasAnyAuthority("MANAGER","ADMIN")

                .mvcMatchers(HttpMethod.PUT,"/admin/customer","/admin/inventory",
                        "/admin/invoice","/admin/level-up","/admin/product").hasAnyAuthority("MANAGER","ADMIN")

                .mvcMatchers(HttpMethod.DELETE,"/admin/customer/*","/admin/inventory/*",
                        "/admin/invoice/*","/admin/level-up/*","/admin/product/*").hasAuthority("ADMIN")

                .anyRequest().permitAll();


        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
//                .csrf().disable();
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
    
}


