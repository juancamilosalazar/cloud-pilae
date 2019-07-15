package com.uco.pilae.pilae.security;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .oauth2ResourceServer().jwt();
1
        Okta.configureResourceServer401ResponseBody(http);
    }
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(HttpMethod.GET).permitAll()
                .and()
                .oauth2ResourceServer().jwt();

        Okta.configureResourceServer401ResponseBody(http);
    }*/
}